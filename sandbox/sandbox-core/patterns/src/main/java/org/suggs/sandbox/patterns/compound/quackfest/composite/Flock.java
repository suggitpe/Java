/*
 * Flock.java created on 19 Sep 2007 06:34:29 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.quackfest.composite;

import org.suggs.sandbox.patterns.compound.quackfest.IQuackable;
import org.suggs.sandbox.patterns.compound.quackfest.observer.IObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite object to hold all of the quackable birdlife
 * 
 * @author suggitpe
 * @version 1.0 19 Sep 2007
 */
public class Flock implements IQuackable {

    private List<IQuackable> flock = new ArrayList<IQuackable>();

    /**
     * Add another quackable to the existing list
     * 
     * @param aQuackable
     *            the quackable object to add
     */
    public void add( IQuackable aQuackable ) {
        flock.add( aQuackable );
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.quackfest.IQuackable#quack()
     */
    public void quack() {
        for ( IQuackable q : flock ) {
            q.quack();
        }
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.quackfest.observer.IQuackObservable#notifyObservers()
     */
    public void notifyObservers() {
        for ( IQuackable q : flock ) {
            q.notifyObservers();
        }
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.quackfest.observer.IQuackObservable#registerObserver(org.suggs.sandbox.patterns.compound.quackfest.observer.IObserver)
     */
    public void registerObserver( IObserver observer ) {
        for ( IQuackable q : flock ) {
            q.registerObserver( observer );
        }
    }

}
