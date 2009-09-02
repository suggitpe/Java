/*
 * StateTransitionEventImpl.java created on 1 Sep 2009 19:13:44 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine.impl;

import org.suggs.libs.statemachine.IStateTransitionEvent;

/**
 * Simple class to encapsulate an event for a state transition.
 * 
 * @author suggitpe
 * @version 1.0 1 Sep 2009
 */
public class StateTransitionEventImpl implements IStateTransitionEvent
{

    private final String mEventName_;

    /**
     * Constructs a new instance.
     * 
     * @param aEventName
     *            the name of the event
     */
    public StateTransitionEventImpl( String aEventName )
    {
        super();
        mEventName_ = aEventName;
    }

    /**
     * @see org.suggs.libs.statemachine.IStateTransitionEvent#getEventName()
     */
    @Override
    public String getEventName()
    {
        return mEventName_;
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
        if ( aRhs instanceof StateTransitionEventImpl && getClass() == aRhs.getClass() )
        {
            StateTransitionEventImpl rhs = (StateTransitionEventImpl) aRhs;
            if ( mEventName_.equals( rhs.mEventName_ ) )
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
        return mEventName_.hashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuffer buff = new StringBuffer( "StateTransitionEventImpl:" );
        buff.append( " eventName=[" ).append( mEventName_ ).append( "]" );
        return buff.toString();
    }
}
