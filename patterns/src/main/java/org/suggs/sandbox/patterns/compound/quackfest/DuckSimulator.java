/*
 * DuckSumulator.java created on 18 Sep 2007 17:54:44 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.quackfest;

import org.suggs.sandbox.patterns.compound.quackfest.adapter.GooseAdapter;
import org.suggs.sandbox.patterns.compound.quackfest.birdlife.Goose;
import org.suggs.sandbox.patterns.compound.quackfest.composite.Flock;
import org.suggs.sandbox.patterns.compound.quackfest.decorator.QuackCounter;
import org.suggs.sandbox.patterns.compound.quackfest.factory.AbstractDuckFactory;
import org.suggs.sandbox.patterns.compound.quackfest.observer.Quackologist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simulator for a duck
 */
public class DuckSimulator {

    private static final Logger LOG = LoggerFactory.getLogger( DuckSimulator.class );

    public void simulate( AbstractDuckFactory aFact ) {

        Flock duckFlock = new Flock();
        duckFlock.add( aFact.createRedheadDuck() );
        duckFlock.add( aFact.createDuckCall() );
        duckFlock.add( aFact.createRubberDuck() );
        duckFlock.add( new GooseAdapter( new Goose() ) );

        Flock mallardFlock = new Flock();
        mallardFlock.add( aFact.createMallardDuck() );
        mallardFlock.add( aFact.createMallardDuck() );
        mallardFlock.add( aFact.createMallardDuck() );
        mallardFlock.add( aFact.createMallardDuck() );

        duckFlock.add( mallardFlock );

        LOG.debug( "Duck simulator: simulating a flock of mallards" );
        simulate( mallardFlock );

        Quackologist observer = new Quackologist();
        duckFlock.registerObserver( observer );

        LOG.debug( "Duck simulator: simulating a flock of ducks including flock of mallards" );
        simulate( duckFlock );

        // not counting the goose here
        LOG.debug( "Total number of quacks was [" + QuackCounter.getQuacks() + "]" );
    }

    public void simulate( IQuackable aQuackable ) {
        aQuackable.quack();
    }

}
