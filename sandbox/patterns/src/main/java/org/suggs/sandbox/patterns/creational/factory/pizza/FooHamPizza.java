/*
 * FooHamPizza.java created on 22 Aug 2007 06:31:11 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.creational.factory.pizza;

/**
 * Foo based ham pizza
 */
public class FooHamPizza extends AbstractPizza {

    public FooHamPizza() {
        super( new String[] { "Cheese", "Tomato", "Ham" } );
    }

    public FooHamPizza( String[] aToppings ) {
        super( aToppings );
    }
    @Override
    protected String getPizzaName() {
        return "Foo Ham Pizza";
    }

}
