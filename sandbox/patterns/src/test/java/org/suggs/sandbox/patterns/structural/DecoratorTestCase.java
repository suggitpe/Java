/*
 * DecoratorTestCase.java created on 29 Aug 2007 06:12:04 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural;

import org.suggs.sandbox.patterns.AbstractPatternTestCase;
import org.suggs.sandbox.patterns.structural.decorator.IBeverage;
import org.suggs.sandbox.patterns.structural.decorator.beverage.Espresso;
import org.suggs.sandbox.patterns.structural.decorator.beverage.HouseBlend;
import org.suggs.sandbox.patterns.structural.decorator.beveragedecorator.Mocha;
import org.suggs.sandbox.patterns.structural.decorator.beveragedecorator.SteamedMilk;
import org.suggs.sandbox.patterns.structural.decorator.beveragedecorator.WhippedMilk;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * Test case for the decorator pattern
 * 
 * @author suggitpe
 * @version 1.0 29 Aug 2007
 */
public class DecoratorTestCase extends AbstractPatternTestCase
{

    private static final Log LOG = LogFactory.getLog( DecoratorTestCase.class );

    @Test
    public void testNormalEspresso()
    {
        IBeverage e = new Espresso();
        LOG.debug( "Normal espresso - " + e );
    }

    @Test
    public void testEspressoWithExtras()
    {
        IBeverage e = new Espresso();
        e = new Mocha( e );
        e = new Mocha( e );
        e = new WhippedMilk( e );
        LOG.debug( "Espresso with extras - " + e );
    }

    @Test
    public void testHouseBlend()
    {
        IBeverage h = new HouseBlend();
        LOG.debug( "Normal house blend - " + h );
    }

    @Test
    public void testHouseBlendWithExtras()
    {
        IBeverage h = new HouseBlend();
        h = new SteamedMilk( h );
        LOG.debug( "House blend with extras - " + h );
    }
}
