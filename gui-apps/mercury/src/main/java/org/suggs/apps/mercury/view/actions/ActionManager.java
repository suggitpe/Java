/*
 * ActionManager.java created on 16 Sep 2008 06:52:27 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.actions;

import org.suggs.apps.mercury.view.IActionManager;

import java.util.Map;

import org.eclipse.jface.action.IAction;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * This class is used to manage the configured actions that are available for use by the Mercury application.
 * 
 * @author suggitpe
 * @version 1.0 16 Sep 2008
 */
public class ActionManager implements InitializingBean, IActionManager {

    private Map<String, IAction> actionMap;
    public static final String BEAN_NAME = "actionManager";

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() {
        Assert.notNull( actionMap,
                        "Action Map has not been initialised in the ActionManager.  Please update Spring XML." );
    }

    /**
     * Setter for the action map
     * 
     * @param aMap
     *            the map of actions to set
     */
    public void setActionMap( Map<String, IAction> aMap ) {
        actionMap = aMap;
    }

    /**
     * Getter for the actions in the action map
     * 
     * @param actionName
     *            the name of the action
     * @return the action that corresponds to that name
     */
    @Override
    public IAction getAction( String actionName ) {
        IAction ret = actionMap.get( actionName );
        if ( ret == null ) {
            throw new IllegalStateException( "No action found for name " + actionName );
        }
        return ret;
    }

}
