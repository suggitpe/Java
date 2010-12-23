/*
 * GarageDoor.java created on 30 Aug 2007 06:10:44 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.command.receivers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to encapsulate a garage door
 * 
 * @author suggitpe
 * @verrsion 1.0 30 Aug 2007
 */
public class GarageDoor {

    private static final Logger LOG = LoggerFactory.getLogger( GarageDoor.class );
    private String location;

    /**
     * Constructs a new instance.
     * 
     * @param aLocation
     *            the location of the garage door
     */
    public GarageDoor( String aLocation ) {
        location = aLocation;
    }

    /**
     * Move door up
     */
    public void up() {
        LOG.debug( "Garage door up!" );
    }

    /**
     * Move door down
     */
    public void down() {
        LOG.debug( "Garage door down!" );
    }

    /**
     * Stop the door movement
     */
    public void stop() {
        LOG.debug( "Garge door stop!" );
    }

    /**
     * Turn light on
     */
    public void lightOn() {
        LOG.debug( "Light (" + location + ") on!" );
    }

    /**
     * Turn light off
     */
    public void lightOff() {
        LOG.debug( "Light (" + location + ") off!" );
    }

}
