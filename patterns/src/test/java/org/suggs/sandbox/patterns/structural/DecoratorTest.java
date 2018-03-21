/*
 * DecoratorTestCase.java created on 29 Aug 2007 06:12:04 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural;

import org.suggs.sandbox.patterns.AbstractPatternTest;
import org.suggs.sandbox.patterns.structural.decorator.IBeverage;
import org.suggs.sandbox.patterns.structural.decorator.beverage.Espresso;
import org.suggs.sandbox.patterns.structural.decorator.beverage.HouseBlend;
import org.suggs.sandbox.patterns.structural.decorator.beveragedecorator.Mocha;
import org.suggs.sandbox.patterns.structural.decorator.beveragedecorator.SteamedMilk;
import org.suggs.sandbox.patterns.structural.decorator.beveragedecorator.WhippedMilk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;

/**
 * Test case for the decorator pattern
 */
public class DecoratorTest extends AbstractPatternTest {

    private static final Logger LOG = LoggerFactory.getLogger( DecoratorTest.class );

    @Test
    public void testNormalEspresso() {
        IBeverage e = new Espresso();
        LOG.debug( "Normal espresso - " + e );
    }

    @Test
    public void testEspressoWithExtras() {
        IBeverage e = new Espresso();
        e = new Mocha( e );
        e = new Mocha( e );
        e = new WhippedMilk( e );
        LOG.debug( "Espresso with extras - " + e );
    }

    @Test
    public void testHouseBlend() {
        IBeverage h = new HouseBlend();
        LOG.debug( "Normal house blend - " + h );
    }

    @Test
    public void testHouseBlendWithExtras() {
        IBeverage h = new HouseBlend();
        h = new SteamedMilk( h );
        LOG.debug( "House blend with extras - " + h );
    }
}
