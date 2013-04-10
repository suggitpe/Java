/*
 * Mocha.java created on 29 Aug 2007 05:57:02 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.decorator.beveragedecorator;

import org.suggs.sandbox.patterns.structural.decorator.IBeverage;

/**
 * Mocha decorator
 */
public class Mocha extends AbstractCondimentDecorator {

    public Mocha(IBeverage aBeverage) {
        super(aBeverage);
    }

    @Override
    public String getDescription() {
        return getBeverage().getDescription() + ", Mocha";
    }

    @Override
    public double cost() {
        return 0.2d + getBeverage().cost();
    }

}
