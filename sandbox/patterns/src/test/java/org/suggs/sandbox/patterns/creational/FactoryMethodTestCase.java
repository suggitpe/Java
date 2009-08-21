/*
 * FactoryMethodTestCase.java created on 23 Aug 2007 06:17:31 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.creational;

import org.suggs.sandbox.patterns.AbstractPatternTestCase;
import org.suggs.sandbox.patterns.creational.factory.IPizza;
import org.suggs.sandbox.patterns.creational.factory.IPizzaStore;
import org.suggs.sandbox.patterns.creational.factory.PizzaStoreException;
import org.suggs.sandbox.patterns.creational.factory.pizzastore.BarPizzaStore;
import org.suggs.sandbox.patterns.creational.factory.pizzastore.FooPizzaStore;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * Test case for the factory pattern
 * 
 * @author suggitpe
 * @version 1.0 23 Aug 2007
 */
public class FactoryMethodTestCase extends AbstractPatternTestCase
{

    private static final Log LOG = LogFactory.getLog( FactoryMethodTestCase.class );

    @Test
    public void testFooPizzaStore()
    {
        runTest( new FooPizzaStore(), "ham", false );
    }

    @Test
    public void testBarPizzaStore()
    {
        runTest( new BarPizzaStore(), "cheese", false );
    }

    @Test
    public void testFooPizzaStoreFailure()
    {
        runTest( new FooPizzaStore(), "dog-poo", true );
    }

    @Test
    public void testBarPizzaStoreFailure()
    {
        runTest( new BarPizzaStore(), "cat-wee", true );
    }

    /**
     * Simple test execution method
     * 
     * @param aStore
     *            the store to test
     * @param aType
     *            the type of pizza required
     * @param expectException
     *            whether we expect an exception to be thrown
     */
    private void runTest( IPizzaStore aStore, String aType, boolean expectException )
    {
        LOG.debug( "Testing Pizza Store for a [" + aType + "] pizza" );

        IPizza pizza = null;
        try
        {
            pizza = aStore.orderPizza( aType );
            if ( expectException )
            {
                Assert.fail( "The test should have thrown an exception" );
            }
            else
            {
                LOG.debug( "Pizza delivered was a [" + pizza.getClass().getSimpleName() + "]" );
            }
        }
        catch ( PizzaStoreException e )
        {
            if ( !expectException )
            {
                Assert.fail( "Caught exception in the pizza store [" + e.getMessage() + "]" );
            }
            else
            {
                LOG.debug( "Correctly threw exception from the Pizza Store" );
            }
        }
    }
}
