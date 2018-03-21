/*
 * Decafinated.java created on 29 Aug 2007 05:50:57 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.decorator.beverage;

import org.suggs.sandbox.patterns.structural.decorator.AbstractBeverage;

/**
 * Decafinated Coffee beverage
 */
public class Decafinated extends AbstractBeverage {

    public Decafinated() {
        setDescription("Decafinated");
    }

    @Override
    public double cost() {
        return 1.05;
    }

}
