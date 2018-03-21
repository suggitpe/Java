/*
 * RubberDuck.java created on 18 Sep 2007 17:49:18 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.quackfest.birdlife;

import org.suggs.sandbox.patterns.compound.quackfest.IQuackable;
import org.suggs.sandbox.patterns.compound.quackfest.observer.IObserver;
import org.suggs.sandbox.patterns.compound.quackfest.observer.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Rubber duck implementation
 */
public class RubberDuck implements IQuackable {

    private static final Logger LOG = LoggerFactory.getLogger( RubberDuck.class );
    private Observable observable;

    public RubberDuck() {
        observable = new Observable( this );
    }

    @Override
    public void quack() {
        LOG.debug( "Squeek" );
        notifyObservers();
    }

    @Override
    public void notifyObservers() {
        observable.notifyObservers();
    }

    @Override
    public void registerObserver( IObserver observer ) {
        observable.registerObserver( observer );
    }

}
