/*
 * State.java created on 10 Aug 2009 19:31:19 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.state.connection.impl;

import org.suggs.sandbox.patterns.behavioural.state.connection.IState;
import org.suggs.sandbox.patterns.behavioural.state.connection.IStateMachineEventContext;
import org.suggs.sandbox.patterns.behavioural.state.connection.ITransition;
import org.suggs.sandbox.patterns.behavioural.state.connection.StateMachineException;
import org.suggs.sandbox.patterns.behavioural.state.connection.TransitionManager;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.InitializingBean;

/**
 * Class used to encapsulate a the state of the context. It is used by the state machine to derive meaning to
 * the current state of a state machine context.
 * 
 * @author suggitpe
 * @version 1.0 10 Aug 2009
 */
public class State implements IState, InitializingBean {

    private static final Log LOG = LogFactory.getLog( State.class );

    private String stateName;
    private List<ITransition> transitions;

    /**
     * Constructs a new instance.
     * 
     * @param aStateName
     *            the name of the state
     */
    public State( String aStateName ) {
        super();
        stateName = aStateName;
    }

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() {}

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object aRhs ) {
        if ( aRhs == null || this != aRhs ) {
            return false;
        }

        if ( aRhs instanceof State && getClass() == aRhs.getClass() && super.equals( aRhs ) ) {
            State rhs = (State) aRhs;
            if ( stateName.equals( rhs.stateName ) ) {
                return true;
            }
        }
        return false;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return stateName.hashCode();
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.connection.IState#step(org.suggs.sandbox.patterns.behavioural.state.connection.IStateMachineEventContext)
     */
    public IState step( IStateMachineEventContext aContext ) {
        loadTransitionsIntoState();
        try {
            return evaluateTransitionsToNewState( aContext );
        }
        catch ( StateMachineException ex ) {
            LOG.warn( "Failed to evaluate the transitions due to [" + ex.getMessage() + "]" );
            return null;
        }
    }

    private void loadTransitionsIntoState() {
        if ( transitions == null ) {
            transitions = TransitionManager.getInstance().getTranitionsForStartState( this );
        }
    }

    private IState evaluateTransitionsToNewState( IStateMachineEventContext aContext )
                    throws StateMachineException {
        LOG.debug( "Evaluating all of the [" + transitions.size() + "] transitions for the [" + getName()
                   + "] state" );

        ITransition successfullTransition = null;

        for ( ITransition transition : transitions ) {
            if ( transition.evaluateStateAgainstEventsAndGuards( aContext, this ) ) {
                LOG.info( "Transition [" + transition.getName() + "] is valid" );
                if ( successfullTransition != null ) {
                    throw new StateMachineException( "Evaluated that more than one transition is valid" );
                }

                successfullTransition = transition;
            }
            else {
                LOG.info( "Transition [" + transition.getName() + "] is not valid" );
            }
        }

        return successfullTransition != null ? successfullTransition.getEndState() : this;
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.connection.IState#getName()
     */
    @Override
    public String getName() {
        return stateName;
    }

}
