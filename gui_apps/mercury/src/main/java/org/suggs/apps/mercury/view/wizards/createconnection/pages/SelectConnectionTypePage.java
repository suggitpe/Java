/*
 * SelectConnectionTypePage.java created on 17 Sep 2008 18:50:55 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.wizards.createconnection.pages;

import org.suggs.apps.mercury.ContextProvider;

import java.util.HashMap;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * This page forces the choice of making the decision about which JMS
 * provider to select.
 * 
 * @author suggitpe
 * @version 1.0 17 Sep 2008
 */
public class SelectConnectionTypePage extends AbstractCreateConnectionPage
{

    public static final String PAGE_NAME = "CreateConnectionType";
    private static final String PAGE_HELP = "Populate the name of the connection and then select the connection type.\n\nThen press next to move to the next page.";
    private String mConnType_ = "";
    private String mConnName_ = "";
    private String[] mOptions_;

    /**
     * Constructs a new instance.
     */
    @SuppressWarnings("unchecked")
    public SelectConnectionTypePage()
    {
        super( PAGE_NAME, "Connection Type Selection" );
        setDescription( "Select the middleware implementation from the list below" );
        setPageComplete( false );

        HashMap map = (HashMap) ContextProvider.instance().getBean( "adapterList" );
        mOptions_ = (String[]) map.keySet().toArray( new String[0] );
    }

    /**
     * @see org.suggs.apps.mercury.view.wizards.createconnection.pages.AbstractCreateConnectionPage#doBuildControls(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void doBuildControls( Composite controlComposite )
    {
        new SelectConnectionComposite( controlComposite );
    }

    /**
     * Checks to see if the pge has been correctly set up
     */
    private void checkIfPageComplete()
    {
        if ( mConnName_.length() > 0 && mConnType_.length() > 0 )
        {
            setPageComplete( true );
        }
        else
        {
            setPageComplete( false );
        }
    }

    /**
     * @see org.eclipse.jface.dialogs.DialogPage#performHelp()
     */
    @Override
    public void performHelp()
    {
        MessageDialog.openInformation( getWizard().getContainer().getShell(),
                                       "Create Connection Help",
                                       PAGE_HELP );
    }

    /**
     * @see org.eclipse.jface.wizard.WizardPage#getNextPage()
     */
    @Override
    public IWizardPage getNextPage()
    {
        return null;
    }

    /**
     * Getter for the text from the combo box
     * 
     * @return the text from the combo box
     */
    public String getConnectionType()
    {
        return mConnType_;
    }

    /**
     * Getter for the connection name
     * 
     * @return
     */
    public String getConnectionName()
    {
        return mConnName_;
    }

    /**
     * This composite class is created to house the widgets associated
     * with this screen.
     * 
     * @author suggitpe
     * @version 1.0 5 Nov 2008
     */
    private class SelectConnectionComposite extends Composite
    {

        /**
         * Constructs a new instance.
         */
        public SelectConnectionComposite( Composite comp )
        {
            super( comp, SWT.NONE );
            setLayout( new GridLayout( 2, false ) );

            new Label( this, SWT.NONE ).setText( "Connection Name:" );
            final Text name = new Text( this, SWT.BORDER );
            name.setLayoutData( TEXT_BOX_STYLE );
            name.addModifyListener( new ModifyListener()
            {

                public void modifyText( ModifyEvent e )
                {
                    mConnName_ = name.getText();
                    checkIfPageComplete();
                }
            } );

            new Label( this, SWT.CENTER ).setText( "Connection Type:" );
            final Combo combo = new Combo( this, SWT.DROP_DOWN );
            combo.setLayoutData( TEXT_BOX_STYLE );

            combo.setItems( mOptions_ );
            combo.addSelectionListener( new SelectionAdapter()
            {

                /**
                 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
                 */
                @Override
                public void widgetSelected( SelectionEvent selEv )
                {
                    mConnType_ = combo.getText();
                    checkIfPageComplete();
                }

            } );
        }
    }
}
