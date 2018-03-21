/*
 * Observable.java created on 19 Sep 2007 06:56:45 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.quackfest.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to encapsulate the observable nature of the quackable objects. This manages the logic for all of the
 * behaviour that is required for being observabnle.
 */
public class Observable implements IQuackObservable {

    private List<IObserver> observers = new ArrayList<IObserver>();
    private IQuackObservable duck;

    public Observable( IQuackObservable aDuck ) {
        duck = aDuck;
    }

    @Override
    public void notifyObservers() {
        for ( IObserver o : observers ) {
            o.update( duck );
        }
    }

    @Override
    public void registerObserver( IObserver observer ) {
        observers.add( observer );
    }
}
