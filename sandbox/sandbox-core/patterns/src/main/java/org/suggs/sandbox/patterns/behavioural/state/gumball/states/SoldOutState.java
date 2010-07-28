/*
 * SoldOutState.java created on 10 Sep 2007 18:13:39 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.state.gumball.states;

import org.suggs.sandbox.patterns.behavioural.state.gumball.GumballMachine;
import org.suggs.sandbox.patterns.behavioural.state.gumball.IState;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * State to represent the gumball machine is out of gumballs
 * 
 * @author suggitpe
 * @version 1.0 10 Sep 2007
 */
public class SoldOutState implements IState {

    private static final Log LOG = LogFactory.getLog( SoldOutState.class );

    @SuppressWarnings("unused")
    private GumballMachine gumballMachine;

    /**
     * Constructs a new instance.
     * 
     * @param aMachine
     *            the gumball machine
     */
    public SoldOutState( GumballMachine aMachine ) {
        gumballMachine = aMachine;
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.gumball.IState#dispense()
     */
    @Override
    public void dispense() {
        LOG.warn( "Nothing to dispense" );
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.gumball.IState#ejectQuarter()
     */
    @Override
    public void ejectQuarter() {
        LOG.warn( "No quarter in the machine to eject" );
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.gumball.IState#insertQuarter()
     */
    @Override
    public void insertQuarter() {
        LOG.warn( "No allowing you toi insert a quarter as the machine is empty" );
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.gumball.IState#turnCrank()
     */
    @Override
    public void turnCrank() {
        LOG.warn( "Sorry the machine is empty and so we cannot turn the crank" );
    }

}
