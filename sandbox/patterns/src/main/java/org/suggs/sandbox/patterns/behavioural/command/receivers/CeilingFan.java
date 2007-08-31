/*
 * CeilingFan.java created on 30 Aug 2007 17:07:48 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.command.receivers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class to encapsulate a Ceiling fan
 * 
 * @author suggitpe
 * @version 1.0 30 Aug 2007
 */
public class CeilingFan
{

    private static final Log LOG = LogFactory.getLog( CeilingFan.class );

    private String mSpeed_;
    private String mLocation_;

    /**
     * Constructs a new instance.
     * 
     * @param aLocation
     *            the location of the ceiling fan
     */
    public CeilingFan( String aLocation )
    {
        mLocation_ = aLocation;
    }

    public void high()
    {
        LOG.debug( "Ceiling Fan (" + mLocation_ + ") high!" );
        mSpeed_ = "HIGH";
    }

    public void medium()
    {
        LOG.debug( "Ceiling Fan (" + mLocation_ + ") medium!" );
        mSpeed_ = "MEDIUM";
    }

    public void low()
    {
        LOG.debug( "Ceiling Fan (" + mLocation_ + ") low!" );
        mSpeed_ = "LOW";
    }

    public void off()
    {
        LOG.debug( "Ceiling Fan (" + mLocation_ + ") off!" );
        mSpeed_ = "OFF";
    }

    public String getSpeed()
    {
        return mSpeed_;
    }

}
