/*
 * GarageDoor.java created on 30 Aug 2007 06:10:44 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.command.receivers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class to encapsulate a garage door
 * 
 * @author suggitpe
 * @verrsion 1.0 30 Aug 2007
 */
public class GarageDoor
{

    private static final Log LOG = LogFactory.getLog( GarageDoor.class );

    private String mLocation_;

    /**
     * Constructs a new instance.
     * 
     * @param aLocation
     *            the location of the garage door
     */
    public GarageDoor( String aLocation )
    {
        mLocation_ = aLocation;
    }

    /**
     * Move door up
     */
    public void up()
    {
        LOG.debug( "Garage door up!" );
    }

    /**
     * Move door down
     */
    public void down()
    {
        LOG.debug( "Garage door down!" );
    }

    /**
     * Stop the door movement
     */
    public void stop()
    {
        LOG.debug( "Garge door stop!" );
    }

    /**
     * Turn light on
     */
    public void lightOn()
    {
        LOG.debug( "Light (" + mLocation_ + ") on!" );
    }

    /**
     * Turn light off
     */
    public void lightOff()
    {
        LOG.debug( "Light (" + mLocation_ + ") off!" );
    }

}
