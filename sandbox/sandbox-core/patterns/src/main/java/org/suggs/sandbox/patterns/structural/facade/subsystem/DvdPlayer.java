/*
 * DvdPlayer.java created on 3 Sep 2007 06:56:18 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.facade.subsystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to encapsulate a dvd player
 * 
 * @author suggitpe
 * @version 1.0 3 Sep 2007
 */
public class DvdPlayer {

    private static final Logger LOG = LoggerFactory.getLogger( DvdPlayer.class );

    public void play( String movie ) {
        LOG.debug( "Dvd player playiny [" + movie + "]" );
    }

    public void on() {
        LOG.debug( "Dvd player on" );
    }

    public void stop() {
        LOG.debug( "Dvd player stop" );
    }

    public void eject() {
        LOG.debug( "Dvd player eject" );
    }

    public void off() {
        LOG.debug( "Dvd player off" );
    }

}
