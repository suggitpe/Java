/*
 * StateMachineSteps.java created on 7 Sep 2010 18:31:15 by suggitpe for project state-machine-lib
 * 
 */
package org.suggs.libs.statemachine.jbehave.steps;

import org.suggs.libs.statemachine.StateMachine;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Steps implementation for the State Machine tests.
 * 
 * @author suggitpe
 * @version 1.0 7 Sep 2010
 */
public class StateMachineSteps {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( StateMachineSteps.class );

    @Autowired
    private StateMachine stateMachine;

    /**
     * Sets the stateMachine field to the specified value.
     * 
     * @param aStateMachine
     *            The stateMachine to set.
     */
    public void setStateMachine( StateMachine aStateMachine ) {
        stateMachine = aStateMachine;
    }

    /**
     * Returns the value of stateMachine.
     * 
     * @return Returns the stateMachine.
     */
    public StateMachine getStateMachine() {
        return stateMachine;
    }
}
