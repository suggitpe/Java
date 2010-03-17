/*
 * WinnerState.java created on 10 Sep 2007 18:16:37 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.proxy.remoteproxy.states;

import org.suggs.sandbox.patterns.structural.proxy.remoteproxy.GumballMachine;
import org.suggs.sandbox.patterns.structural.proxy.remoteproxy.IState;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * State to represent when the client is a winner of an extra gumball
 * 
 * @author suggitpe
 * @version 1.0 10 Sep 2007
 */
public class WinnerState implements IState
{

    private static final Log LOG = LogFactory.getLog( WinnerState.class );

    private transient GumballMachine mGumballMachine_;

    /**
     * Constructs a new instance.
     * 
     * @param aMachine
     *            the gumball machine
     */
    public WinnerState( GumballMachine aMachine )
    {
        mGumballMachine_ = aMachine;
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.gumball.IState#dispense()
     */
    public void dispense()
    {
        // this is where we get the good news
        LOG.info( "YOU'RE A WINNER! You get two gumballs for the price of one." );
        mGumballMachine_.releaseBall();
        if ( mGumballMachine_.getCount() == 0 )
        {
            LOG.warn( "Actually we would love to give you an additional gumball however we have none left to give" );
            mGumballMachine_.setState( mGumballMachine_.getSoldOutState() );
        }
        else
        {
            mGumballMachine_.releaseBall();
            if ( mGumballMachine_.getCount() > 0 )
            {
                mGumballMachine_.setState( mGumballMachine_.getNoQuarterState() );
            }
            else
            {
                LOG.warn( "Ooops we have no run out of gumballs" );
                mGumballMachine_.setState( mGumballMachine_.getSoldOutState() );
            }
        }
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.gumball.IState#ejectQuarter()
     */
    public void ejectQuarter()
    {
        LOG.warn( "WE are way beyond ejecting quarters now" );
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.gumball.IState#insertQuarter()
     */
    public void insertQuarter()
    {
        LOG.warn( "In the middle of a vend at the moment" );
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.state.gumball.IState#turnCrank()
     */
    public void turnCrank()
    {
        LOG.warn( "Crank has already been truned" );
    }

}
