/*
 * NoQuarterState.java created on 10 Sep 2007 18:14:38 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.proxy.remoteproxy.states;

import org.suggs.sandbox.patterns.structural.proxy.remoteproxy.GumballMachine;
import org.suggs.sandbox.patterns.structural.proxy.remoteproxy.IState;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * State to represent when there is no quarter in the gumball machine.
 */
public class NoQuarterState implements IState {

    private static final long serialVersionUID = 4949649418578126010L;
    private static final Logger LOG = LoggerFactory.getLogger( NoQuarterState.class );
    private GumballMachine gumballMachine;

    public NoQuarterState( GumballMachine aMachine ) {
        gumballMachine = aMachine;
    }

    @Override
    public void dispense() {
        LOG.warn( "you need to pay first" );
    }

    @Override
    public void ejectQuarter() {
        LOG.warn( "No quarter in the machine" );
    }

    @Override
    public void insertQuarter() {
        LOG.info( "You have inserted a quarter" );
        gumballMachine.setState( gumballMachine.getHasQuarterState() );
    }

    @Override
    public void turnCrank() {
        LOG.warn( "You have turned but there is no quarter in the machine" );
    }

}
