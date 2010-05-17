/*
 * StateMachine.java created on 10 Aug 2009 19:42:23 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.state.connection.impl;

import org.suggs.sandbox.patterns.behavioural.state.connection.IState;
import org.suggs.sandbox.patterns.behavioural.state.connection.IStateMachine;
import org.suggs.sandbox.patterns.behavioural.state.connection.IStateMachineEventContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * This class is responsible for allowing a client to step through the states in the state machine.
 * 
 * @author suggitpe
 * @version 1.0 10 Aug 2009
 */
public class StateMachine implements IStateMachine, InitializingBean {

    // static logger
    private static final Log LOG = LogFactory.getLog( StateMachine.class );
    private IState currentState;

    /**
     * Constructs a new instance.
     * 
     * @param aState
     *            the initial state for the state machine
     */
    public StateMachine( IState aState ) {
        LOG.debug( "Creating new state machine" );
        currentState = aState;
    }

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull( currentState, "Current state for the state manchine must be set upon construction" );
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.connection.IStateMachine#getState()
     */
    @Override
    public IState getState() {
        return currentState;
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.connection.IStateMachine#step(org.suggs.sandbox.patterns.behavioural.state.connection.IStateMachineEventContext)
     */
    @Override
    public void step( IStateMachineEventContext aContext ) {
        IState newState = currentState.step( aContext );
        if ( newState != null && newState != currentState ) {
            LOG.info( "Transitioning state machine to new state of [" + newState.getName() + "]" );
            currentState = newState;
            // this recursive call allows us to work through transient
            // states where there are valid transitions upon entering
            // that new state (e.g. transient states).
            this.step( aContext );
        }
        else {
            LOG.info( "No valid transitions found from [" + currentState.getName() + "]" );
        }
    }

}
