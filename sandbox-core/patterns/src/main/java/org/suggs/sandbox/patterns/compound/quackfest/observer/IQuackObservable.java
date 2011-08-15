/*
 * IQuackObservable.java created on 19 Sep 2007 06:53:13 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.quackfest.observer;

/**
 * Observable interface so that the
 * 
 * @author suggitpe
 * @version 1.0 19 Sep 2007
 */
public interface IQuackObservable {

    /**
     * Registers an observer with the observable
     * 
     * @param aObserver
     *            the observer to add
     */
    void registerObserver( IObserver aObserver );

    /**
     * Notifies all the obsrevers of a change
     */
    void notifyObservers();

}
