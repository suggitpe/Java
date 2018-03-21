/*
 * BarHamPizza.java created on 22 Aug 2007 06:31:32 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.creational.factory.pizza;

/**
 * Bar based ham pizza
 */
public class BarHamPizza extends AbstractPizza {

    public BarHamPizza() {
        super( new String[] { "Cheese", "Tomato" } );
    }

    @Override
    protected String getPizzaName() {
        return "Bar Ham PIzza";
    }

}
