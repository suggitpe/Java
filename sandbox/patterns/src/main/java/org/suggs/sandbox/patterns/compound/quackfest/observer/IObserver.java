/*
 * IObserver.java created on 19 Sep 2007 07:02:26 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.quackfest.observer;

/**
 * Interface defining the behaviour of quack observers
 * 
 * @author suggitpe
 * @version 1.0 19 Sep 2007
 */
public interface IObserver {

    /**
     * Update with the new state of the quackable
     * 
     * @param aObservable
     */
    void update( IQuackObservable aObservable );

}
