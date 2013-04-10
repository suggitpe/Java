/*
 * IQuackable.java created on 18 Sep 2007 17:47:27 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.quackfest;

import org.suggs.sandbox.patterns.compound.quackfest.observer.IQuackObservable;

/**
 * Interface for classes with the behaviour to quack (as in the duck).
 */
public interface IQuackable extends IQuackObservable {

    void quack();

}
