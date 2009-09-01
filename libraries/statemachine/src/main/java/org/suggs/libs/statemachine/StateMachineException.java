/*
 * StateMachineException.java created on 31 Aug 2009 18:11:48 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine;

/**
 * Exception to be used within the context of the state machine
 * library.
 * 
 * @author suggitpe
 * @version 1.0 31 Aug 2009
 */
public class StateMachineException extends Exception
{

    /**
     * Constructs a new instance.
     */
    public StateMachineException()
    {
        super();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aMsg
     *            the exception message
     */
    public StateMachineException( String aMsg )
    {
        super( aMsg );
    }

    /**
     * Constructs a new instance.
     * 
     * @param aException
     *            an exception to nest
     */
    public StateMachineException( Throwable aException )
    {
        super( aException );
    }

    /**
     * Constructs a new instance.
     * 
     * @param aMsg
     *            the message of the exception
     * @param aException
     *            a nested exception to embed in this exception
     */
    public StateMachineException( String aMsg, Throwable aException )
    {
        super( aMsg, aException );
    }
}
