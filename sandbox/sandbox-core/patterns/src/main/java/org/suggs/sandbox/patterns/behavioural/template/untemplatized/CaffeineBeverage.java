/*
 * CaffeineBeverage.java created on 3 Sep 2007 15:25:03 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.template.untemplatized;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class to encapsulate a caffeinated beverage. In this method we define that all subclassses must implement a
 * method called prepareRecipe and provide some common methods that can be called if needed.
 * 
 * @author suggitpe
 * @version 1.0 3 Sep 2007
 */
public abstract class CaffeineBeverage {

    private static final Log LOG = LogFactory.getLog( CaffeineBeverage.class );

    /**
     * Prepare a beverage.
     */
    public abstract void prepareReceipe();

    /**
     * Boil some water
     */
    protected void boilWater() {
        LOG.debug( "Boiling water" );
    }

    /**
     * Pour water into the cup
     */
    protected void pourInCup() {
        LOG.debug( "Pouring water into the cup" );
    }

}
