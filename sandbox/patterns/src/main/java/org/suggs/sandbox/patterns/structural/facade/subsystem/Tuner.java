/*
 * Tuner.java created on 3 Sep 2007 06:56:08 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.facade.subsystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to encapsulate a tuner
 */
public class Tuner {

    private static final Logger LOG = LoggerFactory.getLogger(Tuner.class);

    public void off() {
        LOG.debug("Tuner off");
    }

}
