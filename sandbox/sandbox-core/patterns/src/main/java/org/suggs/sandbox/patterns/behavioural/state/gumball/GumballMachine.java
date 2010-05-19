/*
 * GumballMachine.java created on 10 Sep 2007 17:58:21 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.state.gumball;

import org.suggs.sandbox.patterns.behavioural.state.gumball.states.HasQuarterState;
import org.suggs.sandbox.patterns.behavioural.state.gumball.states.NoQuarterState;
import org.suggs.sandbox.patterns.behavioural.state.gumball.states.SoldOutState;
import org.suggs.sandbox.patterns.behavioural.state.gumball.states.SoldState;
import org.suggs.sandbox.patterns.behavioural.state.gumball.states.WinnerState;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * State machine for a gumball machine object
 * 
 * @author suggitpe
 * @version 1.0 10 Sep 2007
 */
public class GumballMachine {

    private static final Log LOG = LogFactory.getLog( GumballMachine.class );

    private IState soldOutState;
    private IState noQuarterState;
    private IState hasQuarterState;
    private IState soldState;
    private IState winnerState;

    private IState mState_ = soldOutState;
    private int count = 0;

    /**
     * Constructs a new instance.
     * 
     * @param aCount
     *            the number of gumballs in the system
     */
    public GumballMachine( int aCount ) {
        soldOutState = new SoldOutState( this );
        noQuarterState = new NoQuarterState( this );
        hasQuarterState = new HasQuarterState( this );
        soldState = new SoldState( this );
        winnerState = new WinnerState( this );

        count = aCount;
        if ( count > 0 ) {
            mState_ = noQuarterState;
        }
    }

    /**
     * Insert the quarter
     */
    public void insertQuarter() {
        mState_.insertQuarter();
    }

    /**
     * Eject the quarter
     */
    public void ejectQuarter() {
        mState_.ejectQuarter();
    }

    /**
     * Turns the crank on the machine
     */
    public void turnCrank() {
        mState_.turnCrank();
        mState_.dispense();
    }

    /**
     * Releases a gum ball into the slot for the buyer to collect
     */
    public void releaseBall() {
        LOG.info( "A gumball comes rolling into the slot" );
        if ( count != 0 ) {
            --count;
        }
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new StringBuffer( "Gumball Machine: gumball count=[" + count + "];  state=["
                                 + mState_.getClass().getSimpleName() + "]" ).toString();
    }

    // =================
    // GETTERS & SETTERS

    /**
     * Setter for the state of the machine
     * 
     * @param aState
     *            the state to set
     */
    public void setState( IState aState ) {
        mState_ = aState;
    }

    /**
     * getter for the count
     * 
     * @return the number of gumballs left
     */
    public int getCount() {
        return count;
    }

    /**
     * Getter for the sold out state
     * 
     * @return the sold out state
     */
    public IState getSoldOutState() {
        return soldOutState;
    }

    /**
     * Getter for the no quarter state
     * 
     * @return the no quarter state
     */
    public IState getNoQuarterState() {
        return noQuarterState;
    }

    /**
     * Getter for the has quarter state
     * 
     * @return the has quarter state
     */
    public IState getHasQuarterState() {
        return hasQuarterState;
    }

    /**
     * Getter for the sold state
     * 
     * @return the sold state
     */
    public IState getSoldState() {
        return soldState;
    }

    /**
     * Getter for the winner state
     * 
     * @return the winner state
     */
    public IState getWinnerState() {
        return winnerState;
    }

}
