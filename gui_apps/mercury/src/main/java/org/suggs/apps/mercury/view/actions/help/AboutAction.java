/*
 * AboutAction.java created on 15 Oct 2008 06:14:49 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.actions.help;

import org.suggs.apps.mercury.view.dialogs.HelpAboutDialog;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Display;

/**
 * Action for managing the about dialog box
 * 
 * @author suggitpe
 * @version 1.0 15 Oct 2008
 */
public class AboutAction extends Action
{

    private static final Log LOG = LogFactory.getLog( AboutAction.class );

    /**
     * Constructs a new instance.
     */
    public AboutAction()
    {
        super( "&About" );
        setToolTipText( "Info about Mercury" );
    }

    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run()
    {
        LOG.debug( "Opening About dialog" );
        HelpAboutDialog had = new HelpAboutDialog( Display.getCurrent().getActiveShell() );
        had.open();
    }

}
