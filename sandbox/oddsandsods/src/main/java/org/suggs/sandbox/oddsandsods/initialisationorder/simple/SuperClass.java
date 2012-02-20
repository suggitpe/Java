/*
 * SuperClass.java created on 20 Sep 2007 17:52:59 by suggitpe for project SandBox - OddsAndSods
 * 
 */
package org.suggs.sandbox.oddsandsods.initialisationorder.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class that will print a value (three) when instantiated.
 * 
 * @author suggitpe
 * @version 1.0 20 Sep 2007
 */
public class SuperClass {

    private static final Logger LOG = LoggerFactory.getLogger( SuperClass.class );

    /**
     * Constructs a new instance.
     */
    public SuperClass() {
        printThree();
    }

    /**
     * 
     */
    public void printThree() {
        LOG.debug( "three" );
    }

}
