/*
 * IQuackable.java created on 18 Sep 2007 17:47:27 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.quackfest;

import org.suggs.sandbox.patterns.compound.quackfest.observer.IQuackObservable;

/**
 * Interface for classes with the behaviour to quack (as in the duck).
 * 
 * @author suggitpe
 * @version 1.0 18 Sep 2007
 */
public interface IQuackable extends IQuackObservable
{

    /**
     * Make a noise like a duck.
     */
    void quack();

}
