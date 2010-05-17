/*
 * Screen.java created on 3 Sep 2007 06:56:51 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.facade.subsystem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class to encapsulate a screen
 * 
 * @author suggitpe
 * @version 1.0 3 Sep 2007
 */
public class Screen {

    private static final Log LOG = LogFactory.getLog( Screen.class );

    public void down() {
        LOG.debug( "Screen down" );
    }

    public void up() {
        LOG.debug( "Screen up" );
    }

}
