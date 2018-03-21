/*
 * MallardDuck.java created on 18 Sep 2007 17:49:18 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.quackfest.birdlife;

import org.suggs.sandbox.patterns.compound.quackfest.IQuackable;
import org.suggs.sandbox.patterns.compound.quackfest.observer.IObserver;
import org.suggs.sandbox.patterns.compound.quackfest.observer.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Mallard duck implementation
 */
public class MallardDuck implements IQuackable {

    private static final Logger LOG = LoggerFactory.getLogger( MallardDuck.class );
    private Observable observable;

    public MallardDuck() {
        observable = new Observable( this );
    }

    @Override
    public void quack() {
        LOG.debug( "Quack" );
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
