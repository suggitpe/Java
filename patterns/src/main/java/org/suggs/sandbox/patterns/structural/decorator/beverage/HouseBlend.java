/*
 * HouseBlend.java created on 29 Aug 2007 05:49:33 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.decorator.beverage;

import org.suggs.sandbox.patterns.structural.decorator.AbstractBeverage;

/**
 * House Blend Coffee beverage
 */
public class HouseBlend extends AbstractBeverage {

    public HouseBlend() {
        setDescription("House Blend Coffee");
    }

    @Override
    public double cost() {
        return 0.89;
    }

}
