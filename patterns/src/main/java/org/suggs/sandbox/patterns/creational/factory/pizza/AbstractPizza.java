/*
 * AbstractPizza.java created on 23 Aug 2007 05:44:23 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.creational.factory.pizza;

import org.suggs.sandbox.patterns.creational.factory.IPizza;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract implementation of the pizza
 */
public abstract class AbstractPizza implements IPizza {

    private static final Logger LOG = LoggerFactory.getLogger( AbstractPizza.class );

    private String[] toppings;

    protected AbstractPizza( String[] aToppings ) {
        if ( aToppings != null ) {
            toppings = Arrays.copyOf( aToppings, aToppings.length );
        }
    }

    protected abstract String getPizzaName();

    @Override
    public void bake() {
        LOG.debug( "Baking for 30 mins" );
    }

    @Override
    public void box() {
        LOG.debug( "Boxing up the pizza" );
    }

    @Override
    public void cut() {
        LOG.debug( "Cutting the pizza" );
    }

    @Override
    public void prepare() {
        LOG.debug( "Preparing " + getPizzaName() );
        LOG.debug( "\ttossing dough ..." );
        LOG.debug( "\tadding sauce ..." );
        StringBuffer buff = new StringBuffer();
        for ( String s : toppings ) {
            buff.append( s ).append( " " );

        }
        LOG.debug( "\tadding toppings: " + buff.toString() );
    }
}
