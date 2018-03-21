/*
 * Screen.java created on 3 Sep 2007 06:56:51 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.facade.subsystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to encapsulate a screen
 */
public class Screen {

    private static final Logger LOG = LoggerFactory.getLogger(Screen.class);

    public void down() {
        LOG.debug("Screen down");
    }

    public void up() {
        LOG.debug("Screen up");
    }

}
