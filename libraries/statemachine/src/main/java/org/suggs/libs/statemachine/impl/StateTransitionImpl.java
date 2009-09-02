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

import org.springframework.beans.factory.InitializingBean;

/**
 * TODO Write javadoc for StateTransitionImpl
 * 
 * @author suggitpe
 * @version 1.0 1 Sep 2009
 */
public class StateTransitionImpl implements IStateTransition, InitializingBean
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
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception
    {
    }

    /**
     * @see org.suggs.libs.statemachine.IStateTransition#evaluateTransitionValidity(org.suggs.libs.statemachine.IStateMachineContext)
     */
    @Override
    public boolean evaluateTransitionValidity( IStateMachineContext aContext )
                    throws StateMachineException
    {
        return false;
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
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object aRhs )
    {

        if ( aRhs == null )
        {
            return false;
        }

        if ( this == aRhs )
        {
            return true;
        }

        // if this class extends any other classes then we need to
        // call super.equals(aRhs), else don't
        if ( aRhs instanceof StateTransitionImpl && getClass() == aRhs.getClass() )
        {
            StateTransitionImpl rhs = (StateTransitionImpl) aRhs;
            if ( mStateTransitionName_.equals( rhs.mStateTransitionName_ )
                 && mStartingState_.equals( rhs.mStartingState_ )
                 && mEndingState_.equals( rhs.mEndingState_ )
                 && mTransitionEvents_.size() == rhs.mTransitionEvents_.size()
                 && mTransitionGuards_.size() == rhs.mTransitionGuards_.size() )
            {
                return true;
            }
        }
        return false;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        return mStateTransitionName_.hashCode() + mStartingState_.hashCode()
               + mEndingState_.hashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuffer buff = new StringBuffer( "StateTransitionImpl:" );
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
