/*
 * WhippedMilk.java created on 29 Aug 2007 06:01:48 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.decorator.beveragedecorator;

import org.suggs.sandbox.patterns.structural.decorator.IBeverage;

/**
 * Whipped milk beverage
 */
public class WhippedMilk extends AbstractCondimentDecorator {

    public WhippedMilk(IBeverage aBeverage) {
        super(aBeverage);
    }

    @Override
    public String getDescription() {
        return getBeverage().getDescription() + ", Whipped";
    }

    @Override
    public double cost() {
        return 0.1d + getBeverage().cost();
    }

}
