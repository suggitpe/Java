/*
 * IQuackObservable.java created on 19 Sep 2007 06:53:13 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.quackfest.observer;

/**
 * Observable interface so that the
 */
public interface IQuackObservable {

    void registerObserver( IObserver aObserver );

    void notifyObservers();

}
