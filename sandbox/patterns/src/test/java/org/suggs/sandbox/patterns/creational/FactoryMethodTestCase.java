/*
 * FactoryMethodTestCase.java created on 23 Aug 2007 06:17:31 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.creational;

import org.suggs.sandbox.patterns.creational.factory.IPizza;
import org.suggs.sandbox.patterns.creational.factory.IPizzaStore;
import org.suggs.sandbox.patterns.creational.factory.PizzaStoreException;
import org.suggs.sandbox.patterns.creational.factory.pizzastore.BarPizzaStore;
import org.suggs.sandbox.patterns.creational.factory.pizzastore.FooPizzaStore;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Test case for the factory pattern
 * 
 * @author suggitpe
 * @version 1.0 23 Aug 2007
 */
public class FactoryMethodTestCase extends TestCase
{

    private static final Log LOG = LogFactory.getLog( FactoryMethodTestCase.class );

    /**
     * Test the normal flow for the foo store
     */
    public void testFooPizzaStore()
    {
        runTest( "testFooPizzaStore", new FooPizzaStore(), "ham", false );
    }

    /**
     * Test the normal flow for the bar pizza store
     */
    public void testBarPizzaStore()
    {
        runTest( "testBarPizzaStore", new BarPizzaStore(), "cheese", false );

    }

    /**
     * Test the alternate flow for the foo store
     */
    public void testFooPizzaStoreFailure()
    {
        runTest( "testFooPizzaStoreFailure", new FooPizzaStore(), "dog-poo", true );
    }

    /**
     * Test the alternate flow for the bar store
     */
    public void testBarPizzaStoreFailure()
    {
        runTest( "testBarPizzaStoreFailure", new BarPizzaStore(), "cat-wee", true );
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
    private void runTest( String aTestName, IPizzaStore aStore, String aType, boolean expectException )
    {
        LOG.info( " --------------- [" + aTestName + "] start" );
        LOG.debug( "Testing Pizza Store for a [" + aType + "] pizza" );

        IPizza pizza = null;
        try
        {
            pizza = aStore.orderPizza( aType );
            if ( expectException )
            {
                fail( "The test should have thrown an exception" );
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
                fail( "Caught exception in the pizza store [" + e.getMessage() + "]" );
            }
            else
            {
                LOG.debug( "Correctly threw exception from the Pizza Store" );
            }
        }

        LOG.info( " --------------- [" + aTestName + "] end" );
    }
}
