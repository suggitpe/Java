/*
 * AbstractDuckFactory.java created on 18 Sep 2007 18:18:24 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.quackfest.factory;

import org.suggs.sandbox.patterns.compound.quackfest.IQuackable;

/**
 * Abstract Duck Factory to create famillies of Ducks
 */
public abstract class AbstractDuckFactory {

    public abstract IQuackable createMallardDuck();

    public abstract IQuackable createRedheadDuck();

    public abstract IQuackable createDuckCall();

    public abstract IQuackable createRubberDuck();

}
