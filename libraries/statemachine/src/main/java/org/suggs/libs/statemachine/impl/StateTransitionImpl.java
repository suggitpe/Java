/*
 * StateTransitionImpl.java created on 1 Sep 2009 07:20:35 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine.impl;

import org.suggs.libs.statemachine.State;
import org.suggs.libs.statemachine.StateMachineContext;
import org.suggs.libs.statemachine.StateTransition;
import org.suggs.libs.statemachine.StateTransitionEvent;
import org.suggs.libs.statemachine.StateTransitionGuard;
import org.suggs.libs.statemachine.StateMachineException;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The purpose of this class is to encapsulate a transition between two states. It understands that it has a
 * relationship between two states (start and end) and contains references to the logical evaluation (through
 * events and guard conditions) of the validation for transitioning between those two states.
 * 
 * @author suggitpe
 * @version 1.0 1 Sep 2009
 */
public class StateTransitionImpl implements StateTransition {

    private static final Log LOG = LogFactory.getLog( StateTransitionImpl.class );

    private final String stateTransitionName;
    private final State startingState;
    private final State endingState;
    private List<StateTransitionEvent> transitionEvents = new ArrayList<StateTransitionEvent>();
    private List<StateTransitionGuard> transitionGuards = new ArrayList<StateTransitionGuard>();

    /**
     * Constructs a new instance.
     * 
     * @param aStateTransitionName
     *            the name of the state transition
     * @param aStartingState
     *            the state that the start of the transition
     * @param aEndingState
     *            the state at the end of the transition
     */
    public StateTransitionImpl( String aStateTransitionName, State aStartingState, State aEndingState ) {
        super();
        stateTransitionName = aStateTransitionName;
        startingState = aStartingState;
        endingState = aEndingState;
    }

    /**
     * @see org.suggs.libs.statemachine.StateTransition#evaluateTransitionValidity(org.suggs.libs.statemachine.StateMachineContext)
     */
    @Override
    public boolean evaluateTransitionValidity( StateMachineContext aContext ) throws StateMachineException {
        if ( aContext == null ) {
            throw new StateMachineException( "Null context passed into the transition evaluation for transition["
                                             + stateTransitionName + "]" );
        }

        return ( isTransitionEventValid( aContext ) && areAllTransitionGuardsValid( aContext ) );
    }

    private boolean isTransitionEventValid( StateMachineContext aContext ) {
        if ( !areTransitionEventsSet() ) {
            return true;
        }

        return isOneContextEventValidForTransition( aContext );
    }

    private boolean areTransitionEventsSet() {
        if ( transitionEvents == null || transitionEvents.size() == 0 ) {
            return false;
        }
        return true;
    }

    private boolean isOneContextEventValidForTransition( StateMachineContext aContext ) {
        for ( StateTransitionEvent event : transitionEvents ) {
            if ( aContext.getStateTransitionEvent().equals( event ) ) {
                if ( LOG.isDebugEnabled() ) {
                    LOG.debug( "Transition event [" + event + "] is found on [" + this + "]" );
                }
                return true;
            }
        }
        if ( LOG.isDebugEnabled() ) {
            LOG.debug( "No valid transition events found on [" + this + "] to match the context [" + aContext
                       + "]" );
        }
        return false;
    }

    private boolean areAllTransitionGuardsValid( StateMachineContext aContext ) {
        if ( !areTransitionGuardsSet() ) {
            return true;
        }

        return ( areAllGuardsValidForTransition( aContext ) );
    }

    private boolean areTransitionGuardsSet() {
        if ( transitionGuards == null || transitionGuards.size() == 0 ) {
            return false;
        }
        return true;
    }

    private boolean areAllGuardsValidForTransition( StateMachineContext aContext ) {
        for ( StateTransitionGuard g : transitionGuards ) {
            if ( !g.evaluateGuard( aContext ) ) {
                return false;
            }
        }
        return true;
    }

    /**
     * @see org.suggs.libs.statemachine.StateTransition#getStartingState()
     */
    @Override
    public State getStartingState() {
        return new StateImpl( startingState );
    }

    /**
     * @see org.suggs.libs.statemachine.StateTransition#getEndingState()
     */
    @Override
    public State getEndingState() {
        return new StateImpl( endingState );
    }

    /**
     * @see org.suggs.libs.statemachine.StateTransition#getTransitionName()
     */
    @Override
    public String getTransitionName() {
        return stateTransitionName;
    }

    /**
     * Setter for the transition events
     * 
     * @param aListOfEvents
     *            the list of transition events
     */
    public void setTransitionEvents( List<StateTransitionEvent> aListOfEvents ) {
        transitionEvents = aListOfEvents;
    }

    /**
     * Setter for a single transition event
     * 
     * @param aEvent
     *            the transition event to add to the transition
     */
    public void addTransitionEvent( StateTransitionEvent aEvent ) {
        transitionEvents.add( aEvent );
    }

    /**
     * Setter for the transition guards
     * 
     * @param aListOfGuards
     *            the list of transition guards
     */
    public void setTransitionGuards( List<StateTransitionGuard> aListOfGuards ) {
        transitionGuards = aListOfGuards;
    }

    /**
     * Setter for a single transition guard
     * 
     * @param aGuard
     *            the transition guard to add to the transition
     */
    public void addTransitionGuard( StateTransitionGuard aGuard ) {
        transitionGuards.add( aGuard );
    }

    /**
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

        StateTransitionImpl other = (StateTransitionImpl) obj;
        if ( endingState == null ) {
            if ( other.endingState != null ) {
                return false;
            }
        }
        else if ( !endingState.equals( other.endingState ) ) {
            return false;
        }

        if ( startingState == null ) {
            if ( other.startingState != null ) {
                return false;
            }
        }
        else if ( !startingState.equals( other.startingState ) ) {
            return false;
        }

        if ( stateTransitionName == null ) {
            if ( other.stateTransitionName != null ) {
                return false;
            }
        }
        else if ( !stateTransitionName.equals( other.stateTransitionName ) ) {
            return false;
        }

        if ( transitionEvents == null ) {
            if ( other.transitionEvents != null ) {
                return false;
            }
        }
        else if ( !transitionEvents.equals( other.transitionEvents ) ) {
            return false;
        }

        if ( transitionGuards == null ) {
            if ( other.transitionGuards != null ) {
                return false;
            }
        }
        else if ( !transitionGuards.equals( other.transitionGuards ) ) {
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
        result = prime * result + ( ( endingState == null ) ? 0 : endingState.hashCode() );
        result = prime * result + ( ( startingState == null ) ? 0 : startingState.hashCode() );
        result = prime * result + ( ( stateTransitionName == null ) ? 0 : stateTransitionName.hashCode() );
        result = prime * result + ( ( transitionEvents == null ) ? 0 : transitionEvents.hashCode() );
        result = prime * result + ( ( transitionGuards == null ) ? 0 : transitionGuards.hashCode() );
        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder buff = new StringBuilder( "StateTransitionImpl:" );
        buff.append( " stateTransitionName=[" )
            .append( stateTransitionName )
            .append( "], startingState=[" )
            .append( startingState )
            .append( "], endingState=[" )
            .append( endingState )
            .append( "]" );
        return buff.toString();
    }

}
