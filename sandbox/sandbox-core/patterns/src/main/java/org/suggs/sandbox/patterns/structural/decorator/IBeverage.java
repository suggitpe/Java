/*
 * IBeverage.java created on 28 Aug 2007 17:25:05 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.decorator;

/**
 * Interface for a beverage.
 * 
 * @author suggitpe
 * @version 1.0 28 Aug 2007
 */
public interface IBeverage {

    /**
     * Gets the description of the beverage
     * 
     * @return the description of the beverage
     */
    String getDescription();

    /**
     * Returns the cost of the beverage
     * 
     * @return cost
     */
    double cost();

}
