/*
 * AbstractCondimentDecorator.java created on 28 Aug 2007 17:29:18 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.decorator.beveragedecorator;

import org.suggs.sandbox.patterns.structural.decorator.AbstractBeverage;
import org.suggs.sandbox.patterns.structural.decorator.IBeverage;

/**
 * Abstract decorator class that forces us to re-implement the getDescription method.
 */
public abstract class AbstractCondimentDecorator extends AbstractBeverage {

    private IBeverage beverage;

    @SuppressWarnings("unused")
    private AbstractCondimentDecorator() {
    }

    public AbstractCondimentDecorator(IBeverage aBeverage) {
        beverage = aBeverage;
    }

    @Override
    public abstract String getDescription();

    protected IBeverage getBeverage() {
        return beverage;
    }
}
