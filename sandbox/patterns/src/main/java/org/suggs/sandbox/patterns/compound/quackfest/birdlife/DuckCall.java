/*
 * DuckCall.java created on 18 Sep 2007 17:49:18 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.quackfest.birdlife;

import org.suggs.sandbox.patterns.compound.quackfest.IQuackable;
import org.suggs.sandbox.patterns.compound.quackfest.observer.IObserver;
import org.suggs.sandbox.patterns.compound.quackfest.observer.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Duck call (the type that hunters use) implementation
 * 
 * @author suggitpe
 * @version 1.0 18 Sep 2007
 */
public class DuckCall implements IQuackable {

    private static final Logger LOG = LoggerFactory.getLogger( DuckCall.class );
    private Observable observable;

    /**
     * Constructs a new instance.
     */
    public DuckCall() {
        observable = new Observable( this );
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.quackfest.IQuackable#quack()
     */
    @Override
    public void quack() {
        LOG.debug( "Kwak" );
        notifyObservers();
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.quackfest.observer.IQuackObservable#notifyObservers()
     */
    @Override
    public void notifyObservers() {
        observable.notifyObservers();
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.quackfest.observer.IQuackObservable#registerObserver(org.suggs.sandbox.patterns.compound.quackfest.observer.IObserver)
     */
    @Override
    public void registerObserver( IObserver observer ) {
        observable.registerObserver( observer );
    }

}
