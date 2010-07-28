/*
 * MallardDuck.java created on 18 Sep 2007 17:49:18 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.quackfest.birdlife;

import org.suggs.sandbox.patterns.compound.quackfest.IQuackable;
import org.suggs.sandbox.patterns.compound.quackfest.observer.IObserver;
import org.suggs.sandbox.patterns.compound.quackfest.observer.Observable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Mallard duck implementation
 * 
 * @author suggitpe
 * @version 1.0 18 Sep 2007
 */
public class MallardDuck implements IQuackable {

    private static final Log LOG = LogFactory.getLog( MallardDuck.class );
    private Observable observable;

    /**
     * Constructs a new instance.
     */
    public MallardDuck() {
        observable = new Observable( this );
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.quackfest.IQuackable#quack()
     */
    @Override
    public void quack() {
        LOG.debug( "Quack" );
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
