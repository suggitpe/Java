/*
 * HasQuarterState.java created on 10 Sep 2007 18:15:47 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.proxy.remoteproxy.states;

import org.suggs.sandbox.patterns.structural.proxy.remoteproxy.GumballMachine;
import org.suggs.sandbox.patterns.structural.proxy.remoteproxy.IState;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * State to represent when the gumball machine has a quarter.
 * 
 * @author suggitpe
 * @version 1.0 10 Sep 2007
 */
public class HasQuarterState implements IState {

    private static final long serialVersionUID = -3183850881728001111L;
    private static final Logger LOG = LoggerFactory.getLogger( HasQuarterState.class );
    private GumballMachine gumballMachine;
    private Random randomWinner = new Random( System.currentTimeMillis() );

    /**
     * Constructs a new instance.
     * 
     * @param aMachine
     *            the gumball machine
     */
    public HasQuarterState( GumballMachine aMachine ) {
        gumballMachine = aMachine;
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.gumball.IState#dispense()
     */
    @Override
    public void dispense() {
        LOG.warn( "No gumball dispensed" );
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.gumball.IState#ejectQuarter()
     */
    @Override
    public void ejectQuarter() {
        LOG.info( "Quarter returned" );
        gumballMachine.setState( gumballMachine.getNoQuarterState() );
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.gumball.IState#insertQuarter()
     */
    @Override
    public void insertQuarter() {
        LOG.warn( "You cannot insert another quarter" );
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.gumball.IState#turnCrank()
     */
    @Override
    public void turnCrank() {
        LOG.info( "You have turned the crank" );
        int winner = randomWinner.nextInt( 10 );
        if ( ( winner == 0 ) && gumballMachine.getCount() > 1 ) {
            gumballMachine.setState( gumballMachine.getWinnerState() );
        }
        else {
            gumballMachine.setState( gumballMachine.getSoldState() );
        }
    }
}
