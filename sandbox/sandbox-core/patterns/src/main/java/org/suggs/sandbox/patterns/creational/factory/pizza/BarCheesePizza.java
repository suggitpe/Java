/*
 * BarCheesePizza.java created on 22 Aug 2007 06:29:40 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.creational.factory.pizza;

/**
 * Bar based cheese pizza
 * 
 * @author suggitpe
 * @version 1.0 22 Aug 2007
 */
public class BarCheesePizza extends AbstractPizza
{

    /**
     * Constructs a new instance.
     */
    public BarCheesePizza()
    {
        super( new String[] { "Cheese", "Tomato" } );
    }

    /**
     * @see org.suggs.sandbox.patterns.creational.factory.pizza.AbstractPizza#getPizzaName()
     */
    @Override
    protected String getPizzaName()
    {
        return "Bar Cheese Pizza";
    }

}
