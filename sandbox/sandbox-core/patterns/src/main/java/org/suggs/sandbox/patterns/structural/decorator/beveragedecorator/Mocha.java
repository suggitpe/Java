/*
 * Mocha.java created on 29 Aug 2007 05:57:02 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.decorator.beveragedecorator;

import org.suggs.sandbox.patterns.structural.decorator.IBeverage;

/**
 * Mocha decorator
 * 
 * @author suggitpe
 * @version 1.0 29 Aug 2007
 */
public class Mocha extends AbstractCondimentDecorator {

    /**
     * Constructs a new instance.
     * 
     * @param aBeverage
     *            the beverage top decorate
     */
    public Mocha( IBeverage aBeverage ) {
        super( aBeverage );
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.decorator.beveragedecorator.AbstractCondimentDecorator#getDescription()
     */
    @Override
    public String getDescription() {
        return getBeverage().getDescription() + ", Mocha";
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.decorator.IBeverage#cost()
     */
    public double cost() {
        return 0.2d + getBeverage().cost();
    }

}
