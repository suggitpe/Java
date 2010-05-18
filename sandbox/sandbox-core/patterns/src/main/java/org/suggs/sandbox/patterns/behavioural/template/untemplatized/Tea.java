/*
 * Tea.java created on 3 Sep 2007 15:23:48 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.template.untemplatized;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class to encapsulate tea.
 * 
 * @author suggitpe
 * @version 1.0 3 Sep 2007
 */
public class Tea extends CaffeineBeverage {

    private static final Log LOG = LogFactory.getLog( Tea.class );

    /**
     * @see org.suggs.sandbox.patterns.behavioural.template.untemplatized.CaffeineBeverage#prepareReceipe()
     */
    @Override
    public void prepareReceipe() {
        boilWater();
        steepTeaBag();
        pourInCup();
        addLemon();
    }

    /**
     * Steep the tea bag
     */
    private void steepTeaBag() {
        LOG.debug( "Steeping tea bag" );
    }

    /**
     * Add lemon to the mix
     */
    private void addLemon() {
        LOG.debug( "Adding lemon" );
    }

}
