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
     * 
     * @param aMsg
     *            the exception message
     */
    public StateMachineException( String aMsg )
    {
        super( aMsg );
    }

}
