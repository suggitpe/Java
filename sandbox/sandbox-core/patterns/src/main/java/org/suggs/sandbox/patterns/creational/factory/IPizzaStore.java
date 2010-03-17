/*
 * IPizzaStore.java created on 23 Aug 2007 06:30:29 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.creational.factory;

/**
 * Interface to the pizza store
 * 
 * @author suggitpe
 * @version 1.0 23 Aug 2007
 */
public interface IPizzaStore
{

    /**
     * @param aType
     *            type
     * @return a pizza
     * @throws PizzaStoreException
     */
    IPizza orderPizza( String aType ) throws PizzaStoreException;

}
