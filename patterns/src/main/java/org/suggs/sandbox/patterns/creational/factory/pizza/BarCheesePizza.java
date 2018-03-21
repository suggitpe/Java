/*
 * BarCheesePizza.java created on 22 Aug 2007 06:29:40 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.creational.factory.pizza;

/**
 * Bar based cheese pizza
 */
public class BarCheesePizza extends AbstractPizza {

    public BarCheesePizza() {
        super( new String[] { "Cheese", "Tomato" } );
    }

    @Override
    protected String getPizzaName() {
        return "Bar Cheese Pizza";
    }

}
