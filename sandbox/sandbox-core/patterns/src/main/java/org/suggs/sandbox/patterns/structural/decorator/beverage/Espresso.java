/*
 * Espresso.java created on 29 Aug 2007 05:46:31 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.decorator.beverage;

import org.suggs.sandbox.patterns.structural.decorator.AbstractBeverage;

/**
 * Espresso beverage
 * 
 * @author suggitpe
 * @version 1.0 29 Aug 2007
 */
public class Espresso extends AbstractBeverage {

    /**
     * Constructs a new instance.
     */
    public Espresso() {
        setDescription( "Espresso" );
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.decorator.IBeverage#cost()
     */
    public double cost() {
        return 1.99;
    }

}
