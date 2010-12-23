/*
 * CeilingFan.java created on 30 Aug 2007 17:07:48 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.command.receivers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to encapsulate a Ceiling fan
 * 
 * @author suggitpe
 * @version 1.0 30 Aug 2007
 */
public class CeilingFan {

    private static final Logger LOG = LoggerFactory.getLogger( CeilingFan.class );
    private String speed;
    private String location;

    /**
     * Constructs a new instance.
     * 
     * @param aLocation
     *            the location of the ceiling fan
     */
    public CeilingFan( String aLocation ) {
        location = aLocation;
    }

    /**
     * 
     */
    public void high() {
        LOG.debug( "Ceiling Fan (" + location + ") high!" );
        speed = "HIGH";
    }

    /**
     * 
     */
    public void medium() {
        LOG.debug( "Ceiling Fan (" + location + ") medium!" );
        speed = "MEDIUM";
    }

    /**
     * 
     */
    public void low() {
        LOG.debug( "Ceiling Fan (" + location + ") low!" );
        speed = "LOW";
    }

    /**
     * 
     */
    public void off() {
        LOG.debug( "Ceiling Fan (" + location + ") off!" );
        speed = "OFF";
    }

    /**
     * @return speed
     */
    public String getSpeed() {
        return speed;
    }

}
