/*
 * Coffee.java created on 3 Sep 2007 15:23:41 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.template.untemplatized;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class to encapsulate coffee.
 * 
 * @author suggitpe
 * @version 1.0 3 Sep 2007
 */
public class Coffee extends CaffeineBeverage {

    private static final Log LOG = LogFactory.getLog( Coffee.class );

    /**
     * @see org.suggs.sandbox.patterns.behavioural.template.untemplatized.CaffeineBeverage#prepareReceipe()
     */
    @Override
    public void prepareReceipe() {
        boilWater();
        brewCoffeeGrinds();
        pourInCup();
        addMilkAndSugar();
    }

    /**
     * Brew the coffee grinds
     */
    private void brewCoffeeGrinds() {
        LOG.debug( "Brewing coffee grinds" );
    }

    /**
     * Add in milk and sugar.
     */
    private void addMilkAndSugar() {
        LOG.debug( "Add in milk and sugar to the coffee" );
    }

}
