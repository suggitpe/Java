/*
 * MallardDuck.java created on 31 Aug 2007 17:16:59 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.adapter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A mallard duck class
 * 
 * @author suggitpe
 * @version 1.0 31 Aug 2007
 */
public class MallardDuck implements IDuck {

    private static final Log LOG = LogFactory.getLog( MallardDuck.class );

    /**
     * @see org.suggs.sandbox.patterns.structural.adapter.IDuck#fly()
     */
    @Override
    public void fly() {
        LOG.debug( "I'm flying" );
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.adapter.IDuck#quack()
     */
    @Override
    public void quack() {
        LOG.debug( "Quack!" );
    }

}
