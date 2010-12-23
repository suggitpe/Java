/*
 * FooPizzaStore.java created on 22 Aug 2007 06:22:38 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.creational.factory.pizzastore;

import org.suggs.sandbox.patterns.creational.factory.IPizza;
import org.suggs.sandbox.patterns.creational.factory.PizzaStoreException;
import org.suggs.sandbox.patterns.creational.factory.pizza.FooCheesePizza;
import org.suggs.sandbox.patterns.creational.factory.pizza.FooHamPizza;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Concrete pizza store object that implements the createPizza factory method.
 * 
 * @author suggitpe
 * @version 1.0 22 Aug 2007
 */
public class FooPizzaStore extends AbstractPizzaStore {

    private static final Logger LOG = LoggerFactory.getLogger( FooPizzaStore.class );

    /**
     * @see org.suggs.sandbox.patterns.creational.factory.pizzastore.AbstractPizzaStore#createPizza(java.lang.String)
     */
    @Override
    protected IPizza createPizza( String aType ) throws PizzaStoreException {
        LOG.debug( "Creating a Foo based pizza" );

        if ( aType.equalsIgnoreCase( "cheese" ) ) {
            return new FooCheesePizza();
        }
        else if ( aType.equalsIgnoreCase( "ham" ) ) {
            return new FooHamPizza();
        }
        throw new PizzaStoreException( "Pizza type [" + aType + "] is not valid for FooPizzaStore" );
    }
}
