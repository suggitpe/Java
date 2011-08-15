/*
 * Quackologist.java created on 19 Sep 2007 17:56:21 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.quackfest.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Observer of quackObservables
 * 
 * @author suggitpe
 * @version 1.0 19 Sep 2007
 */
public class Quackologist implements IObserver {

    private static final Logger LOG = LoggerFactory.getLogger( Quackologist.class );

    /**
     * @see org.suggs.sandbox.patterns.compound.quackfest.observer.IObserver#update(org.suggs.sandbox.patterns.compound.quackfest.observer.IQuackObservable)
     */
    @Override
    public void update( IQuackObservable quacker ) {
        LOG.debug( "Quackologist: [" + quacker.getClass().getSimpleName() + "] just quacked" );
    }

}
