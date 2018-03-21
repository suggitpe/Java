/*
 * CdPlayer.java created on 3 Sep 2007 06:56:24 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.facade.subsystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to encapsulate a CD player
 */
public class CdPlayer {

    private static final Logger LOG = LoggerFactory.getLogger(CdPlayer.class);

    public void off() {
        LOG.debug("CD player off");
    }

    public void eject() {
        LOG.debug("CD player eject");
    }

    public void stop() {
        LOG.debug("CD player stop");
    }

}
