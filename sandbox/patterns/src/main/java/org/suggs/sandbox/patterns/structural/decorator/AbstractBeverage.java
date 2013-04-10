/*
 * AbstractBeverage.java created on 28 Aug 2007 17:27:07 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.decorator;

/**
 * Abstract base class for a beverage
 */
public abstract class AbstractBeverage implements IBeverage {

    private String description;

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String aDescription) {
        description = aDescription;
    }

    @Override
    public String toString() {
        return "Beverage: description=[" + getDescription() + "], cost=[" + cost() + "]";
    }
}
