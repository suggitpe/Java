/*
 * NoQuarterState.java created on 10 Sep 2007 18:14:38 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.state.states;

import org.suggs.sandbox.patterns.behavioural.state.GumballMachine;
import org.suggs.sandbox.patterns.behavioural.state.IState;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * State to represent when there is no quarter in the gumball machine.
 * 
 * @author suggitpe
 * @version 1.0 10 Sep 2007
 */
public class NoQuarterState implements IState
{

    private static final Log LOG = LogFactory.getLog( NoQuarterState.class );

    private GumballMachine mGumballMachine_;

    /**
     * Constructs a new instance.
     * 
     * @param aMachine
     *            the gumball machine
     */
    public NoQuarterState( GumballMachine aMachine )
    {
        mGumballMachine_ = aMachine;
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.IState#dispense()
     */
    public void dispense()
    {
        LOG.warn( "you need to pay first" );
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.IState#ejectQuarter()
     */
    public void ejectQuarter()
    {
        LOG.warn( "No quarter in the machine" );
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.IState#insertQuarter()
     */
    public void insertQuarter()
    {
        LOG.info( "You have inserted a quarter" );
        mGumballMachine_.setState( mGumballMachine_.getHasQuarterState() );
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.IState#turnCrank()
     */
    public void turnCrank()
    {
        LOG.warn( "You have turned but there is no quarter in the machine" );
    }

}
