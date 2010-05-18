/*
 * RubberDuck.java created on 18 Sep 2007 17:49:18 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.quackfest.birdlife;

import org.suggs.sandbox.patterns.compound.quackfest.IQuackable;
import org.suggs.sandbox.patterns.compound.quackfest.observer.IObserver;
import org.suggs.sandbox.patterns.compound.quackfest.observer.Observable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Rubber duck implementation
 * 
 * @author suggitpe
 * @version 1.0 18 Sep 2007
 */
public class RubberDuck implements IQuackable {

    private static final Log LOG = LogFactory.getLog( RubberDuck.class );
    private Observable observable;

    /**
     * Constructs a new instance.
     */
    public RubberDuck() {
        observable = new Observable( this );
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.quackfest.IQuackable#quack()
     */
    public void quack() {
        LOG.debug( "Squeek" );
        notifyObservers();
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.quackfest.observer.IQuackObservable#notifyObservers()
     */
    public void notifyObservers() {
        observable.notifyObservers();
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.quackfest.observer.IQuackObservable#registerObserver(org.suggs.sandbox.patterns.compound.quackfest.observer.IObserver)
     */
    public void registerObserver( IObserver observer ) {
        observable.registerObserver( observer );
    }

}
