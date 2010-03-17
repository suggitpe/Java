/*
 * HasQuarterState.java created on 10 Sep 2007 18:15:47 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.state.gumball.states;

import org.suggs.sandbox.patterns.behavioural.state.gumball.GumballMachine;
import org.suggs.sandbox.patterns.behavioural.state.gumball.IState;

import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * State to represent when the gumball machine has a quarter.
 * 
 * @author suggitpe
 * @version 1.0 10 Sep 2007
 */
public class HasQuarterState implements IState
{

    private static final Log LOG = LogFactory.getLog( HasQuarterState.class );

    private GumballMachine mGumballMachine_;

    private Random mRandomWinner = new Random( System.currentTimeMillis() );

    /**
     * Constructs a new instance.
     * 
     * @param aMachine
     *            the gumball machine
     */
    public HasQuarterState( GumballMachine aMachine )
    {
        mGumballMachine_ = aMachine;
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.gumball.IState#dispense()
     */
    public void dispense()
    {
        LOG.warn( "No gumball dispensed" );
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.gumball.IState#ejectQuarter()
     */
    public void ejectQuarter()
    {
        LOG.info( "Quarter returned" );
        mGumballMachine_.setState( mGumballMachine_.getNoQuarterState() );
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.gumball.IState#insertQuarter()
     */
    public void insertQuarter()
    {
        LOG.warn( "You cannot insert another quarter" );
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.gumball.IState#turnCrank()
     */
    public void turnCrank()
    {
        LOG.info( "You have turned the crank" );
        int winner = mRandomWinner.nextInt( 10 );
        if ( ( winner == 0 ) && mGumballMachine_.getCount() > 1 )
        {
            mGumballMachine_.setState( mGumballMachine_.getWinnerState() );
        }
        else
        {
            mGumballMachine_.setState( mGumballMachine_.getSoldState() );
        }
    }
}
