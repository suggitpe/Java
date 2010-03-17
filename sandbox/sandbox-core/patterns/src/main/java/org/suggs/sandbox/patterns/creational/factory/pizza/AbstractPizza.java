/*
 * AbstractPizza.java created on 23 Aug 2007 05:44:23 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.creational.factory.pizza;

import org.suggs.sandbox.patterns.creational.factory.IPizza;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Abstract implementation of the pizza
 * 
 * @author suggitpe
 * @version 1.0 23 Aug 2007
 */
public abstract class AbstractPizza implements IPizza
{

    private static final Log LOG = LogFactory.getLog( AbstractPizza.class );

    private String[] mToppings;

    /**
     * Constructs a new instance.
     */
    protected AbstractPizza()
    {
    }

    /**
     * Constructs a new instance.
     * 
     * @param aToppings
     *            an array of toppings to add to the pizza
     */
    protected AbstractPizza( String[] aToppings )
    {
        mToppings = aToppings;
    }

    /**
     * Abstract method that forces concrete types to define their name
     * 
     * @return the name of the pizza
     */
    protected abstract String getPizzaName();

    /**
     * @see org.suggs.sandbox.patterns.creational.factory.IPizza#bake()
     */
    public void bake()
    {
        LOG.debug( "Baking for 30 mins" );
    }

    /**
     * @see org.suggs.sandbox.patterns.creational.factory.IPizza#box()
     */
    public void box()
    {
        LOG.debug( "Boxing up the pizza" );
    }

    /**
     * @see org.suggs.sandbox.patterns.creational.factory.IPizza#cut()
     */
    public void cut()
    {
        LOG.debug( "Cutting the pizza" );
    }

    /**
     * @see org.suggs.sandbox.patterns.creational.factory.IPizza#prepare()
     */
    public void prepare()
    {
        LOG.debug( "Preparing " + getPizzaName() );
        LOG.debug( "\ttossing dough ..." );
        LOG.debug( "\tadding sauce ..." );
        StringBuffer buff = new StringBuffer();
        for ( String s : mToppings )
        {
            buff.append( s ).append( " " );

        }
        LOG.debug( "\tadding toppings: " + buff.toString() );
    }
}
