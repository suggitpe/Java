/*
 * CommandTestCase.java created on 29 Aug 2007 06:33:36 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural;

import org.suggs.sandbox.patterns.AbstractPatternTestCase;
import org.suggs.sandbox.patterns.behavioural.state.GumballMachine;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Test case for the command pattern
 * 
 * @author suggitpe
 * @version 1.0 29 Aug 2007
 */
public class StateTestCase extends AbstractPatternTestCase
{

    private static final Log LOG = LogFactory.getLog( StateTestCase.class );

    /**
     * @see org.suggs.sandbox.patterns.AbstractPatternTestCase#testName()
     */
    @Override
    public void testName()
    {
        LOG.info( "=====================    ============" );
        LOG.debug( "STATE PATTERN" );
    }

    /**
     * Standard test to test the gumball state machine
     */
    public void testGumballMachine()
    {
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

    public void testBadCrankTurn()
    {
        GumballMachine gm = new GumballMachine( 5 );

        gm.turnCrank();
    }

}
