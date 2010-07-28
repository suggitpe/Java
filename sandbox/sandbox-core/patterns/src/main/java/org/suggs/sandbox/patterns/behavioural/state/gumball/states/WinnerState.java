/*
 * WinnerState.java created on 10 Sep 2007 18:16:37 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.state.gumball.states;

import org.suggs.sandbox.patterns.behavioural.state.gumball.GumballMachine;
import org.suggs.sandbox.patterns.behavioural.state.gumball.IState;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * State to represent when the client is a winner of an extra gumball
 * 
 * @author suggitpe
 * @version 1.0 10 Sep 2007
 */
public class WinnerState implements IState {

    private static final Log LOG = LogFactory.getLog( WinnerState.class );
    private GumballMachine gumballMachine;

    /**
     * Constructs a new instance.
     * 
     * @param aMachine
     *            the gumball machine
     */
    public WinnerState( GumballMachine aMachine ) {
        gumballMachine = aMachine;
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.gumball.IState#dispense()
     */
    @Override
    public void dispense() {
        // this is where we get the good news
        LOG.info( "YOU'RE A WINNER! You get two gumballs for the price of one." );
        gumballMachine.releaseBall();
        if ( gumballMachine.getCount() == 0 ) {
            LOG.warn( "Actually we would love to give you an additional gumball however we have none left to give" );
            gumballMachine.setState( gumballMachine.getSoldOutState() );
        }
        else {
            gumballMachine.releaseBall();
            if ( gumballMachine.getCount() > 0 ) {
                gumballMachine.setState( gumballMachine.getNoQuarterState() );
            }
            else {
                LOG.warn( "Ooops we have no run out of gumballs" );
                gumballMachine.setState( gumballMachine.getSoldOutState() );
            }
        }
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.gumball.IState#ejectQuarter()
     */
    @Override
    public void ejectQuarter() {
        LOG.warn( "WE are way beyond ejecting quarters now" );
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.gumball.IState#insertQuarter()
     */
    @Override
    public void insertQuarter() {
        LOG.warn( "In the middle of a vend at the moment" );
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.gumball.IState#turnCrank()
     */
    @Override
    public void turnCrank() {
        LOG.warn( "Crank has already been truned" );
    }

}
