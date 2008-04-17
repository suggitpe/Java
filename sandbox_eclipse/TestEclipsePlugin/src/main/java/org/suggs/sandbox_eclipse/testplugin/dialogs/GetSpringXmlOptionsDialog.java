/*
 * GetSpringXmlOptionsDialog.java created on 11 Mar 2008 19:08:08 by suggitpe for project Sandbox - TestEclipsePlugin
 * 
 */
package org.suggs.sandbox_eclipse.testplugin.dialogs;

import java.io.File;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * This class is a dialog class that allows us to collect data from
 * the users as to the settings to use when creating the Spring XML.
 * This class extends the Dialog class so that it can implement its on
 * version of the Dialog box.
 * 
 * @author suggitpe
 * @version 1.0 11 Mar 2008
 */
public class GetSpringXmlOptionsDialog extends Dialog
{

    private GridData mD2;
    private GridData mD3;

    {
        mD2 = new GridData( GridData.HORIZONTAL_ALIGN_FILL );
        mD2.horizontalSpan = 2;
        mD3 = new GridData( GridData.HORIZONTAL_ALIGN_FILL );
        mD3.horizontalSpan = 3;
    }

    private String mDestinationValue_;
    private String mKeyValue_;
    private String mFileNameValue_;

    public static final String DEST_POPUP = "POPUP";
    public static final String DEST_FILE = "FILE";

    public static final String KEY_NONE = "NONE";
    public static final String KEY_CONFS = "CONFS";
    public static final String KEY_ADVCS = "ADVICES";

    private Button mPopupRadio_;
    private Button mFileRadio_;
    private Text mFileName_;

    private Button mNoneRadio_;
    private Button mConfsRadio_;
    private Button mAdvicesRadio_;

    /**
     * Constructs a new instance.
     * 
     * @param parentShell
     */
    public GetSpringXmlOptionsDialog( Shell parentShell )
    {
        super( parentShell );
    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#close()
     */
    @Override
    public boolean close()
    {
        // here we need to get the data out of the dialog widgets
        // before we close the dialog
        mFileNameValue_ = mFileName_.getText();
        mDestinationValue_ = mPopupRadio_.getSelection() ? DEST_POPUP : DEST_FILE;
        mKeyValue_ = mNoneRadio_.getSelection() ? KEY_NONE : mConfsRadio_.getSelection()
                        ? KEY_CONFS : KEY_ADVCS;
        return super.close();
    }

    /**
     * Getter for the required destination. This should be replaced
     * with an enumeration when we are fully Java 5 compatible.
     * 
     * @return the int representing the destination.
     */
    public String getDestination()
    {
        return mDestinationValue_;
    }

    /**
     * Getter for the contents of the file text box
     * 
     * @return the contents of the file text box
     */
    public String getFileName()
    {
        return mFileNameValue_;
    }

    /**
     * Getter for the required FSM key selected. This should be
     * replaced with an enumeration when we are fully Java 5
     * compatible.
     * 
     * @return the int representing the FSM key selection.
     */
    public String getFsmKeySelection()
    {
        return mKeyValue_;
    }

    /**
     * This is overridden so we can create the internal Dialog
     * controls
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea( Composite parent )
    {
        Composite ret = (Composite) super.createDialogArea( parent );
        createKeySelection( ret );
        createDestinationSection( ret );
        ret.pack();
        return ret;
    }

    /**
     * This method is overriden so that we can add in some validation
     * logic around the passed in filenames.
     * 
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    @Override
    protected void okPressed()
    {
        // now we do the validation
        if ( mFileRadio_.getSelection() )
        {
            String name = mFileName_.getText();
            if ( name == null || name.equals( "" ) )
            {
                MessageDialog.openError( Display.getCurrent().getActiveShell(),
                                         "Incomplete input",
                                         "If you select the file destination, then "
                                                         + "you also need to select a filename" );
                return;
            }

            File dest = new File( name );
            if ( !dest.exists() )
            {
                MessageDialog.openError( Display.getCurrent().getActiveShell(),
                                         "Bad data",
                                         "The filename entered ["
                                                         + name
                                                         + "], does not exist. Please make sure that the file exists before continuing." );
                return;
            }
        }

        super.okPressed();
    }

    /**
     * Helper method to create the key selection section of the dialog
     * 
     * @param aComp
     *            the base shell
     */
    private void createKeySelection( final Composite aComp )
    {
        // -------------------
        // create the group to use including any text
        Group group1 = new Group( aComp, SWT.None );
        group1.setText( "FSM Key Selection" );

        // set the layout such that we have to columns and they do not
        // have the same size
        group1.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );
        group1.setLayout( new GridLayout( 3, false ) );

        mNoneRadio_ = new Button( group1, SWT.RADIO );
        mNoneRadio_.setSelection( true );
        mNoneRadio_.setText( "None" );

        mAdvicesRadio_ = new Button( group1, SWT.RADIO );
        mAdvicesRadio_.setText( "Advices" );

        mConfsRadio_ = new Button( group1, SWT.RADIO );
        mConfsRadio_.setText( "Confirmations" );
    }

