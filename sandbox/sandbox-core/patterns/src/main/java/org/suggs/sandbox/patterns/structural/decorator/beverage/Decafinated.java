/*
 * Decafinated.java created on 29 Aug 2007 05:50:57 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.decorator.beverage;

import org.suggs.sandbox.patterns.structural.decorator.AbstractBeverage;

/**
 * Decafinated Coffee beverage
 * 
 * @author suggitpe
 * @version 1.0 29 Aug 2007
 */
public class Decafinated extends AbstractBeverage {

    /**
     * Constructs a new instance.
     */
    public Decafinated() {
        setDescription( "Decafinated" );
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.decorator.IBeverage#cost()
     */
    public double cost() {
        return 1.05;
    }

}
