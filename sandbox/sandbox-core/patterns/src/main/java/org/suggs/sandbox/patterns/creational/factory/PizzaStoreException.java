/*
 * PizzaStoreException.java created on 23 Aug 2007 05:58:40 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.creational.factory;

/**
 * Exception to manage exceptions that have occured in te Pizza Store
 * 
 * @author suggitpe
 * @version 1.0 23 Aug 2007
 */
public class PizzaStoreException extends Exception {

    /**
     * Constructs a new instance.
     */
    public PizzaStoreException() {
        super();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aMessage
     */
    public PizzaStoreException( String aMessage ) {
        super( aMessage );
    }

    /**
     * Constructs a new instance.
     * 
     * @param aMessage
     * @param aError
     */
    public PizzaStoreException( String aMessage, Throwable aError ) {
        super( aMessage, aError );
    }

    /**
     * Constructs a new instance.
     * 
     * @param aError
     */
    public PizzaStoreException( Throwable aError ) {
        super( aError );
    }
}
