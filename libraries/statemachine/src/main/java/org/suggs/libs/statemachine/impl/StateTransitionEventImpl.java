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

    private final String eventName_;

    /**
     * Constructs a new instance.
     * 
     * @param aEventName
     *            the name of the event
     */
    public StateTransitionEventImpl( String aEventName )
    {
        super();
        eventName_ = aEventName;
    }

    /**
     * @see org.suggs.libs.statemachine.IStateTransitionEvent#getEventName()
     */
    @Override
    public String getEventName()
    {
        return eventName_;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuilder buff = new StringBuilder( "StateTransitionEventImpl:" );
        buff.append( " eventName=[" ).append( eventName_ ).append( "]" );
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
        if ( eventName_ == null )
        {
            if ( other.eventName_ != null )
            {
                return false;
            }
        }
        else if ( !eventName_.equals( other.eventName_ ) )
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
        result = prime * result + ( ( eventName_ == null ) ? 0 : eventName_.hashCode() );
        return result;
    }
}
