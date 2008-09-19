/*
 * CreateConnTypePage.java created on 17 Sep 2008 18:50:55 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.wizards.createconnection.pages;

import org.suggs.apps.mercury.ContextProvider;

import java.util.HashMap;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * This page forces the choice of making the decision about which JMS
 * provider to select.
 * 
 * @author suggitpe
 * @version 1.0 17 Sep 2008
 */
public class CreateConnTypePage extends WizardPage
{

    public static final String PAGE_NAME = "CreateConnectionType";
    private Combo mCombo_;

    /**
     * Constructs a new instance.
     */
    public CreateConnTypePage()
    {
        super( PAGE_NAME, "Connection Type Selection", null );
    }

    /**
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl( Composite parent )
    {
        Composite topLevel = new Composite( parent, SWT.NONE );
        topLevel.setLayout( new GridLayout( 2, false ) );

        new Label( topLevel, SWT.CENTER ).setText( "Please select connection type ..." );
        mCombo_ = new Combo( topLevel, SWT.DROP_DOWN );
        HashMap map = (HashMap) ContextProvider.instance().getBean( "adapterList" );

        // TODO: sort out type safety
        mCombo_.setItems( (String[]) map.keySet().toArray( new String[0] ) );

        setControl( topLevel );
        setPageComplete( true );
    }

    /**
     * Getter for the text in the combo box
     * 
     * @return the text in the combo box
     */
    public String getConnectionTYpe()
    {
        return mCombo_.getText();
    }
}
