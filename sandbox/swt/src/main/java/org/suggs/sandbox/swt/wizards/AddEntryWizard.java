/*
 * AddEntryWizard.java created on 21 Oct 2008 18:52:11 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.wizards;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;

/**
 * Address Entry Wizard demonstration.
 * 
 * @author suggitpe
 * @version 1.0 21 Oct 2008
 */
public class AddEntryWizard extends Wizard
{

    private WelcomePage welcomePage_;
    private NamePage namePage_;
    private EmailPage emailPage_;

    /**
     * Constructs a new instance.
     */
    public AddEntryWizard()
    {
        welcomePage_ = new WelcomePage();
        namePage_ = new NamePage();
        emailPage_ = new EmailPage();

        addPage( welcomePage_ );
        addPage( namePage_ );
        addPage( emailPage_ );

        setWindowTitle( "Address Book Entry Wizard" );
    }

    /**
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish()
    {
        new AddressEntry( namePage_.getFirstName(),
                          namePage_.getLastName(),
                          emailPage_.getEmailAddress() );

        MessageDialog.openInformation( getShell(),
                                       "Demo Complete",
                                       "You have entered the following ...\nFirst name: "
                                                       + namePage_.getFirstName() + "\nLast name: "
                                                       + namePage_.getLastName()
                                                       + "\nEmail address: "
                                                       + emailPage_.getEmailAddress() );

        return true;
    }

}
