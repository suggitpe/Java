/*
 * CreateConnectionWizardAction.java created on 15 Sep 2008 18:42:43 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.actions;

import org.suggs.apps.mercury.view.wizards.createconnection.CreateConnectionWizard;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

/**
 * This action is responsible for the creation of new connections
 * 
 * @author suggitpe
 * @version 1.0 15 Sep 2008
 */
public class CreateConnectionWizardAction extends Action
{

    private static final Log LOG = LogFactory.getLog( CreateConnectionWizardAction.class );

    /**
     * Constructs a new instance.
     */
    public CreateConnectionWizardAction()
    {
        super( "&Create Connection" );
        setToolTipText( "Create new connection" );
        setImageDescriptor( ImageDescriptor.createFromURL( this.getClass()
            .getClassLoader()
            .getResource( "eclipse.gif" ) ) );
    }

    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run()
    {
        LOG.debug( "Starting create connection wizard" );
        WizardDialog d = new WizardDialog( Display.getCurrent().getActiveShell(),
                                           new CreateConnectionWizard() );
        d.open();

    }

}
