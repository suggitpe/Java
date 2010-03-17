/*
 * AbstractConnectionDetailsPage.java created on 5 Nov 2008 19:28:57 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.wizards.createconnection.pages;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * This is the abstract implementation for the create details page.
 * 
 * @author suggitpe
 * @version 1.0 5 Nov 2008
 */
public class GenericConnectionDetailsPage extends AbstractCreateConnectionPage
{

    private static final String NFE_ERROR_TEXT = "The value that has been entered for the port number is not a number, please only user whole numbers for this field";

    public static final String PAGE_NAME = "GenericConnectionDetailsPage";

    // private static final Log LOG = LogFactory.getLog(
    // AbstractConnectionDetailsPage.class );
    private String mHostname_;
    private int mPort_;
    private boolean mSecurityEnabled_;
    private String mUsername_;
    private String mPassword_;

    /**
     * Constructs a new instance.
     */
    public GenericConnectionDetailsPage()
    {
        super( PAGE_NAME, "Enter generic connection details" );
        setDescription( "Enter details for the basic connection information for the broker that you wish to connect to" );
        setPageComplete( false );
    }

    /**
     * @see org.suggs.apps.mercury.view.wizards.createconnection.pages.AbstractCreateConnectionPage#doBuildControls(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void doBuildControls( Composite controlComposite )
    {
        Composite c = new ConnectionDetailsComposite( controlComposite );
        c.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );
    }

    /**
     * @see org.eclipse.jface.wizard.WizardPage#getNextPage()
     */
    @Override
    public IWizardPage getNextPage()
    {
        SelectConnectionTypePage sp = (SelectConnectionTypePage) getWizard().getPage( SelectConnectionTypePage.PAGE_NAME );
        if ( sp.getConnectionType().equals( "IBM_MQ" ) )
        {
            return getWizard().getPage( IbmMqConnectionDataPage.PAGE_NAME );
        }
        ( (WizardPage) getWizard().getPage( IbmMqConnectionDataPage.PAGE_NAME ) ).setPageComplete( true );
        return getWizard().getPage( ConnectionDataSummaryPage.PAGE_NAME );

    }

    /**
     * Checking method to see if this page is complete
     */
    protected void checkIfPageComplete()
    {
        if ( mHostname_ == null || mHostname_.length() == 0 || mPort_ == 0 )
        {
            setPageComplete( false );
            return;
        }

        if ( mSecurityEnabled_ )
        {
            if ( mUsername_ == null || mUsername_.length() == 0 )
            {
                setPageComplete( false );
                return;

            }
        }

        setPageComplete( true );
    }

    /**
     * Getter for the hostname field
     * 
     * @return the hostname field
     */
    public String getHostname()
    {
        return mHostname_;
    }

    /**
     * Getter for the port number
     * 
     * @return the port number
     */
    public int getPort()
    {
        return mPort_;
    }

    /**
     * Getter for the security enabled flag
     * 
     * @return the security enabled flag
     */
    public boolean isSecurityEnabled()
    {
        return mSecurityEnabled_;
    }

    /**
     * Getter for the username field
     * 
     * @return the user name
     */
    public String getUsername()
    {
        return mUsername_;
    }

    /**
     * Getter for the password field
     * 
     * @return the password field
     */
    public String getPassword()
    {
        return mPassword_;
    }

    // ##################################
    // ########## INNER CLASSES #########
    // ##################################
    /**
     * Class to represent the data behind the
     * 
     * @author suggitpe
     * @version 1.0 5 Nov 2008
     */
    private class ConnectionDetailsComposite extends Composite
    {

        /**
         * Constructs a new instance.
         * 
         * @param comp
         */
        public ConnectionDetailsComposite( Composite comp )
        {
            super( comp, SWT.NONE );
            setLayout( new GridLayout( 2, false ) );

            // create hostname field
            new Label( this, SWT.NONE ).setText( "Hostname:" );
            final Text hostname = new Text( this, SWT.BORDER );
            hostname.setLayoutData( TEXT_BOX_STYLE );
            hostname.addModifyListener( new ModifyListener()
            {

                public void modifyText( ModifyEvent e )
                {
                    mHostname_ = hostname.getText();
                    checkIfPageComplete();
                }
            } );

            // port number
            new Label( this, SWT.NONE ).setText( "Port number:" );
            final Text portNum = new Text( this, SWT.BORDER );
            portNum.setLayoutData( TEXT_BOX_STYLE );
            portNum.addModifyListener( new ModifyListener()
            {

                public void modifyText( ModifyEvent e )
                {

                    String p = portNum.getText();
                    if ( p == null || p.equals( "" ) )
                    {
                        mPort_ = 0;
                        return;
                    }

                    int ptNum = 0;
                    try
                    {
                        ptNum = Integer.parseInt( p );
                        mPort_ = ptNum;
                    }
                    catch ( NumberFormatException nfe )
                    {
                        mPort_ = 0;
                        MessageDialog.openError( getShell(), "Number Format Error", NFE_ERROR_TEXT );
                        portNum.setText( "" );
                    }
                    checkIfPageComplete();
                }
            } );

            // set security check box
            new Label( this, SWT.NONE ).setText( "Enable Security:" );
            final Button setSecurity = new Button( this, SWT.CHECK );

            // security username
            new Label( this, SWT.NONE ).setText( "Username:" );
            final Text username = new Text( this, SWT.BORDER | SWT.READ_ONLY );
            username.setLayoutData( TEXT_BOX_STYLE );
            username.setEnabled( false );
            username.addModifyListener( new ModifyListener()
            {

                public void modifyText( ModifyEvent e )
                {
                    mUsername_ = username.getText();
                    checkIfPageComplete();
                }
            } );

            new Label( this, SWT.NONE ).setText( "Password:" );
            final Text password = new Text( this, SWT.BORDER | SWT.READ_ONLY | SWT.PASSWORD );
            password.setLayoutData( TEXT_BOX_STYLE );
            password.setEnabled( false );
            password.addModifyListener( new ModifyListener()
            {

                public void modifyText( ModifyEvent e )
                {
                    mPassword_ = password.getText();
                }
            } );

            // this is down here for obvious reasons
            setSecurity.addSelectionListener( new SelectionAdapter()
            {

                /**
                 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
                 */
                @Override
                public void widgetSelected( SelectionEvent e )
                {
                    mSecurityEnabled_ = setSecurity.getSelection();
                    if ( mSecurityEnabled_ )
                    {
                        username.setEditable( true );
                        username.setEnabled( true );
                        password.setEditable( true );
                        password.setEnabled( true );
                    }
                    else
                    {
                        username.setEditable( false );
                        username.setEnabled( false );
                        username.setText( "" );
                        password.setEditable( false );
                        password.setEnabled( false );
                        password.setText( "" );
                    }
                    checkIfPageComplete();
                }

            } );
        }

    }

}
