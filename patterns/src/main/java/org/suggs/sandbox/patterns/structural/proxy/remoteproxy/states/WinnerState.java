/*
 * WinnerState.java created on 10 Sep 2007 18:16:37 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.proxy.remoteproxy.states;

import org.suggs.sandbox.patterns.structural.proxy.remoteproxy.GumballMachine;
import org.suggs.sandbox.patterns.structural.proxy.remoteproxy.IState;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * State to represent when the client is a winner of an extra gumball
 */
public class WinnerState implements IState {

    private static final long serialVersionUID = 1610977684883232090L;
    private static final Logger LOG = LoggerFactory.getLogger( WinnerState.class );
    private GumballMachine gumballMachine;

    public WinnerState( GumballMachine aMachine ) {
        gumballMachine = aMachine;
    }

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

    @Override
    public void ejectQuarter() {
        LOG.warn( "WE are way beyond ejecting quarters now" );
    }

    @Override
    public void insertQuarter() {
        LOG.warn( "In the middle of a vend at the moment" );
    }

    @Override
    public void turnCrank() {
        LOG.warn( "Crank has already been truned" );
    }

}
