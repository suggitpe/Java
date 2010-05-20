/*
 * StateMachineException.java created on 18 Aug 2009 06:56:39 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.state.connection;

/**
 * Generic State Machine Exception class.
 * 
 * @author suggitpe
 * @version 1.0 18 Aug 2009
 */
public class StateMachineException extends Exception {

    private static final long serialVersionUID = -4487527091340053649L;

    /**
     * Constructs a new instance.
     */
    public StateMachineException() {
        super();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aMsg
     *            the exception message
     */
    public StateMachineException( String aMsg ) {
        super( aMsg );
    }

    /**
     * Constructs a new instance.
     * 
     * @param aException
     *            an exception to nest
     */
    public StateMachineException( Throwable aException ) {
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
    public StateMachineException( String aMsg, Throwable aException ) {
        super( aMsg, aException );
    }
}
