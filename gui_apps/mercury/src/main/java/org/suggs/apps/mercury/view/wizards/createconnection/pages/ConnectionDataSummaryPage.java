/*
 * IbmMqConnectionData.java created on 10 Nov 2008 18:55:02 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.wizards.createconnection.pages;

import org.suggs.apps.mercury.model.connection.ConnectionDetails;
import org.suggs.apps.mercury.view.wizards.createconnection.CreateConnectionWizard;

import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 * This pupose of this page is to show to the user what they have
 * selected through the wizard and allow them to run a test connection
 * through to the underlying middleware.
 * 
 * @author suggitpe
 * @version 1.0 10 Nov 2008
 */
public class ConnectionDataSummaryPage extends AbstractCreateConnectionPage
{

    private static final Log LOG = LogFactory.getLog( ConnectionDataSummaryPage.class );
    public static final String PAGE_NAME = "ConnectionDataSummaryPage";

    private TableViewer mViewer_;

    /**
     * Constructs a new instance.
     */
    public ConnectionDataSummaryPage()
    {
        super( PAGE_NAME, "Create Connection Summary Page" );
        setPageComplete( true );
        setDescription( "Please review the connection data provided here before hitting finish." );
    }

    /**
     * @see org.suggs.apps.mercury.view.wizards.createconnection.pages.AbstractCreateConnectionPage#doBuildControls(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected void doBuildControls( Composite controlComposite )
    {
        Composite c = new SummaryDataComposite( controlComposite );
        c.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );

        Composite t = new TestConnectionSummary( controlComposite );
        t.setLayoutData( new GridData( GridData.HORIZONTAL_ALIGN_END ) );
    }

    /**
     * Accessor method to enable us to update the table contents
     */
    public void updateTableContents()
    {
        Map<String, String> map = ( (CreateConnectionWizard) getWizard() ).getConnectionData();
        EditableTableItem[] data = new EditableTableItem[map.size()];

        Set<String> keys = map.keySet();

        int i = 0;
        for ( String s : keys )
        {
            data[i++] = new EditableTableItem( s, map.get( s ) );
        }

        mViewer_.setInput( data );
    }

    // ##################################
    // ########## INNER CLASSES #########
    // ##################################
    private class SummaryDataComposite extends Composite
    {

        /**
         * Constructs a new instance.
         */
        public SummaryDataComposite( Composite comp )
        {
            super( comp, SWT.NONE );
            setLayout( new GridLayout( 1, false ) );

            new Label( this, SWT.NONE ).setText( "Please review the following data to verify that the data is correct." );

            // build the table
            final Table table = new Table( this, SWT.BORDER | SWT.FULL_SELECTION );
            table.setToolTipText( "Review contents for new connection" );

            table.setLinesVisible( true );
            table.setHeaderVisible( true );

            TableLayout layout = new TableLayout();
            layout.addColumnData( new ColumnWeightData( 25, 75, true ) );
            layout.addColumnData( new ColumnWeightData( 75, 75, true ) );

            new TableColumn( table, SWT.LEFT ).setText( "Name" );
            new TableColumn( table, SWT.LEFT ).setText( "Value" );

            table.setLayout( layout );
            table.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );

            // build the table viewer
            mViewer_ = new TableViewer( table );

            // hook in the table contents
            mViewer_.setContentProvider( new SummaryTableContentProvider() );
            mViewer_.setLabelProvider( new SummaryTableLabelProvider() );
            updateTableContents();
        }
    }

    /**
     * @author suggitpe
     * @version 1.0 14 Nov 2008
     */
    private class TestConnectionSummary extends Composite
    {

        /**
         * Constructs a new instance.
         */
        public TestConnectionSummary( Composite comp )
        {
            super( comp, SWT.NONE );
            setLayout( new GridLayout( 1, false ) );

            final Button test = new Button( this, SWT.PUSH );
            test.setText( "Test Connection" );
            test.addSelectionListener( new SelectionAdapter()
            {

                /**
                 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
                 */
                @Override
                public void widgetSelected( SelectionEvent e )
                {
                    ConnectionDetails dtls = ( (CreateConnectionWizard) getWizard() ).getConnectionDetails();
                    LOG.debug( "Testing connection with connection details [" + dtls + "]" );
                }
            } );

        }
    }

    /**
     * This class is the content provider for the data for the table
     * 
     * @author suggitpe
     * @version 1.0 12 Nov 2008
     */
    private class SummaryTableContentProvider implements IStructuredContentProvider
    {

        /**
         * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
         */
        public Object[] getElements( Object inputElement )
        {
            return (Object[]) inputElement;
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
            // nadda
        }
    }

    /**
     * This class is the Label provider for the table
     * 
     * @author suggitpe
     * @version 1.0 12 Nov 2008
     */
    private class SummaryTableLabelProvider implements ITableLabelProvider
    {

        /**
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object,
         *      int)
         */
        public Image getColumnImage( Object element, int columnIndex )
        {
            // no thanks
            return null;
        }

        /**
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object,
         *      int)
         */
        public String getColumnText( Object element, int columnIndex )
        {
            EditableTableItem tItem = (EditableTableItem) element;
            switch ( columnIndex )
            {
                case 0:
                    return tItem.getName();
                case 1:
                    return tItem.getValue();
                default:
                    return "Inavlid column" + columnIndex;

            }
        }

        /**
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
         */
        public void addListener( ILabelProviderListener listener )
        {
            // nadda
        }

        /**
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
         */
        public void dispose()
        {
            // nadda
        }

        /**
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object,
         *      java.lang.String)
         */
        public boolean isLabelProperty( Object element, String property )
        {
            return false;
        }

        /**
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
         */
        public void removeListener( ILabelProviderListener listener )
        {
        }
    }

    /**
     * This class is used to encapsulate the data that will be sent to
     * the table.
     * 
     * @author suggitpe
     * @version 1.0 13 Nov 2008
     */
    class EditableTableItem
    {

        private String mName_;
        private String mValue_;

        /**
         * Constructs a new instance.
         * 
         * @param name
         *            the name to set
         * @param value
         *            the value to set
         */
        public EditableTableItem( String name, String value )
        {
            mName_ = name;
            mValue_ = value;
        }

        /**
         * Returns the value of Name.
         * 
         * @return Returns the Name.
         */
        public String getName()
        {
            return mName_;
        }

        /**
         * Sets the mName_ field to the specified value.
         * 
         * @param name
         *            The Name to set.
         */
        public void setName( String name )
        {
            mName_ = name;
        }

        /**
         * Returns the value of Value.
         * 
         * @return Returns the Value.
         */
        public String getValue()
        {
            return mValue_;
        }

        /**
         * Sets the Value field to the specified value.
         * 
         * @param value
         *            The Value to set.
         */
        public void setValue( String value )
        {
            mValue_ = value;
        }

    }

}
