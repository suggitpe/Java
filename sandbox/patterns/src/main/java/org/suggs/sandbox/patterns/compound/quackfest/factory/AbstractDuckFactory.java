/*
 * AbstractDuckFactory.java created on 18 Sep 2007 18:18:24 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.quackfest.factory;

import org.suggs.sandbox.patterns.compound.quackfest.IQuackable;

/**
 * Abstract Duck Factory to create famillies of Ducks
 * 
 * @author suggitpe
 * @version 1.0 18 Sep 2007
 */
public abstract class AbstractDuckFactory
{

    /**
     * Create a mallard duck
     * 
     * @return a mallard duck
     */
    public abstract IQuackable createMallardDuck();

    /**
     * Create a red head duck
     * 
     * @return a read head duck
     */
    public abstract IQuackable createRedheadDuck();

    /**
     * create a duck call
     * 
     * @return a hunters duck call
     */
    public abstract IQuackable createDuckCall();

    /**
     * create a rubber duck
     * 
     * @return a bath toy
     */
    public abstract IQuackable createRubberDuck();

}
