/*
 * HouseBlend.java created on 29 Aug 2007 05:49:33 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.decorator.beverage;

import org.suggs.sandbox.patterns.structural.decorator.AbstractBeverage;

/**
 * House Blend Coffee beverage
 * 
 * @author suggitpe
 * @version 1.0 29 Aug 2007
 */
public class HouseBlend extends AbstractBeverage {

    /**
     * Constructs a new instance.
     */
    public HouseBlend() {
        setDescription( "House Blend Coffee" );
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.decorator.IBeverage#cost()
     */
    public double cost() {
        return 0.89;
    }

}
