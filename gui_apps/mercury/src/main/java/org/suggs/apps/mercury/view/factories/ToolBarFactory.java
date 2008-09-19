/*
 * ToolBarBuilder.java created on 16 Sep 2008 07:15:27 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.factories;

import org.suggs.apps.mercury.view.IActionManager;
import org.suggs.apps.mercury.view.IToolBarFactory;
import org.suggs.apps.mercury.view.actions.CreateConnectionWizardAction;

import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jface.action.ToolBarManager;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * Builder class that is responsible for the contruction of the
 * toolbars used in the application
 * 
 * @author suggitpe
 * @version 1.0 16 Sep 2008
 */
public class ToolBarFactory implements IToolBarFactory, InitializingBean
{

    private static final Log LOG = LogFactory.getLog( ToolBarFactory.class );

    private IActionManager mActionManager_;

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception
    {
        Assert.notNull( mActionManager_,
                        "An action manager object must be injected into the ToolBarFactory object.  Please revise Spring XML" );
    }

    /**
     * @see org.suggs.apps.mercury.view.IToolBarFactory#createToolbar(java.lang.String,
     *      int)
     */
    public ToolBarManager createToolbar( String toolBarType, int style )
    {
        if ( LOG.isDebugEnabled() )
        {
            LOG.debug( "Constructing toolbar manager for type [" + toolBarType + "]" );
        }

        if ( toolBarType.equals( "MAIN" ) )
        {
            return buildMainToolbar( style );
        }
        throw new NotImplementedException( "No toolbar manager exists for type [" + toolBarType
                                           + "]" );
    }

    /**
     * This method wil build the toolbar manager for the main screen
     * 
     * @param style
     *            the style of the toolbar
     * @return the toolbar manager
     */
    private final ToolBarManager buildMainToolbar( int style )
    {
        ToolBarManager mgr = new ToolBarManager( style );
        mgr.add( new CreateConnectionWizardAction() );
        return mgr;
    }

    /**
     * Setter for the action manager
     * 
     * @param actionManager
     *            the action manager
     */
    public void setActionManager( IActionManager actionManager )
    {
        mActionManager_ = actionManager;
    }

}
