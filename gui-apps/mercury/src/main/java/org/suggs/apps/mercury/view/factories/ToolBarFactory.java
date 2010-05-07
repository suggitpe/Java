/*
 * ToolBarBuilder.java created on 16 Sep 2008 07:15:27 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.factories;

import org.suggs.apps.mercury.ContextProvider;
import org.suggs.apps.mercury.view.IActionManager;
import org.suggs.apps.mercury.view.IToolBarFactory;
import org.suggs.apps.mercury.view.actions.ActionManager;

import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * Builder class that is responsible for the contruction of the toolbars used in the application
 * 
 * @author suggitpe
 * @version 1.0 16 Sep 2008
 */
public class ToolBarFactory implements IToolBarFactory, InitializingBean {

    private static final Log LOG = LogFactory.getLog( ToolBarFactory.class );

    private IActionManager actionManager;
    private boolean showToolbar;

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() {
        Assert.notNull( actionManager,
                        "An action manager object must be injected into the ToolBarFactory object.  Please revise Spring XML" );
    }

    /**
     * @see org.suggs.apps.mercury.view.IToolBarFactory#createToolbar(java.lang.String, int)
     */
    public ToolBarManager createToolbar( String toolBarType, int style ) {
        if ( LOG.isDebugEnabled() ) {
            LOG.debug( "Constructing toolbar manager for type [" + toolBarType + "]" );
        }

        if ( !showToolbar ) {
            return null;
        }

        if ( toolBarType.equals( "MAIN" ) ) {
            return buildMainToolbar( style );
        }
        throw new NotImplementedException( "No toolbar manager exists for type [" + toolBarType + "]" );
    }

    /**
     * This method wil build the toolbar manager for the main screen
     * 
     * @param style
     *            the style of the toolbar
     * @return the toolbar manager
     */
    private ToolBarManager buildMainToolbar( int style ) {
        ToolBarManager main = new ToolBarManager( style );

        // get action manager factory
        ActionManager mgr = (ActionManager) ContextProvider.instance().getBean( ActionManager.BEAN_NAME );

        main.add( mgr.getAction( "CREATE_CONNECTION" ) );
        main.add( mgr.getAction( "EDIT_CONNECTION" ) );
        main.add( mgr.getAction( "REMOVE_CONNECTION" ) );

        main.add( new Separator() );

        return main;
    }

    /**
     * Setter for the action manager
     * 
     * @param aActionManager
     *            the action manager
     */
    public final void setActionManager( IActionManager aActionManager ) {
        actionManager = aActionManager;
    }

    /**
     * Setter for showing the toolbar
     * 
     * @param aShowToolbar
     *            flag to set the toolbar switch
     */
    public final void setShowToolbar( boolean aShowToolbar ) {
        showToolbar = aShowToolbar;
    }

}
