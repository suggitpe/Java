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
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuilder buff = new StringBuilder( "StateTransitionEventImpl:" );
        buff.append( " eventName=[" ).append( mEventName_ ).append( "]" );
        return buff.toString();
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
        StateTransitionEventImpl other = (StateTransitionEventImpl) obj;
        if ( mEventName_ == null )
        {
            if ( other.mEventName_ != null )
            {
                return false;
            }
        }
        else if ( !mEventName_.equals( other.mEventName_ ) )
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
        result = prime * result + ( ( mEventName_ == null ) ? 0 : mEventName_.hashCode() );
        return result;
    }
}
