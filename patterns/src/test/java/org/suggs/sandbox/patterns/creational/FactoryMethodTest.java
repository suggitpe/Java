/*
 * FactoryMethodTestCase.java created on 23 Aug 2007 06:17:31 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.creational;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.suggs.sandbox.patterns.AbstractPatternTest;
import org.suggs.sandbox.patterns.creational.factory.IPizza;
import org.suggs.sandbox.patterns.creational.factory.IPizzaStore;
import org.suggs.sandbox.patterns.creational.factory.PizzaStoreException;
import org.suggs.sandbox.patterns.creational.factory.pizzastore.BarPizzaStore;
import org.suggs.sandbox.patterns.creational.factory.pizzastore.FooPizzaStore;

import static org.junit.Assert.fail;

/**
 * Test case for the factory pattern
 */
public class FactoryMethodTest extends AbstractPatternTest {

    private static final Logger LOG = LoggerFactory.getLogger(FactoryMethodTest.class);

    @Test
    public void testFooPizzaStore() {
        runTest(new FooPizzaStore(), "ham", false);
    }

    @Test
    public void testBarPizzaStore() {
        runTest(new BarPizzaStore(), "cheese", false);
    }

    @Test
    public void testFooPizzaStoreFailure() {
        runTest(new FooPizzaStore(), "dog-poo", true);
    }

    @Test
    public void testBarPizzaStoreFailure() {
        runTest(new BarPizzaStore(), "cat-wee", true);
    }

    private void runTest(IPizzaStore aStore, String aType, boolean expectException) {
        LOG.debug("Testing Pizza Store for a [" + aType + "] pizza");

        IPizza pizza = null;
        try {
            pizza = aStore.orderPizza(aType);
            if (expectException) {
                fail("The test should have thrown an exception");
            } else {
                LOG.debug("Pizza delivered was a [" + pizza.getClass().getSimpleName() + "]");
            }
        } catch (PizzaStoreException e) {
            if (!expectException) {
                fail("Caught exception in the pizza store [" + e.getMessage() + "]");
            } else {
                LOG.debug("Correctly threw exception from the Pizza Store");
            }
        }
    }
}
