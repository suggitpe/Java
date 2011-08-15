/*
 * Goose.java created on 18 Sep 2007 17:59:16 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.quackfest.birdlife;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Impl for a goose (not IQuackable)
 * 
 * @author suggitpe
 * @version 1.0 18 Sep 2007
 */
public class Goose {

    private static final Logger LOG = LoggerFactory.getLogger( Goose.class );

    public void honk() {
        LOG.debug( "Honk" );
    }

}
