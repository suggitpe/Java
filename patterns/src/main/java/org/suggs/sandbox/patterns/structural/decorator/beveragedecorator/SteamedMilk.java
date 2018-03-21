/*
 * SteamedMilk.java created on 29 Aug 2007 06:01:05 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.decorator.beveragedecorator;

import org.suggs.sandbox.patterns.structural.decorator.IBeverage;

/**
 * Steamed Milk decorator
 */
public class SteamedMilk extends AbstractCondimentDecorator {

    public SteamedMilk( IBeverage aBeverage ) {
        super( aBeverage );
    }

    @Override
    public String getDescription() {
        return getBeverage().getDescription() + ", Steamed";
    }

    @Override
    public double cost() {
        return 0.1d + getBeverage().cost();
    }

}
