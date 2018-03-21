/*
 * PopcornMachine.java created on 3 Sep 2007 06:56:58 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.facade.subsystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to encapsulate the popcorn machine
 */
public class PopcornMachine {

    private static final Logger LOG = LoggerFactory.getLogger(PopcornMachine.class);

    public void pop() {
        LOG.debug("popping ....");
    }

    public void on() {
        LOG.debug("Popcorn machine on");
    }

    public void off() {
        LOG.debug("Popcorn machine off");
    }

}
