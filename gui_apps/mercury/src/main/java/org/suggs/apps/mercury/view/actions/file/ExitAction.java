/*
 * ExitAction.java created on 20 Oct 2008 06:56:31 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.actions.file;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Display;

/**
 * Encapsulation of the exit from Mercury functionality
 * 
 * @author suggitpe
 * @version 1.0 20 Oct 2008
 */
public class ExitAction extends Action
{

    private static final Log LOG = LogFactory.getLog( ExitAction.class );

    /**
     * Constructs a new instance.
     */
    public ExitAction()
    {
        super( "&Exit" );
        setToolTipText( "Exit Mercury application" );
    }

    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run()
    {
        LOG.debug( "Exiting Mercury" );
        Display.getCurrent().getActiveShell().close();
    }

}
