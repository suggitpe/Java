/*
 * SoldState.java created on 10 Sep 2007 18:11:02 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.proxy.remoteproxy.states;

import org.suggs.sandbox.patterns.structural.proxy.remoteproxy.GumballMachine;
import org.suggs.sandbox.patterns.structural.proxy.remoteproxy.IState;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * State to represent the state where we are in the middle of the transaction and are just waiting to dispense
 * the gumball.
 * 
 * @author suggitpe
 * @version 1.0 10 Sep 2007
 */
public class SoldState implements IState {

    private static final long serialVersionUID = 2491723550946691480L;
    private static final Log LOG = LogFactory.getLog( SoldState.class );
    private transient GumballMachine gumballMachine;

    /**
     * Constructs a new instance.
     * 
     * @param aMachine
     *            the gumball machine
     */
    public SoldState( GumballMachine aMachine ) {
        gumballMachine = aMachine;
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.gumball.IState#dispense()
     */
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

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.gumball.IState#ejectQuarter()
     */
    public void ejectQuarter() {
        LOG.warn( "Sorry but we have gone past that stage in the sale" );
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.gumball.IState#insertQuarter()
     */
    public void insertQuarter() {
        LOG.warn( "Please wait we are already giving you a gumball" );
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.gumball.IState#turnCrank()
     */
    public void turnCrank() {
        LOG.warn( "Turning twice will not get any more gumballs" );
    }

}