    /**
     * Helper method to create the destination section of the dialog
     * 
     * @param aComp
     *            the base shell
     */
    private void createDestinationSection( final Composite aComp )
    {

        // -------------------
        Group group2 = new Group( aComp, SWT.None );
        group2.setText( "Destination" );

        // set the layout such that we have to columns and they do not
        // have the same size
        group2.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );
        group2.setLayout( new GridLayout( 3, false ) );

        mPopupRadio_ = new Button( group2, SWT.RADIO );
        mPopupRadio_.setSelection( true );
        mPopupRadio_.setText( "Popup" );
        mPopupRadio_.setToolTipText( "Selecting this option means that the end result will end up in a little popup window to copy and paste" );

        mFileRadio_ = new Button( group2, SWT.RADIO );
        mFileRadio_.setText( "File" );
        mFileRadio_.setLayoutData( mD2 );
        mFileRadio_.setToolTipText( "Selecting this option means that the end result will be written to a file" );

        // now we should create the file selection
        final Label fileLabel = new Label( group2, SWT.None );
        fileLabel.setText( "File:" );
        fileLabel.setEnabled( false );

        mFileName_ = new Text( group2, SWT.BORDER );
        mFileName_.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );
        // mFileName_.setEditable( false );
        mFileName_.setEnabled( false );
        mFileName_.selectAll();

        // set the button
        final Button file = new Button( group2, SWT.None );
        file.setText( "..." );
        file.setEnabled( false );

        // ------------------
        // set up the selection listeners
        file.addSelectionListener( new SelectionListener()
        {

            public void widgetDefaultSelected( SelectionEvent e )
            {
            }

            public void widgetSelected( SelectionEvent e )
            {
                FileDialog fd = new FileDialog( Display.getCurrent().getActiveShell() );
                fd.setFilterPath( "c:\\" );
                fd.setFilterExtensions( new String[] { "*.xml" } );
                String selected = fd.open();
                mFileName_.setText( selected );
            }
        } );

        mFileRadio_.addSelectionListener( new SelectionListener()
        {

            public void widgetDefaultSelected( SelectionEvent e )
            {
            }

            public void widgetSelected( SelectionEvent e )
            {
                if ( mFileRadio_.getSelection() )
                {
                    fileLabel.setEnabled( true );
                    mFileName_.setEnabled( true );
                    file.setEnabled( true );
                    // mFileName_.setEditable( false );
                }
                else
                {
                    fileLabel.setEnabled( false );
                    mFileName_.setEnabled( false );
                    file.setEnabled( false );
                }
            }
        } );
    }

    /**
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    @Override
    protected void configureShell( Shell newShell )
    {
        super.configureShell( newShell );
        newShell.setText( "FSM Generation Options" );
    }

}
