/*
 * PizzaStoreException.java created on 23 Aug 2007 05:58:40 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.creational.factory;

/**
 * Exception to manage exceptions that have occured in te Pizza Store
 */
public class PizzaStoreException extends Exception {

    private static final long serialVersionUID = -7236519459564318461L;

    public PizzaStoreException(String aMessage) {
        super(aMessage);
    }
}
