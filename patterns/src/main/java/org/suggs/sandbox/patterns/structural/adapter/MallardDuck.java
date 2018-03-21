/*
 * MallardDuck.java created on 31 Aug 2007 17:16:59 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A mallard duck class
 */
public class MallardDuck implements IDuck {

    private static final Logger LOG = LoggerFactory.getLogger( MallardDuck.class );

    @Override
    public void fly() {
        LOG.debug( "I'm flying" );
    }

    @Override
    public void quack() {
        LOG.debug( "Quack!" );
    }

}
