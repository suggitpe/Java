/*
 * FooCheesePizza.java created on 22 Aug 2007 06:30:28 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.creational.factory.pizza;

/**
 * Foo based pizza
 */
public class FooCheesePizza extends AbstractPizza {

    public FooCheesePizza() {
        super( new String[] { "Cheese", "Tomato" } );
    }

    @Override
    protected String getPizzaName() {
        return "Foo Cheese Pizza";
    }

}
