/*
 * WildTurkey.java created on 31 Aug 2007 17:19:25 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to represent a turkey
 */
public class WildTurkey implements ITurkey {

    private static final Logger LOG = LoggerFactory.getLogger( WildTurkey.class );

    @Override
    public void fly() {
        LOG.debug( "I'm flying a short distance" );
    }

    @Override
    public void gobble() {
        LOG.debug( "Gobble gobble" );
    }

}
