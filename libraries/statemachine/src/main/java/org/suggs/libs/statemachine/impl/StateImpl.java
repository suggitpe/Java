/*
 * StateImpl.java created on 28 Aug 2009 18:21:13 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine.impl;

import org.suggs.libs.statemachine.Action;
import org.suggs.libs.statemachine.State;
import org.suggs.libs.statemachine.StateMachineContext;
import org.suggs.libs.statemachine.StateTransition;
import org.suggs.libs.statemachine.StateMachineException;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This main purpose of this class is to act as the source of state information of the state machine. It is
 * also an associative class in that it provides the link between the step execution at the state machine
 * level down into the association and evaluation of the transitions.
 * 
 * @author suggitpe
 * @version 1.0 28 Aug 2009
 */
public class StateImpl implements State {

    private static final Log LOG = LogFactory.getLog( StateImpl.class );

    private final String stateName;
    private Action entryAction;
    private Action exitAction;
    private Collection<StateTransition> transitions = new ArrayList<StateTransition>();

    /**
     * Constructs a new instance.
     * 
     * @param aStateName
     *            the name of the state to initialise to
     */
    public StateImpl( String aStateName ) {
        super();
        stateName = aStateName;
    }

    /**
     * Constructs a new instance from another one.
     * 
     * @param aState
     *            the state from which to build the new one.
     */
    public StateImpl( State aState ) {
        super();
        stateName = aState.getStateName();
    }

    /**
     * @see org.suggs.libs.statemachine.State#getStateName()
     */
    @Override
    public String getStateName() {
        return stateName;
    }

    /**
     * Setter for the entry action
     * 
     * @param aAction
     *            the entry action
     */
    public void setEntryAction( Action aAction ) {
        entryAction = aAction;
    }

    /**
     * Setter for the Exit action
     * 
     * @param aAction
     *            the exit action
     */
    public void setExitAction( Action aAction ) {
        exitAction = aAction;
    }

    /**
     * This method will retrieve all of the transitions that are associated with this state and will evaluate
     * all of them. If one (any only one) transition evaluation returns true, then we return the end state of
     * that transition, else we return the current state to denote that no stepping should occur.
     * 
     * @see org.suggs.libs.statemachine.State#step(org.suggs.libs.statemachine.StateMachineContext)
     */
    @Override
    public State step( StateMachineContext aContext ) throws StateMachineException {
        if ( LOG.isDebugEnabled() ) {
            LOG.debug( "Step called from state=[" + stateName + "]" );
        }
        loadTransitionsIntoState();
        return evaluateTransitionsToNewState( aContext );
    }

    private void loadTransitionsIntoState() {
        if ( transitions == null || transitions.size() == 0 ) {
            transitions = StateTransitionManager.instance().getListOfTransitionsForState( this );
        }
    }

    private State evaluateTransitionsToNewState( StateMachineContext aContext )
                    throws StateMachineException {
        StateTransition successfulTransition = null;
        for ( StateTransition transInLoop : transitions ) {
            if ( transInLoop.evaluateTransitionValidity( aContext ) ) {
                if ( LOG.isInfoEnabled() ) {
                    LOG.info( "State Transition [" + transInLoop + "] is valid" );
                }
                if ( successfulTransition != null ) {
                    throw new StateMachineException( "More than one transition appears to be valid (only one should be valid for any given starting state)" );
                }
                successfulTransition = transInLoop;
            }
            else {
                if ( LOG.isInfoEnabled() ) {
                    LOG.info( "State Transition [" + transInLoop + "] is not valid" );
                }
            }
        }

        return successfulTransition == null ? this : successfulTransition.getEndingState();
    }

    /**
     * @see org.suggs.libs.statemachine.State#executeEntryAction(org.suggs.libs.statemachine.StateMachineContext)
     */
    @Override
    public void executeEntryAction( StateMachineContext aContext ) throws StateMachineException {
        if ( entryAction != null ) {
            if ( LOG.isDebugEnabled() ) {
                LOG.debug( "Executing entry action:" + entryAction );
            }
            entryAction.execute( aContext );
        }
    }

    /**
     * @see org.suggs.libs.statemachine.State#executeExitAction(org.suggs.libs.statemachine.StateMachineContext)
     */
    @Override
    public void executeExitAction( StateMachineContext aContext ) throws StateMachineException {
        if ( exitAction != null ) {
            if ( LOG.isDebugEnabled() ) {
                LOG.debug( "Executing exit action:" + exitAction );
            }
            exitAction.execute( aContext );
        }
    }

    /**
     * This impl ONLY uses the state name for equals comparisons.
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }

        StateImpl other = (StateImpl) obj;
        if ( stateName == null ) {
            if ( other.stateName != null ) {
                return false;
            }
        }
        else if ( !stateName.equals( other.stateName ) ) {
            return false;
        }

        if ( entryAction == null && other.entryAction != null ) {
            return false;
        }

        if ( exitAction == null && other.exitAction != null ) {
            return false;
        }

        if ( transitions == null && other.transitions != null ) {
            return false;
        }

        return true;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( stateName == null ) ? 0 : stateName.hashCode() );
        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder buff = new StringBuilder( "StateImpl:" );
        buff.append( " stateName=[" ).append( stateName ).append( "]" );
        return buff.toString();
    }

}
