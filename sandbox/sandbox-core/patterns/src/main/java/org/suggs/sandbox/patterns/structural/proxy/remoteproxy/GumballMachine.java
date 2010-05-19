/*
 * GumballMachine.java created on 10 Sep 2007 17:58:21 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.proxy.remoteproxy;

import org.suggs.sandbox.patterns.structural.proxy.remoteproxy.states.HasQuarterState;
import org.suggs.sandbox.patterns.structural.proxy.remoteproxy.states.NoQuarterState;
import org.suggs.sandbox.patterns.structural.proxy.remoteproxy.states.SoldOutState;
import org.suggs.sandbox.patterns.structural.proxy.remoteproxy.states.SoldState;
import org.suggs.sandbox.patterns.structural.proxy.remoteproxy.states.WinnerState;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * State machine for a gumball machine object. This is a remote object so that clients can interface with it.
 * 
 * @author suggitpe
 * @version 1.0 10 Sep 2007
 */
public class GumballMachine extends UnicastRemoteObject implements IGumballMachineRemote {

    private static final Log LOG = LogFactory.getLog( GumballMachine.class );

    private IState soldOutState;
    private IState noQuarterState;
    private IState hasQuarterState;
    private IState soldState;
    private IState winnerState;

    private IState state = soldOutState;
    private int count = 0;
    private String location;

    /**
     * Constructs a new instance.
     * 
     * @param aCount
     *            the number of gumballs in the system
     * @param aLocation
     *            the location of the gumball machine
     * @throws RemoteException
     *             if there is a connection error
     */
    public GumballMachine( String aLocation, int aCount ) throws RemoteException {
        soldOutState = new SoldOutState( this );
        noQuarterState = new NoQuarterState( this );
        hasQuarterState = new HasQuarterState( this );
        soldState = new SoldState( this );
        winnerState = new WinnerState( this );

        location = aLocation;
        count = aCount;
        if ( count > 0 ) {
            state = noQuarterState;
        }
    }

    /**
     * Insert the quarter
     */
    public void insertQuarter() {
        state.insertQuarter();
    }

    /**
     * Eject the quarter
     */
    public void ejectQuarter() {
        state.ejectQuarter();
    }

    /**
     * Turns the crank on the machine
     */
    public void turnCrank() {
        state.turnCrank();
        state.dispense();
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
                                 + state.getClass().getSimpleName() + "]" ).toString();
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
        state = aState;
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.proxy.remoteproxy.IGumballMachineRemote#getState()
     */
    public IState getState() {
        return state;
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.proxy.remoteproxy.IGumballMachineRemote#getCount()
     */
    public int getCount() {
        return count;
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.proxy.remoteproxy.IGumballMachineRemote#getLocation()
     */
    public String getLocation() {
        return location;
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
