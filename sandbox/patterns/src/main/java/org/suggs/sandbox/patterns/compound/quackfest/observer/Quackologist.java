/*
 * Quackologist.java created on 19 Sep 2007 17:56:21 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.quackfest.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Observer of quackObservables
 */
public class Quackologist implements IObserver {

    private static final Logger LOG = LoggerFactory.getLogger( Quackologist.class );

    @Override
    public void update( IQuackObservable quacker ) {
        LOG.debug( "Quackologist: [" + quacker.getClass().getSimpleName() + "] just quacked" );
    }

}
