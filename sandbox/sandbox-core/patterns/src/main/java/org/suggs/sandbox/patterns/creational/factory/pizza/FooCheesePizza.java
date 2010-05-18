/*
 * FooCheesePizza.java created on 22 Aug 2007 06:30:28 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.creational.factory.pizza;

/**
 * Foo based pizza
 * 
 * @author suggitpe
 * @version 1.0 22 Aug 2007
 */
public class FooCheesePizza extends AbstractPizza {

    /**
     * Constructs a new instance.
     */
    public FooCheesePizza() {
        super( new String[] { "Cheese", "Tomato" } );
    }

    /**
     * @see org.suggs.sandbox.patterns.creational.factory.pizza.AbstractPizza#getPizzaName()
     */
    @Override
    protected String getPizzaName() {
        return "Foo Cheese Pizza";
    }

}
