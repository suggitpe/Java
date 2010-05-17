/*
 * CommandTestCase.java created on 29 Aug 2007 06:33:36 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.state;

import org.suggs.sandbox.patterns.AbstractPatternTestCase;
import org.suggs.sandbox.patterns.behavioural.state.gumball.GumballMachine;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * Test case for the command pattern
 * 
 * @author suggitpe
 * @version 1.0 29 Aug 2007
 */
public class StateGumballTestCase extends AbstractPatternTestCase {

    private static final Log LOG = LogFactory.getLog( StateGumballTestCase.class );

    /**
     * Standard test to test the gumball state machine
     */
    @Test
    public void testGumballMachine() {
        GumballMachine gm = new GumballMachine( 5 );

        LOG.debug( gm );
        LOG.debug( "***" );

        gm.insertQuarter();
        gm.turnCrank();
        LOG.debug( gm );
        LOG.debug( "***" );

        gm.turnCrank();
        LOG.debug( gm );
        LOG.debug( "***" );

        gm.insertQuarter();
        LOG.debug( gm );
        LOG.debug( "***" );

        gm.ejectQuarter();
        LOG.debug( gm );
        LOG.debug( "***" );

        gm.insertQuarter();
        gm.turnCrank();
        LOG.debug( "***" );

        gm.insertQuarter();
        gm.turnCrank();
        LOG.debug( gm );
        LOG.debug( "***" );

        gm.insertQuarter();
        gm.turnCrank();
        LOG.debug( "***" );

        gm.insertQuarter();
        gm.turnCrank();
        LOG.debug( gm );
        LOG.debug( "***" );
    }

    @Test
    public void testBadCrankTurn() {
        GumballMachine gm = new GumballMachine( 5 );
        gm.turnCrank();
    }
}
