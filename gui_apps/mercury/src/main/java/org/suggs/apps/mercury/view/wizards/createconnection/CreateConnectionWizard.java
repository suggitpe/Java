/*
 * CreateConnectionWizard.java created on 17 Sep 2008 18:43:06 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.wizards.createconnection;

import org.suggs.apps.mercury.view.wizards.createconnection.pages.CreateConnTypePage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jface.wizard.Wizard;

/**
 * TODO Write javadoc for CreateConnectionWizard
 * 
 * @author suggitpe
 * @version 1.0 17 Sep 2008
 */
public class CreateConnectionWizard extends Wizard
{

    private static final Log LOG = LogFactory.getLog( CreateConnectionWizard.class );

    /**
     * Constructs a new instance.
     */
    public CreateConnectionWizard()
    {
        super();
    }

    /**
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages()
    {
        addPage( new CreateConnTypePage() );
    }

    /**
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish()
    {
        return true;
    }

    /**
     * @see org.eclipse.jface.wizard.Wizard#performCancel()
     */
    @Override
    public boolean performCancel()
    {
        LOG.debug( "Cancelled from create connection wizard" );
        return true;
    }

}
