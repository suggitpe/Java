/*
 * AbstractCondimentDecorator.java created on 28 Aug 2007 17:29:18 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.decorator.beveragedecorator;

import org.suggs.sandbox.patterns.structural.decorator.AbstractBeverage;
import org.suggs.sandbox.patterns.structural.decorator.IBeverage;

/**
 * Abstract decorator class that forces us to re-implement the getDescription method.
 * 
 * @author suggitpe
 * @version 1.0 28 Aug 2007
 */
public abstract class AbstractCondimentDecorator extends AbstractBeverage {

    private IBeverage beverage;

    /**
     * Constructs a new instance. This is hidden from all other classes (no default construction).
     */
    @SuppressWarnings("unused")
    private AbstractCondimentDecorator() {}

    /**
     * Constructs a new instance.
     * 
     * @param aBeverage
     *            the decorating object
     */
    public AbstractCondimentDecorator( IBeverage aBeverage ) {
        beverage = aBeverage;
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.decorator.AbstractBeverage#getDescription()
     */
    @Override
    public abstract String getDescription();

    /**
     * Getter for the decorated beverage
     * 
     * @return the beverage to decorate
     */
    protected IBeverage getBeverage() {
        return beverage;
    }
}
