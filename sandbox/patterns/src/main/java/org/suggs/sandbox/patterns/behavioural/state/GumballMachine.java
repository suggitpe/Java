/*
 * GumballMachine.java created on 10 Sep 2007 17:58:21 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.state;

import org.suggs.sandbox.patterns.behavioural.state.states.HasQuarterState;
import org.suggs.sandbox.patterns.behavioural.state.states.NoQuarterState;
import org.suggs.sandbox.patterns.behavioural.state.states.SoldOutState;
import org.suggs.sandbox.patterns.behavioural.state.states.SoldState;
import org.suggs.sandbox.patterns.behavioural.state.states.WinnerState;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * State machine for a gumball machine object
 * 
 * @author suggitpe
 * @version 1.0 10 Sep 2007
 */
public class GumballMachine
{

    private static final Log LOG = LogFactory.getLog( GumballMachine.class );

    private IState mSoldOutState_;
    private IState mNoQuarterState_;
    private IState mHasQuarterState_;
    private IState mSoldState_;
    private IState mWinnerState_;

    private IState mState_ = mSoldOutState_;
    int mCount_ = 0;

    /**
     * Constructs a new instance.
     * 
     * @param aCount
     *            the number of gumballs in the system
     */
    public GumballMachine( int aCount )
    {
        mSoldOutState_ = new SoldOutState( this );
        mNoQuarterState_ = new NoQuarterState( this );
        mHasQuarterState_ = new HasQuarterState( this );
        mSoldState_ = new SoldState( this );
        mWinnerState_ = new WinnerState( this );

        mCount_ = aCount;
        if ( mCount_ > 0 )
        {
            mState_ = mNoQuarterState_;
        }
    }

    /**
     * Insert the quarter
     */
    public void insertQuarter()
    {
        mState_.insertQuarter();
    }

    /**
     * Eject the quarter
     */
    public void ejectQuarter()
    {
        mState_.ejectQuarter();
    }

    /**
     * Turns the crank on the machine
     */
    public void turnCrank()
    {
        mState_.turnCrank();
        mState_.dispense();
    }

    /**
     * Releases a gum ball into the slot for the buyer to collect
     */
    public void releaseBall()
    {
        LOG.info( "A gumball comes rolling into the slot" );
        if ( mCount_ != 0 )
        {
            --mCount_;
        }
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return new StringBuffer( "Gumball Machine: gumball count=[" + mCount_ + "];  state=[" + mState_.getClass().getSimpleName() + "]" ).toString();
    }

    // =================
    // GETTERS & SETTERS

    /**
     * Setter for the state of the machine
     * 
     * @param aState
     *            the state to set
     */
    public void setState( IState aState )
    {
        mState_ = aState;
    }

    /**
     * getter for the count
     * 
     * @return the number of gumballs left
     */
    public int getCount()
    {
        return mCount_;
    }

    /**
     * Getter for the sold out state
     * 
     * @return the sold out state
     */
    public IState getSoldOutState()
    {
        return mSoldOutState_;
    }

    /**
     * Getter for the no quarter state
     * 
     * @return the no quarter state
     */
    public IState getNoQuarterState()
    {
        return mNoQuarterState_;
    }

    /**
     * Getter for the has quarter state
     * 
     * @return the has quarter state
     */
    public IState getHasQuarterState()
    {
        return mHasQuarterState_;
    }

    /**
     * Getter for the sold state
     * 
     * @return the sold state
     */
    public IState getSoldState()
    {
        return mSoldState_;
    }

    /**
     * Getter for the winner state
     * 
     * @return the winner state
     */
    public IState getWinnerState()
    {
        return mWinnerState_;
    }

}
