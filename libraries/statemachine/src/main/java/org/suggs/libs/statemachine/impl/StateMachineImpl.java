/*
 * StateMachineImpl.java created on 24 Aug 2009 06:53:39 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine.impl;

import org.suggs.libs.statemachine.IState;
import org.suggs.libs.statemachine.IStateMachine;
import org.suggs.libs.statemachine.IStateMachineContext;
import org.suggs.libs.statemachine.StateMachineException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Implementation of the IStateMachine interface. This implementation
 * will delegate all transition evaluation to the underlying current
 * state. Thus the core responsibility of this class is to manage the
 * overall state of the state machine.
 * 
 * @author suggitpe
 * @version 1.0 24 Aug 2009
 */
public class StateMachineImpl implements IStateMachine
{

    private static final Log LOG = LogFactory.getLog( StateMachineImpl.class );

    private IState mCurrentState_;

    /**
     * Constructs a new instance.
     * 
     * @param aInitialState
     *            an initial state for the state machine
     */
    public StateMachineImpl( IState aInitialState )
    {
        super();
        mCurrentState_ = aInitialState;
    }

    /**
     * This method delegates down to the underlying current state to
     * perform the state transition evaluation. If we get a new state
     * back from that delegating call, then we update our internal
     * state to reflect this.
     * 
     * @see org.suggs.libs.statemachine.IStateMachine#step(org.suggs.libs.statemachine.IStateMachineContext)
     */
    @Override
    public void step( IStateMachineContext aContext ) throws StateMachineException
    {
        IState newState = mCurrentState_.step( aContext );
        if ( newState == null || mCurrentState_.equals( newState ) )
        {
            if ( LOG.isInfoEnabled() )
            {
                LOG.info( "No valid transitions found from state=[" + mCurrentState_
                          + "], state remain unchanged." );
            }
        }
        else
        {
            if ( LOG.isInfoEnabled() )
            {
                LOG.info( "Transitioning state machine to new state=[" + newState + "]" );
            }
            mCurrentState_ = newState;
            // this may look odd: we need to call step again when we
            // reach a new state to allow for transitory states within
            // the overall state machine.
            this.step( aContext );
        }
    }

    /**
     * @see org.suggs.libs.statemachine.IStateMachine#getCurrentState()
     */
    @Override
    public IState getCurrentState()
    {
        return mCurrentState_;
    }

    /**
     * Setter for the current state
     * 
     * @param aState
     *            the state to set as the current state
     */
    public void setCurrentState( IState aState )
    {
        mCurrentState_ = aState;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuilder buff = new StringBuilder( "StateMachineImpl:" );
        buff.append( " currentState=[" ).append( mCurrentState_ ).append( "]" );
        return buff.toString();
    }
}
