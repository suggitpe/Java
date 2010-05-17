/*
 * TheatreLights.java created on 3 Sep 2007 06:56:42 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.facade.subsystem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class to encapsulate the lights within a theatre
 * 
 * @author suggitpe
 * @version 1.0 3 Sep 2007
 */
public class TheatreLights {

    private static final Log LOG = LogFactory.getLog( TheatreLights.class );

    public void dim( int i ) {
        LOG.debug( "Dimming lights to [" + i + "]" );
    }

    public void off() {
        LOG.debug( "Lights off" );
    }

}
