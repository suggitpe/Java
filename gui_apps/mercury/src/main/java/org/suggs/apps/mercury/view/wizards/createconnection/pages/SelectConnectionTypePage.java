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
    public SelectConnectionTypePage()
    {
        super( PAGE_NAME, "Connection Type Selection" );
        setDescription( "Select the underlying middleware implementation from the list below" );
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

        new Label( controlComposite, SWT.NONE ).setText( "Connection name" );
        final Text name = new Text( controlComposite, SWT.BORDER );
        name.addModifyListener( new ModifyListener()
        {

            public void modifyText( ModifyEvent e )
            {
                mConnName_ = name.getText();
                checkIfPageComplete();
            }
        } );

        new Label( controlComposite, SWT.CENTER ).setText( "Please select connection type ..." );
        final Combo combo = new Combo( controlComposite, SWT.DROP_DOWN );

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

}
