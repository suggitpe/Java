/*
 * Projector.java created on 3 Sep 2007 06:56:34 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.facade.subsystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to encapsulate the screen
 */
public class Projector {

    private static final Logger LOG = LoggerFactory.getLogger(Projector.class);

    public void wideScreenMode() {
        LOG.debug("Projector in wide screen");
    }

    public void on() {
        LOG.debug("Projector on");
    }

    public void off() {
        LOG.debug("Projector off");
    }

}
