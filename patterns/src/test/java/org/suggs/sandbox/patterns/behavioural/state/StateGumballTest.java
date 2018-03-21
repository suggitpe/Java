/*
 * CommandTestCase.java created on 29 Aug 2007 06:33:36 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.state;

import org.suggs.sandbox.patterns.AbstractPatternTest;
import org.suggs.sandbox.patterns.behavioural.state.gumball.GumballMachine;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test case for the command pattern
 */
public class StateGumballTest extends AbstractPatternTest {

    private static final Logger LOG = LoggerFactory.getLogger( StateGumballTest.class );

    @Test
    public void testGumballMachine() {
        GumballMachine gm = new GumballMachine( 5 );

        LOG.debug( gm.toString() );
        LOG.debug( "***" );

        gm.insertQuarter();
        gm.turnCrank();
        LOG.debug( gm.toString() );
        LOG.debug( "***" );

        gm.turnCrank();
        LOG.debug( gm.toString() );
        LOG.debug( "***" );

        gm.insertQuarter();
        LOG.debug( gm.toString() );
        LOG.debug( "***" );

        gm.ejectQuarter();
        LOG.debug( gm.toString() );
        LOG.debug( "***" );

        gm.insertQuarter();
        gm.turnCrank();
        LOG.debug( "***" );

        gm.insertQuarter();
        gm.turnCrank();
        LOG.debug( gm.toString() );
        LOG.debug( "***" );

        gm.insertQuarter();
        gm.turnCrank();
        LOG.debug( "***" );

        gm.insertQuarter();
        gm.turnCrank();
        LOG.debug( gm.toString() );
        LOG.debug( "***" );
    }

    @Test
    public void testBadCrankTurn() {
        GumballMachine gm = new GumballMachine( 5 );
        gm.turnCrank();
    }
}
