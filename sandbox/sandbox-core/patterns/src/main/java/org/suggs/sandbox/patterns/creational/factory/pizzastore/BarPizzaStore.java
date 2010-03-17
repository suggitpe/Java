/*
 * BarPizzaStore.java created on 22 Aug 2007 06:22:38 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.creational.factory.pizzastore;

import org.suggs.sandbox.patterns.creational.factory.IPizza;
import org.suggs.sandbox.patterns.creational.factory.PizzaStoreException;
import org.suggs.sandbox.patterns.creational.factory.pizza.BarCheesePizza;
import org.suggs.sandbox.patterns.creational.factory.pizza.BarHamPizza;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Concrete pizza store object that implements the createPizza factory
 * method.
 * 
 * @author suggitpe
 * @version 1.0 22 Aug 2007
 */
public class BarPizzaStore extends AbstractPizzaStore
{

    private static final Log LOG = LogFactory.getLog( BarPizzaStore.class );

    /**
     * @see org.suggs.sandbox.patterns.creational.factory.pizzastore.AbstractPizzaStore#createPizza(java.lang.String)
     */
    @Override
    protected IPizza createPizza( String aType ) throws PizzaStoreException
    {
        LOG.debug( "Creating a Bar based pizza" );

        if ( aType.equalsIgnoreCase( "cheese" ) )
        {
            return new BarCheesePizza();
        }
        else if ( aType.equalsIgnoreCase( "ham" ) )
        {
            return new BarHamPizza();
        }
        throw new PizzaStoreException( "Pizza type [" + aType + "] is not valid for BarPizzaStore" );
    }
}
