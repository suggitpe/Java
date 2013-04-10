/*
 * TheatreLights.java created on 3 Sep 2007 06:56:42 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.facade.subsystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to encapsulate the lights within a theatre
 */
public class TheatreLights {

    private static final Logger LOG = LoggerFactory.getLogger(TheatreLights.class);

    public void dim(int i) {
        LOG.debug("Dimming lights to [" + i + "]");
    }

    public void off() {
        LOG.debug("Lights off");
    }

}
