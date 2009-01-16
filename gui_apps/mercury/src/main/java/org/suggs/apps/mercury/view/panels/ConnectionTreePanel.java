/*
 * ConnectionTreePanel.java created on 24 Nov 2008 09:14:02 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.panels;

import org.suggs.apps.mercury.ContextProvider;
import org.suggs.apps.mercury.model.connection.connectionstore.ConnectionStoreException;
import org.suggs.apps.mercury.model.connection.connectionstore.IConnectionStore;
import org.suggs.apps.mercury.model.connection.connectionstore.IConnectionStoreChangeListener;
import org.suggs.apps.mercury.model.util.MercuryUtilityException;
import org.suggs.apps.mercury.model.util.xml.IXsltTransformerUtil;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * In this panel we look at the connections from the connection store
 * as a tree that we can traverse
 * 
 * @author suggitpe
 * @version 1.0 24 Nov 2008
 */
public class ConnectionTreePanel extends Composite implements IConnectionStoreChangeListener
{

    private static final String XSLT = "xslt/connection-tree.xsl";

    private TreeViewer mViewer_ = null;
    private IXsltTransformerUtil mXsltUtil_;
    private IConnectionStore mConnStr_;

    // initialiser
    {
        mXsltUtil_ = (IXsltTransformerUtil) ContextProvider.instance().getBean( "xsltUtil" );
        mConnStr_ = (IConnectionStore) ContextProvider.instance()
            .getBean( "connectionStoreManager" );
        mConnStr_.addConnectionStoreChangeListener( this );
    }

    /**
     * Constructs a new instance.
     */
    public ConnectionTreePanel( Composite parent )
    {
        super( parent, SWT.BORDER );
        populateCtrls();
    }

    /**
     * Populates the tree and its contents.
     */
    private void populateCtrls()
    {
        setLayout( new FillLayout() );

        mViewer_ = new TreeViewer( this, SWT.BORDER );
        mViewer_.getTree().setToolTipText( "Select a connection" );
        mViewer_.setContentProvider( new ConnectionTreeContentProvider() );
        mViewer_.setLabelProvider( new ConnectionTreeLabelProvider() );

        // now lets populate the table itself
        mViewer_.setInput( createConnectionData() );
    }

    /**
     * Here we manage the creation of the input data for the tree
     * viewer.
     * 
     * @return the node that will be used for the tree viewer data.
     */
    private Node createConnectionData()
    {
        try
        {
            String b = mConnStr_.getConnectionStoreDumpAsXml();
            Node elem = mXsltUtil_.transformXmlToDom( b.getBytes(), XSLT );
            return elem;

        }
        catch ( ConnectionStoreException cse )
        {
            throw new IllegalStateException( "Failed to retrieve connection store details from the internal connection store",
                                             cse );
        }
        catch ( MercuryUtilityException mue )
        {
            throw new IllegalStateException( "Failed to parse xml document for connections", mue );
        }

    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connectionstore.IConnectionStoreChangeListener#handleConnectionStoreChange()
     */
    public void handleConnectionStoreChange()
    {
        mViewer_.setInput( createConnectionData() );
    }

    /**
     * This class is used to actually label the data that you are
     * using within the tree. This is extending the default impl as
     * the functionality we need to add is small
     * 
     * @author suggitpe
     * @version 1.0 26 Nov 2008
     */
    class ConnectionTreeLabelProvider extends LabelProvider
    {

        /**
         * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
         */
        @Override
        public String getText( Object node )
        {
            Node n = (Node) node;
            if ( n.getAttributes().getNamedItem( "name" ) != null )
            {
                return n.getAttributes().getNamedItem( "name" ).getTextContent();
            }
            return null;
        }

    }

    /**
     * Content provider that allows the viewer to process the data
     * that is set on it in a manner that suits the data.
     * 
     * @author suggitpe
     * @version 1.0 26 Nov 2008
     */
    class ConnectionTreeContentProvider implements ITreeContentProvider
    {

        /**
         * This is called every time it needs the child elements of an
         * object.
         * 
         * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
         */
        public Object[] getChildren( Object parent )
        {
            return toObjectArray( ( (Node) parent ).getChildNodes() );
        }

        /**
         * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
         */
        public Object getParent( Object node )
        {
            return ( (Node) node ).getParentNode();
        }

        /**
         * This is called by the tree viewer to enable it to
         * understand whether a node in the tree ie expandable.
         * 
         * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
         */
        public boolean hasChildren( Object node )
        {
            return ( ( (Node) node ).getChildNodes().getLength() > 0 );
        }

        /**
         * This is called by the tree viewer to get the initial set of
         * elements for the input object. In the instance of this tree
         * we want to ignore the top level element as this is no good
         * and instead we want to get the layer beneath (ie the impl
         * layer).
         * 
         * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
         */
        public Object[] getElements( Object node )
        {
            return toObjectArray( ( (Node) node ).getChildNodes().item( 0 ).getChildNodes() );
        }

        /**
         * @see org.eclipse.jface.viewers.IContentProvider#dispose()
         */
        public void dispose()
        {
            // nadda
        }

        /**
         * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
         *      java.lang.Object, java.lang.Object)
         */
        public void inputChanged( Viewer viewer, Object oldInput, Object newInput )
        {
        }

        /**
         * Converts a nodelist into an object array
         * 
         * @param list
         *            the nodelist from which to get the nodes
         * @return an objecyt array of the nodes
         */
        private Object[] toObjectArray( NodeList list )
        {
            int len = list.getLength();
            Object[] ret = new Object[len];
            for ( int i = 0; i < len; ++i )
            {
                ret[i] = list.item( i );
            }
            return ret;
        }

    }

}
