/*
 * ConnectionTreePanel.java created on 24 Nov 2008 09:14:02 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.panels;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
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
public class ConnectionTreePanel extends Composite
{

    private static final Log LOG = LogFactory.getLog( ConnectionTreePanel.class );

    TreeViewer mViewer_ = null;

    /**
     * Constructs a new instance.
     */
    public ConnectionTreePanel( Composite parent )
    {
        super( parent, SWT.BORDER );
        setLayout( new FillLayout() );
        populateCtrls();

    }

    private void populateCtrls()
    {
        mViewer_ = new TreeViewer( this, SWT.NONE );
        mViewer_.getTree().setToolTipText( "Select a connection" );
        mViewer_.setContentProvider( new ConnectionTreeContentProvider() );
        mViewer_.setLabelProvider( new ConnectionTreeLabelProvider() );
    }

    /**
     * This class is used to actually label the data that you are
     * using
     * 
     * @author suggitpe
     * @version 1.0 26 Nov 2008
     */
    class ConnectionTreeLabelProvider implements ILabelProvider
    {

        /**
         * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
         */
        public Image getImage( Object arg0 )
        {
            // nadda
            return null;
        }

        /**
         * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
         */
        public String getText( Object arg0 )
        {
            // TODO Auto-generated method stub
            return null;
        }

        /**
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
         */
        public void dispose()
        {
        }

        /**
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object,
         *      java.lang.String)
         */
        public boolean isLabelProperty( Object arg0, String arg1 )
        {
            // TODO Auto-generated method stub
            return false;
        }

        /**
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
         */
        public void addListener( ILabelProviderListener arg0 )
        {
        }

        /**
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
         */
        public void removeListener( ILabelProviderListener arg0 )
        {
        }
    }

    /**
     * Content provider that allows the viewer to process the data
     * that is set on it
     * 
     * @author suggitpe
     * @version 1.0 26 Nov 2008
     */
    class ConnectionTreeContentProvider implements ITreeContentProvider
    {

        /**
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
         * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
         */
        public boolean hasChildren( Object node )
        {
            return ( ( (Node) node ).getChildNodes().getLength() > 0 );
        }

        /**
         * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
         */
        public Object[] getElements( Object node )
        {
            return toObjectArray( ( (Node) node ).getChildNodes() );
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
            LOG.debug( "input is a ["
                       + ( newInput == null ? "null" : newInput.getClass().getName() ) + "]" );
            // viewer.setInput( newInput );
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
