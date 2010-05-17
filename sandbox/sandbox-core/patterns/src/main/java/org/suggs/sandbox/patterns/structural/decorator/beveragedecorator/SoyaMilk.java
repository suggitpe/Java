/*
 * SoyaMilk.java created on 29 Aug 2007 06:01:27 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.decorator.beveragedecorator;

import org.suggs.sandbox.patterns.structural.decorator.IBeverage;

/**
 * Soya Milk decorator
 * 
 * @author suggitpe
 * @version 1.0 29 Aug 2007
 */
public class SoyaMilk extends AbstractCondimentDecorator {

    /**
     * Constructs a new instance.
     * 
     * @param aBeverage
     *            the beverage to decorate
     */
    public SoyaMilk( IBeverage aBeverage ) {
        super( aBeverage );
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.decorator.beveragedecorator.AbstractCondimentDecorator#getDescription()
     */
    @Override
    public String getDescription() {
        return getBeverage().getDescription() + ", Soya";
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.decorator.IBeverage#cost()
     */
    public double cost() {
        return 0.15d + getBeverage().cost();
    }

}
