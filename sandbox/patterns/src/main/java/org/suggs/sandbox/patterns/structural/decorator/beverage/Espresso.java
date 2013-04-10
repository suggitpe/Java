/*
 * Espresso.java created on 29 Aug 2007 05:46:31 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.decorator.beverage;

import org.suggs.sandbox.patterns.structural.decorator.AbstractBeverage;

/**
 * Espresso beverage
 */
public class Espresso extends AbstractBeverage {

    public Espresso() {
        setDescription("Espresso");
    }

    @Override
    public double cost() {
        return 1.99;
    }

}
