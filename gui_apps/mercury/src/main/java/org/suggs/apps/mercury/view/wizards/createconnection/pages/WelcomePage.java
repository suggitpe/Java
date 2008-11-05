/*
 * WelcomePage.java created on 22 Oct 2008 17:46:35 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.wizards.createconnection.pages;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * This is the welcome page for the wizard.
 * 
 * @author suggitpe
 * @version 1.0 22 Oct 2008
 */
public class WelcomePage extends AbstractCreateConnectionPage
{

    public static final String PAGE_NAME = "WelcomePage";
    private static final String PAGE_HELP = "This is a welcome page no help should be needed.\n\nPress next for the next page in the wizard.";

    /**
     * Constructs a new instance.
     * 
     * @param pageName
     */
    public WelcomePage()
    {
        super( PAGE_NAME, "Welcome" );
        setDescription( "Welcome to the Create Connection Wizard" );
        setPageComplete( true );
    }

    /**
     * @see org.suggs.apps.mercury.view.wizards.createconnection.pages.AbstractCreateConnectionPage#doBuildControls(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void doBuildControls( Composite contextComposite )
    {
        new Label( contextComposite, SWT.NONE ).setText( "In this wizard we will guide you through\nthe process of creating a new connection to a JMS resource" );

        new Label( contextComposite, SWT.NONE ).setText( "As a bare minimum you will need the following:\n"
                                                         + " - the type of connection that you want to create"
                                                         + " - hostname of the JMS broker\n"
                                                         + " - port number that the JMS broker resides on" );
        new Label( contextComposite, SWT.NONE ).setText( "In addition you may need to have:\n"
                                                         + " - username and password to access the server"
                                                         + " - any other additional information for the broker connection" );
    }

    /**
     * @see org.eclipse.jface.dialogs.DialogPage#performHelp()
     */
    @Override
    public void performHelp()
    {
        MessageDialog.openInformation( getWizard().getContainer().getShell(),
                                       "Welcome page help",
                                       PAGE_HELP );

    }

}
