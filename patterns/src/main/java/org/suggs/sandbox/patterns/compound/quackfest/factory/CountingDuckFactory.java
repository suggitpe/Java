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
 */
public class CountingDuckFactory extends AbstractDuckFactory {

    @Override
    public IQuackable createDuckCall() {
        return new QuackCounter( new DuckCall() );
    }

    @Override
    public IQuackable createMallardDuck() {
        return new QuackCounter( new MallardDuck() );
    }

    @Override
    public IQuackable createRedheadDuck() {
        return new QuackCounter( new RedheadDuck() );
    }

    @Override
    public IQuackable createRubberDuck() {
        return new QuackCounter( new RubberDuck() );
    }

}
