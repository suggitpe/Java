/*
 * Light.java created on 29 Aug 2007 17:08:24 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.command.receivers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class to encapsulate a Light
 * 
 * @author suggitpe
 * @version 1.0 29 Aug 2007
 */
public class Light {

    private static final Log LOG = LogFactory.getLog( Light.class );
    private String location;

    /**
     * Constructs a new instance.
     * 
     * @param aLocation
     *            the location of the Light
     */
    public Light( String aLocation ) {
        location = aLocation;
    }

    /**
     * Light on
     */
    public void on() {
        LOG.debug( "Light (" + location + ") on!" );
    }

    /**
     * Light off
     */
    public void off() {
        LOG.debug( "Light (" + location + ") off!" );
    }

}
