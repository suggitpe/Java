/*
 * Stereo.java created on 30 Aug 2007 17:07:01 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.command.receivers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class to encapsulate a stereo
 * 
 * @author suggitpe
 * @version 1.0 30 Aug 2007
 */
public class Stereo
{

    private static final Log LOG = LogFactory.getLog( Stereo.class );

    private String mLocation_;

    /**
     * Constructs a new instance.
     * 
     * @param aLocation
     *            the location of the Steroa
     */
    public Stereo( String aLocation )
    {
        mLocation_ = aLocation;
    }

    /** */
    public void on()
    {
        LOG.debug( "Stereo (" + mLocation_ + ") on!" );
    }

    /** */
    public void off()
    {
        LOG.debug( "Stereo (" + mLocation_ + ") off!" );
    }

    /** */
    public void setCd()
    {
        LOG.debug( "CD!" );
    }

    /** */
    public void setDvd()
    {
        LOG.debug( "DVD!" );
    }

    /** */
    public void setRadio()
    {
        LOG.debug( "Radio!" );
    }

    /**
     * @param aVolume
     */
    public void setVolume( int aVolume )
    {
        LOG.debug( "Volume to [" + aVolume + "]" );
    }

}
