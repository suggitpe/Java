/*
 * SoldState.java created on 10 Sep 2007 18:11:02 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.proxy.remoteproxy.states;

import org.suggs.sandbox.patterns.structural.proxy.remoteproxy.GumballMachine;
import org.suggs.sandbox.patterns.structural.proxy.remoteproxy.IState;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * State to represent the state where we are in the middle of the transaction and are just waiting to dispense
 * the gumball.
 */
public class SoldState implements IState {

    private static final long serialVersionUID = 2491723550946691480L;
    private static final Logger LOG = LoggerFactory.getLogger( SoldState.class );
    private GumballMachine gumballMachine;

    public SoldState( GumballMachine aMachine ) {
        gumballMachine = aMachine;
    }

    @Override
    public void dispense() {
        gumballMachine.releaseBall();
        if ( gumballMachine.getCount() > 0 ) {
            gumballMachine.setState( gumballMachine.getNoQuarterState() );
        }
        else {
            LOG.info( "Ooops no more gumballs" );
            gumballMachine.setState( gumballMachine.getSoldOutState() );
        }
    }

    @Override
    public void ejectQuarter() {
        LOG.warn( "Sorry but we have gone past that stage in the sale" );
    }

    @Override
    public void insertQuarter() {
        LOG.warn( "Please wait we are already giving you a gumball" );
    }

    @Override
    public void turnCrank() {
        LOG.warn( "Turning twice will not get any more gumballs" );
    }

}
