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

    private final String mStateTransitionName_;
    private final IState mStartingState_;
    private final IState mEndingState_;
    private List<IStateTransitionEvent> mTransitionEvents_ = new ArrayList<IStateTransitionEvent>();
    private List<IStateTransitionGuard> mTransitionGuards_ = new ArrayList<IStateTransitionGuard>();

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
        mStateTransitionName_ = aStateTransitionName;
        mStartingState_ = aStartingState;
        mEndingState_ = aEndingState;
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
                                             + mStateTransitionName_ + "]" );
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
        if ( mTransitionEvents_ == null || mTransitionEvents_.size() == 0 )
        {
            return false;
        }
        return true;
    }

    private boolean isOneContextEventValidForTransition( IStateMachineContext aContext )
    {
        for ( IStateTransitionEvent event : mTransitionEvents_ )
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
        if ( mTransitionGuards_ == null || mTransitionGuards_.size() == 0 )
        {
            return false;
        }
        return true;
    }

    private boolean areAllGuardsValidForTransition( IStateMachineContext aContext )
    {
        for ( IStateTransitionGuard g : mTransitionGuards_ )
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
        return mStartingState_;
    }

    /**
     * @see org.suggs.libs.statemachine.IStateTransition#getEndingState()
     */
    @Override
    public IState getEndingState()
    {
        return mEndingState_;
    }

    /**
     * @see org.suggs.libs.statemachine.IStateTransition#getTransitionName()
     */
    @Override
    public String getTransitionName()
    {
        return mStateTransitionName_;
    }

    /**
     * Setter for the transition events
     * 
     * @param aListOfEvents
     *            the list of transition events
     */
    public void setTransitionEvents( List<IStateTransitionEvent> aListOfEvents )
    {
        mTransitionEvents_ = aListOfEvents;
    }

    /**
     * Setter for a single transition event
     * 
     * @param aEvent
     *            the transition event to add to the transition
     */
    public void addTransitionEvent( IStateTransitionEvent aEvent )
    {
        mTransitionEvents_.add( aEvent );
    }

    /**
     * Setter for the transition guards
     * 
     * @param aListOfGuards
     *            the list of transition guards
     */
    public void setTransitionGuards( List<IStateTransitionGuard> aListOfGuards )
    {
        mTransitionGuards_ = aListOfGuards;
    }

    /**
     * Setter for a single transition guard
     * 
     * @param aGuard
     *            the transition guard to add to the transition
     */
    public void addTransitionGuard( IStateTransitionGuard aGuard )
    {
        mTransitionGuards_.add( aGuard );
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
        if ( mEndingState_ == null )
        {
            if ( other.mEndingState_ != null )
            {
                return false;
            }
        }
        else if ( !mEndingState_.equals( other.mEndingState_ ) )
        {
            return false;
        }
        if ( mStartingState_ == null )
        {
            if ( other.mStartingState_ != null )
            {
                return false;
            }
        }
        else if ( !mStartingState_.equals( other.mStartingState_ ) )
        {
            return false;
        }
        if ( mStateTransitionName_ == null )
        {
            if ( other.mStateTransitionName_ != null )
            {
                return false;
            }
        }
        else if ( !mStateTransitionName_.equals( other.mStateTransitionName_ ) )
        {
            return false;
        }
        if ( mTransitionEvents_ == null )
        {
            if ( other.mTransitionEvents_ != null )
            {
                return false;
            }
        }
        else if ( !mTransitionEvents_.equals( other.mTransitionEvents_ ) )
        {
            return false;
        }
        if ( mTransitionGuards_ == null )
        {
            if ( other.mTransitionGuards_ != null )
            {
                return false;
            }
        }
        else if ( !mTransitionGuards_.equals( other.mTransitionGuards_ ) )
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
        result = prime * result + ( ( mEndingState_ == null ) ? 0 : mEndingState_.hashCode() );
        result = prime * result + ( ( mStartingState_ == null ) ? 0 : mStartingState_.hashCode() );
        result = prime * result
                 + ( ( mStateTransitionName_ == null ) ? 0 : mStateTransitionName_.hashCode() );
        result = prime * result
                 + ( ( mTransitionEvents_ == null ) ? 0 : mTransitionEvents_.hashCode() );
        result = prime * result
                 + ( ( mTransitionGuards_ == null ) ? 0 : mTransitionGuards_.hashCode() );
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
            .append( mStateTransitionName_ )
            .append( "], startingState=[" )
            .append( mStartingState_ )
            .append( "], endingState=[" )
            .append( mEndingState_ )
            .append( "]" );
        return buff.toString();
    }

}
