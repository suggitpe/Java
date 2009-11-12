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
 * The purpose of this class is to encapsulate a transition between
 * two states. It understands that it has a relationship between two
 * states (start and end) and contains references to the logical
 * evaluation (through events and guard conditions) of the validation
 * for transitioning between those two states.
 * 
 * @author suggitpe
 * @version 1.0 1 Sep 2009
 */
public class StateTransitionImpl implements IStateTransition
{

    private static final Log LOG = LogFactory.getLog( StateTransitionImpl.class );

    private final String stateTransitionName_;
    private final IState startingState_;
    private final IState endingState_;
    private List<IStateTransitionEvent> transitionEvents_ = new ArrayList<IStateTransitionEvent>();
    private List<IStateTransitionGuard> transitionGuards_ = new ArrayList<IStateTransitionGuard>();

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
    public StateTransitionImpl( String aStateTransitionName, IState aStartingState,
                                IState aEndingState )
    {
        super();
        stateTransitionName_ = aStateTransitionName;
        startingState_ = aStartingState;
        endingState_ = aEndingState;
    }

    /**
     * @see org.suggs.libs.statemachine.IStateTransition#evaluateTransitionValidity(org.suggs.libs.statemachine.IStateMachineContext)
     */
    @Override
    public boolean evaluateTransitionValidity( IStateMachineContext aContext )
                    throws StateMachineException
    {
        if ( aContext == null )
        {
            throw new StateMachineException( "Null context passed into the transition evaluation for transition["
                                             + stateTransitionName_ + "]" );
        }

        return ( isTransitionEventValid( aContext ) && areAllTransitionGuardsValid( aContext ) );
    }

    private boolean isTransitionEventValid( IStateMachineContext aContext )
    {
        if ( !areTransitionEventsSet() )
        {
            return true;
        }

        return isOneContextEventValidForTransition( aContext );
    }

    private boolean areTransitionEventsSet()
    {
        if ( transitionEvents_ == null || transitionEvents_.size() == 0 )
        {
            return false;
        }
        return true;
    }

    private boolean isOneContextEventValidForTransition( IStateMachineContext aContext )
    {
        for ( IStateTransitionEvent event : transitionEvents_ )
        {
            if ( aContext.getStateTransitionEvent().equals( event ) )
            {
                if ( LOG.isDebugEnabled() )
                {
                    LOG.debug( "Transition event [" + event + "] is found on [" + this + "]" );
                }
                return true;
            }
        }
        if ( LOG.isDebugEnabled() )
        {
            LOG.debug( "No valid transition events found on [" + this + "] to match the context ["
                       + aContext + "]" );
        }
        return false;
    }

    private boolean areAllTransitionGuardsValid( IStateMachineContext aContext )
    {
        if ( !areTransitionGuardsSet() )
        {
            return true;
        }

        return ( areAllGuardsValidForTransition( aContext ) );
    }

    private boolean areTransitionGuardsSet()
    {
        if ( transitionGuards_ == null || transitionGuards_.size() == 0 )
        {
            return false;
        }
        return true;
    }

    private boolean areAllGuardsValidForTransition( IStateMachineContext aContext )
    {
        for ( IStateTransitionGuard g : transitionGuards_ )
        {
            if ( !g.evaluateGuard( aContext ) )
            {
                return false;
            }
        }
        return true;
    }

    /**
     * @see org.suggs.libs.statemachine.IStateTransition#getStartingState()
     */
    @Override
    public IState getStartingState()
    {
        return startingState_;
    }

    /**
     * @see org.suggs.libs.statemachine.IStateTransition#getEndingState()
     */
    @Override
    public IState getEndingState()
    {
        return endingState_;
    }

    /**
     * @see org.suggs.libs.statemachine.IStateTransition#getTransitionName()
     */
    @Override
    public String getTransitionName()
    {
        return stateTransitionName_;
    }

    /**
     * Setter for the transition events
     * 
     * @param aListOfEvents
     *            the list of transition events
     */
    public void setTransitionEvents( List<IStateTransitionEvent> aListOfEvents )
    {
        transitionEvents_ = aListOfEvents;
    }

    /**
     * Setter for a single transition event
     * 
     * @param aEvent
     *            the transition event to add to the transition
     */
    public void addTransitionEvent( IStateTransitionEvent aEvent )
    {
        transitionEvents_.add( aEvent );
    }

    /**
     * Setter for the transition guards
     * 
     * @param aListOfGuards
     *            the list of transition guards
     */
    public void setTransitionGuards( List<IStateTransitionGuard> aListOfGuards )
    {
        transitionGuards_ = aListOfGuards;
    }

    /**
     * Setter for a single transition guard
     * 
     * @param aGuard
     *            the transition guard to add to the transition
     */
    public void addTransitionGuard( IStateTransitionGuard aGuard )
    {
        transitionGuards_.add( aGuard );
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object obj )
    {
        if ( this == obj )
        {
            return true;
        }
        if ( obj == null )
        {
            return false;
        }
        if ( getClass() != obj.getClass() )
        {
            return false;
        }
        StateTransitionImpl other = (StateTransitionImpl) obj;
        if ( endingState_ == null )
        {
            if ( other.endingState_ != null )
            {
                return false;
            }
        }
        else if ( !endingState_.equals( other.endingState_ ) )
        {
            return false;
        }
        if ( startingState_ == null )
        {
            if ( other.startingState_ != null )
            {
                return false;
            }
        }
        else if ( !startingState_.equals( other.startingState_ ) )
        {
            return false;
        }
        if ( stateTransitionName_ == null )
        {
            if ( other.stateTransitionName_ != null )
            {
                return false;
            }
        }
        else if ( !stateTransitionName_.equals( other.stateTransitionName_ ) )
        {
            return false;
        }
        if ( transitionEvents_ == null )
        {
            if ( other.transitionEvents_ != null )
            {
                return false;
            }
        }
        else if ( !transitionEvents_.equals( other.transitionEvents_ ) )
        {
            return false;
        }
        if ( transitionGuards_ == null )
        {
            if ( other.transitionGuards_ != null )
            {
                return false;
            }
        }
        else if ( !transitionGuards_.equals( other.transitionGuards_ ) )
        {
            return false;
        }
        return true;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( endingState_ == null ) ? 0 : endingState_.hashCode() );
        result = prime * result + ( ( startingState_ == null ) ? 0 : startingState_.hashCode() );
        result = prime * result
                 + ( ( stateTransitionName_ == null ) ? 0 : stateTransitionName_.hashCode() );
        result = prime * result
                 + ( ( transitionEvents_ == null ) ? 0 : transitionEvents_.hashCode() );
        result = prime * result
                 + ( ( transitionGuards_ == null ) ? 0 : transitionGuards_.hashCode() );
        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuilder buff = new StringBuilder( "StateTransitionImpl:" );
        buff.append( " stateTransitionName=[" )
            .append( stateTransitionName_ )
            .append( "], startingState=[" )
            .append( startingState_ )
            .append( "], endingState=[" )
            .append( endingState_ )
            .append( "]" );
        return buff.toString();
    }

}
