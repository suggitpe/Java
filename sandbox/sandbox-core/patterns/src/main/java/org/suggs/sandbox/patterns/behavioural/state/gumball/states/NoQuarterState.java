/*
 * NoQuarterState.java created on 10 Sep 2007 18:14:38 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.state.gumball.states;

import org.suggs.sandbox.patterns.behavioural.state.gumball.GumballMachine;
import org.suggs.sandbox.patterns.behavioural.state.gumball.IState;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * State to represent when there is no quarter in the gumball machine.
 * 
 * @author suggitpe
 * @version 1.0 10 Sep 2007
 */
public class NoQuarterState implements IState {

    private static final Logger LOG = LoggerFactory.getLogger( NoQuarterState.class );
    private GumballMachine gumballMachine;

    /**
     * Constructs a new instance.
     * 
     * @param aMachine
     *            the gumball machine
     */
    public NoQuarterState( GumballMachine aMachine ) {
        gumballMachine = aMachine;
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.gumball.IState#dispense()
     */
    @Override
    public void dispense() {
        LOG.warn( "you need to pay first" );
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.gumball.IState#ejectQuarter()
     */
    @Override
    public void ejectQuarter() {
        LOG.warn( "No quarter in the machine" );
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.gumball.IState#insertQuarter()
     */
    @Override
    public void insertQuarter() {
        LOG.info( "You have inserted a quarter" );
        gumballMachine.setState( gumballMachine.getHasQuarterState() );
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.gumball.IState#turnCrank()
     */
    @Override
    public void turnCrank() {
        LOG.warn( "You have turned but there is no quarter in the machine" );
    }

}
