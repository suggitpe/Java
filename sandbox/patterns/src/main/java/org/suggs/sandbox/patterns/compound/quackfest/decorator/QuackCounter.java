/*
 * QuackCounter.java created on 18 Sep 2007 18:11:03 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.quackfest.decorator;

import org.suggs.sandbox.patterns.compound.quackfest.IQuackable;
import org.suggs.sandbox.patterns.compound.quackfest.observer.IObserver;

/**
 * Decorator for IQuackable objects that will count the number of quacks induced
 * 
 * @author suggitpe
 * @version 1.0 18 Sep 2007
 */
public class QuackCounter implements IQuackable {

    private IQuackable quackable;
    private static int numQuacks;

    /**
     * Constructs a new instance.
     * 
     * @param aQuackable
     *            the quackable object to decorate
     */
    public QuackCounter( IQuackable aQuackable ) {
        quackable = aQuackable;
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.quackfest.IQuackable#quack()
     */
    @Override
    public void quack() {
        quackable.quack();
        ++numQuacks;
    }

    /**
     * Getter for the number of quacks
     * 
     * @return the number of quacks
     */
    public static int getQuacks() {
        return numQuacks;
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.quackfest.observer.IQuackObservable#notifyObservers()
     */
    @Override
    public void notifyObservers() {
        quackable.notifyObservers();
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.quackfest.observer.IQuackObservable#registerObserver(org.suggs.sandbox.patterns.compound.quackfest.observer.IObserver)
     */
    @Override
    public void registerObserver( IObserver observer ) {
        quackable.registerObserver( observer );
    }

}
