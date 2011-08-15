/*
 * FooHamPizza.java created on 22 Aug 2007 06:31:11 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.creational.factory.pizza;

/**
 * Foo based ham pizza
 * 
 * @author suggitpe
 * @version 1.0 22 Aug 2007
 */
public class FooHamPizza extends AbstractPizza {

    /**
     * Constructs a new instance.
     */
    public FooHamPizza() {
        super( new String[] { "Cheese", "Tomato", "Ham" } );
    }

    /**
     * Constructs a new instance.
     * 
     * @param aToppings
     *            the toppings to add
     */
    public FooHamPizza( String[] aToppings ) {
        super( aToppings );
    }

    /**
     * @see org.suggs.sandbox.patterns.creational.factory.pizza.AbstractPizza#getPizzaName()
     */
    @Override
    protected String getPizzaName() {
        return "Foo Ham Pizza";
    }

}
