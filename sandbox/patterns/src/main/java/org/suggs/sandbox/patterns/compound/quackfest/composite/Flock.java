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
 */
public class Flock implements IQuackable {

    private List<IQuackable> flock = new ArrayList<IQuackable>();

    public void add( IQuackable aQuackable ) {
        flock.add( aQuackable );
    }

    @Override
    public void quack() {
        for ( IQuackable q : flock ) {
            q.quack();
        }
    }

    @Override
    public void notifyObservers() {
        for ( IQuackable q : flock ) {
            q.notifyObservers();
        }
    }

    @Override
    public void registerObserver( IObserver observer ) {
        for ( IQuackable q : flock ) {
            q.registerObserver( observer );
        }
    }

}
