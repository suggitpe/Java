/*
 * IObserver.java created on 19 Sep 2007 07:02:26 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.quackfest.observer;

/**
 * Interface defining the behaviour of quack observers
 */
public interface IObserver {

    void update( IQuackObservable aObservable );

}
