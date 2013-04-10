/*
 * Amplifier.java created on 3 Sep 2007 06:56:01 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.facade.subsystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to encapsulate the amplifier
 */
public class Amplifier {

    private static final Logger LOG = LoggerFactory.getLogger(Amplifier.class);

    public void setVolume(int i) {
        LOG.debug("Amplifier volume to [" + i + "]");
    }

    public void setSurroundSound() {
        LOG.debug("Amplifier to surround sound");
    }

    public void setDvd(String movie) {
        LOG.debug("Amplifier setting DVD to [" + movie + "]");
    }

    public void on() {
        LOG.debug("Amplifier on");
    }

    public void off() {
        LOG.debug("Amplifier off");
    }

}
