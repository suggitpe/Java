/*
 * StateTransitionImpl.java created on 1 Sep 2009 07:20:35 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine.impl;

import org.suggs.libs.statemachine.IState;
import org.suggs.libs.statemachine.IStateMachineContext;
import org.suggs.libs.statemachine.IStateTransition;
import org.suggs.libs.statemachine.IStateTransitionEvent;
import org.suggs.libs.statemachine.IStateTransitionGuard;
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
public class StateTransitionImpl implements IStateTransition {

    private static final Log LOG = LogFactory.getLog( StateTransitionImpl.class );

    private final String stateTransitionName;
    private final IState startingState;
    private final IState endingState;
    private List<IStateTransitionEvent> transitionEvents = new ArrayList<IStateTransitionEvent>();
    private List<IStateTransitionGuard> transitionGuards = new ArrayList<IStateTransitionGuard>();

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
    public StateTransitionImpl( String aStateTransitionName, IState aStartingState, IState aEndingState ) {
        super();
        stateTransitionName = aStateTransitionName;
        startingState = aStartingState;
        endingState = aEndingState;
    }

    /**
     * @see org.suggs.libs.statemachine.IStateTransition#evaluateTransitionValidity(org.suggs.libs.statemachine.IStateMachineContext)
     */
    @Override
    public boolean evaluateTransitionValidity( IStateMachineContext aContext ) throws StateMachineException {
        if ( aContext == null ) {
            throw new StateMachineException( "Null context passed into the transition evaluation for transition["
                                             + stateTransitionName + "]" );
        }

        return ( isTransitionEventValid( aContext ) && areAllTransitionGuardsValid( aContext ) );
    }

    private boolean isTransitionEventValid( IStateMachineContext aContext ) {
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

    private boolean isOneContextEventValidForTransition( IStateMachineContext aContext ) {
        for ( IStateTransitionEvent event : transitionEvents ) {
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

    private boolean areAllTransitionGuardsValid( IStateMachineContext aContext ) {
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

    private boolean areAllGuardsValidForTransition( IStateMachineContext aContext ) {
        for ( IStateTransitionGuard g : transitionGuards ) {
            if ( !g.evaluateGuard( aContext ) ) {
                return false;
            }
        }
        return true;
    }

    /**
     * @see org.suggs.libs.statemachine.IStateTransition#getStartingState()
     */
    @Override
    public IState getStartingState() {
        return new StateImpl( startingState );
    }

    /**
     * @see org.suggs.libs.statemachine.IStateTransition#getEndingState()
     */
    @Override
    public IState getEndingState() {
        return new StateImpl( endingState );
    }

    /**
     * @see org.suggs.libs.statemachine.IStateTransition#getTransitionName()
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
    public void setTransitionEvents( List<IStateTransitionEvent> aListOfEvents ) {
        transitionEvents = aListOfEvents;
    }

    /**
     * Setter for a single transition event
     * 
     * @param aEvent
     *            the transition event to add to the transition
     */
    public void addTransitionEvent( IStateTransitionEvent aEvent ) {
        transitionEvents.add( aEvent );
    }

    /**
     * Setter for the transition guards
     * 
     * @param aListOfGuards
     *            the list of transition guards
     */
    public void setTransitionGuards( List<IStateTransitionGuard> aListOfGuards ) {
        transitionGuards = aListOfGuards;
    }

    /**
     * Setter for a single transition guard
     * 
     * @param aGuard
     *            the transition guard to add to the transition
     */
    public void addTransitionGuard( IStateTransitionGuard aGuard ) {
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
