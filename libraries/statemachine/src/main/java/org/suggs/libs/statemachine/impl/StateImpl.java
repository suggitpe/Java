/*
 * StateImpl.java created on 28 Aug 2009 18:21:13 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine.impl;

import org.suggs.libs.statemachine.IState;
import org.suggs.libs.statemachine.IStateMachineContext;
import org.suggs.libs.statemachine.IStateTransition;
import org.suggs.libs.statemachine.StateMachineException;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This main purpose of this class is to act as the source of state
 * information of the state machine. It is also an associative class
 * in that it provides the link between the step execution at the
 * state machine level down into the association and evaluation of the
 * transitions.
 * 
 * @author suggitpe
 * @version 1.0 28 Aug 2009
 */
public class StateImpl implements IState
{

    private static final Log LOG = LogFactory.getLog( StateImpl.class );

    private final String mStateName_;
    private Collection<IStateTransition> mTransitions_ = new ArrayList<IStateTransition>();

    /**
     * Constructs a new instance.
     * 
     * @param aStateName
     *            the name of the state to initialise to
     */
    public StateImpl( String aStateName )
    {
        super();
        mStateName_ = aStateName;
    }

    /**
     * @see org.suggs.libs.statemachine.IState#getStateName()
     */
    @Override
    public String getStateName()
    {
        return mStateName_;
    }

    /**
     * This method will retrieve all of the transitions that are
     * associated with this state and will evaluate all of them. If
     * one (any only one) transition evaluation returns true, then we
     * return the end state of that transition, else we return the
     * current state to denote that no stepping should occur.
     * 
     * @see org.suggs.libs.statemachine.IState#step(org.suggs.libs.statemachine.IStateMachineContext)
     */
    @Override
    public IState step( IStateMachineContext aContext ) throws StateMachineException
    {
        if ( LOG.isDebugEnabled() )
        {
            LOG.debug( "Step called from state=[" + mStateName_ + "]" );
        }
        loadTransitionsIntoState();
        return evaluateTransitionsToNewState( aContext );
    }

    private void loadTransitionsIntoState()
    {
        if ( mTransitions_ == null || mTransitions_.size() == 0 )
        {
            mTransitions_ = StateTransitionManager.instance().getListOfTransitionsForState( this );
        }
    }

    private IState evaluateTransitionsToNewState( IStateMachineContext aContext )
                    throws StateMachineException
    {
        IStateTransition successfulTransition = null;
        for ( IStateTransition transInLoop : mTransitions_ )
        {
            if ( transInLoop.evaluateTransitionValidity( aContext ) )
            {
                if ( LOG.isInfoEnabled() )
                {
                    LOG.info( "State Transition [" + transInLoop + "] is valid" );
                }
                if ( successfulTransition != null )
                {
                    throw new StateMachineException( "More than one transition appears to be valid (only one should be valid for any given starting state)" );
                }
                successfulTransition = transInLoop;
            }
            else
            {
                if ( LOG.isInfoEnabled() )
                {
                    LOG.info( "State Transition [" + transInLoop + "] is not valid" );
                }
            }
        }

        return successfulTransition == null ? this : successfulTransition.getEndingState();
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
        StateImpl other = (StateImpl) obj;
        if ( mStateName_ == null )
        {
            if ( other.mStateName_ != null )
            {
                return false;
            }
        }
        else if ( !mStateName_.equals( other.mStateName_ ) )
        {
            return false;
        }
        if ( mTransitions_ == null )
        {
            if ( other.mTransitions_ != null )
            {
                return false;
            }
        }
        else if ( !mTransitions_.equals( other.mTransitions_ ) )
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
        result = prime * result + ( ( mStateName_ == null ) ? 0 : mStateName_.hashCode() );
        result = prime * result + ( ( mTransitions_ == null ) ? 0 : mTransitions_.hashCode() );
        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuilder buff = new StringBuilder( "StateImpl:" );
        buff.append( " stateName=[" ).append( mStateName_ ).append( "]" );
        return buff.toString();
    }

}
