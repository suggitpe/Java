/*
 * CountingDuckFactory.java created on 18 Sep 2007 18:22:04 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.quackfest.factory;

import org.suggs.sandbox.patterns.compound.quackfest.IQuackable;
import org.suggs.sandbox.patterns.compound.quackfest.birdlife.DuckCall;
import org.suggs.sandbox.patterns.compound.quackfest.birdlife.MallardDuck;
import org.suggs.sandbox.patterns.compound.quackfest.birdlife.RedheadDuck;
import org.suggs.sandbox.patterns.compound.quackfest.birdlife.RubberDuck;
import org.suggs.sandbox.patterns.compound.quackfest.decorator.QuackCounter;

/**
 * A factory to create counting ducks with
 * 
 * @author suggitpe
 * @version 1.0 18 Sep 2007
 */
public class CountingDuckFactory extends AbstractDuckFactory
{

    /**
     * @see org.suggs.sandbox.patterns.compound.quackfest.factory.AbstractDuckFactory#createDuckCall()
     */
    @Override
    public IQuackable createDuckCall()
    {
        return new QuackCounter( new DuckCall() );
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.quackfest.factory.AbstractDuckFactory#createMallardDuck()
     */
    @Override
    public IQuackable createMallardDuck()
    {
        return new QuackCounter( new MallardDuck() );
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.quackfest.factory.AbstractDuckFactory#createRedheadDuck()
     */
    @Override
    public IQuackable createRedheadDuck()
    {
        return new QuackCounter( new RedheadDuck() );
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.quackfest.factory.AbstractDuckFactory#createRubberDuck()
     */
    @Override
    public IQuackable createRubberDuck()
    {
        return new QuackCounter( new RubberDuck() );
    }

}
