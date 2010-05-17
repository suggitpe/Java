/*
 * DarkRoast.java created on 29 Aug 2007 05:51:18 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.decorator.beverage;

import org.suggs.sandbox.patterns.structural.decorator.AbstractBeverage;

/**
 * Dark Roast coffee beverage
 * 
 * @author suggitpe
 * @version 1.0 29 Aug 2007
 */
public class DarkRoast extends AbstractBeverage {

    /**
     * Constructs a new instance.
     */
    public DarkRoast() {
        description = "Dark Roast Coffee";
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.decorator.IBeverage#cost()
     */
    public double cost() {
        return 0.99;
    }

}
