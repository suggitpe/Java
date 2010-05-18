/*
 * CaffeineBeverage.java created on 3 Sep 2007 15:25:03 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.template.templatized;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class to encapsulate a templatized caffeinated beverage
 * 
 * @author suggitpe
 * @version 1.0 3 Sep 2007
 */
public abstract class CaffeineBeverage {

    private static final Log LOG = LogFactory.getLog( CaffeineBeverage.class );

    /**
     * Prepare a beverage.
     */
    public final void prepareReceipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    /**
     * Boil some water
     */
    private void boilWater() {
        LOG.debug( "Boiling water" );
    }

    /**
     * Pour water into the cup
     */
    private void pourInCup() {
        LOG.debug( "Pouring water into the cup" );
    }

    /**
     * Brew the beverage
     */
    protected abstract void brew();

    /**
     * Add in the beverage condiments
     */
    protected abstract void addCondiments();

}
