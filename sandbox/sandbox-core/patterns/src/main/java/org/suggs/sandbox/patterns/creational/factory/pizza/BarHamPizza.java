/*
 * BarHamPizza.java created on 22 Aug 2007 06:31:32 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.creational.factory.pizza;

/**
 * Bar based ham pizza
 * 
 * @author suggitpe
 * @version 1.0 22 Aug 2007
 */
public class BarHamPizza extends AbstractPizza
{

    /**
     * Constructs a new instance.
     */
    public BarHamPizza()
    {
        super( new String[] { "Cheese", "Tomato" } );
    }

    /**
     * @see org.suggs.sandbox.patterns.creational.factory.pizza.AbstractPizza#getPizzaName()
     */
    @Override
    protected String getPizzaName()
    {
        return "Bar Ham PIzza";
    }

}
