/*
 * ISubject.java created on 28 Aug 2007 06:05:28 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.observer;

/**
 * This is the main subject interface
 * 
 * @author suggitpe
 * @version 1.0 28 Aug 2007
 */
public interface ISubject
{

    /**
     * Add an observer to the collection of observers
     * 
     * @param aObserver
     *            the observer to add
     */
    void registerObserver( IObserver aObserver );

    /**
     * Remove an observer from the collection of observers
     * 
     * @param aObserver
     *            the observer to remove
     */
    void removeObserver( IObserver aObserver );

    /**
     * Notify all observers of a particular change
     */
    void notifyObservers();

}
