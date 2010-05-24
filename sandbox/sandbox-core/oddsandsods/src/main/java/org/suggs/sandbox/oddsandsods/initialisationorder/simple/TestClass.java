/*
 * TestClass.java created on 20 Sep 2007 17:55:22 by suggitpe for project SandBox - OddsAndSods
 * 
 */
package org.suggs.sandbox.oddsandsods.initialisationorder.simple;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Simple class that will extend the SuperClass with its own impl of the printThree method.
 * 
 * @author suggitpe
 * @version 1.0 20 Sep 2007
 */
public class TestClass extends SuperClass {

    private static final Log LOG = LogFactory.getLog( TestClass.class );
    private int three = (int) Math.PI; // ie 3

    /**
     * @see org.suggs.sandbox.oddsandsods.initialisationorder.simple.SuperClass#printThree()
     */
    @SuppressWarnings("boxing")
    @Override
    public void printThree() {
        LOG.debug( three );
    }
}
