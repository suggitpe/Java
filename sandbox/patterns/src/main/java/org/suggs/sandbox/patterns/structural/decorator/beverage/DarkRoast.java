/*
 * DarkRoast.java created on 29 Aug 2007 05:51:18 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.decorator.beverage;

import org.suggs.sandbox.patterns.structural.decorator.AbstractBeverage;

/**
 * Dark Roast coffee beverage
 */
public class DarkRoast extends AbstractBeverage {

    public DarkRoast() {
        setDescription("Dark Roast Coffee");
    }

    @Override
    public double cost() {
        return 0.99;
    }

}
