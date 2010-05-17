/*
 * CdPlayer.java created on 3 Sep 2007 06:56:24 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.facade.subsystem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class to encapsulate a CD player
 * 
 * @author suggitpe
 * @version 1.0 3 Sep 2007
 */
public class CdPlayer {

    private static final Log LOG = LogFactory.getLog( CdPlayer.class );

    public void off() {
        LOG.debug( "CD player off" );
    }

    public void eject() {
        LOG.debug( "CD player eject" );
    }

    public void stop() {
        LOG.debug( "CD player stop" );
    }

}
