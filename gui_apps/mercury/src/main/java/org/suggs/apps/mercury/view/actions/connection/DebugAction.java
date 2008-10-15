/*
 * DebugAction.java created on 15 Oct 2008 06:22:01 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.actions.connection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jface.action.Action;

/**
 * TODO Write javadoc for DebugAction
 * 
 * @author suggitpe
 * @version 1.0 15 Oct 2008
 */
public class DebugAction extends Action
{

    private static final Log LOG = LogFactory.getLog( DebugAction.class );

    /**
     * Constructs a new instance.
     */
    public DebugAction()
    {
        super( "&Debug" );
        setToolTipText( "This will debug the currently known connection set" );
    }

    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run()
    {
        LOG.debug( "Opening up connection debug dialog" );
    }

}
