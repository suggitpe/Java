/*
 * SoyaMilk.java created on 29 Aug 2007 06:01:27 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.decorator.beveragedecorator;

import org.suggs.sandbox.patterns.structural.decorator.IBeverage;

/**
 * Soya Milk decorator
 */
public class SoyaMilk extends AbstractCondimentDecorator {


    public SoyaMilk(IBeverage aBeverage) {
        super(aBeverage);
    }

    @Override
    public String getDescription() {
        return getBeverage().getDescription() + ", Soya";
    }

    @Override
    public double cost() {
        return 0.15d + getBeverage().cost();
    }

}
