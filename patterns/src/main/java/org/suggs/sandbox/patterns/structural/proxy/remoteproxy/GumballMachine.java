/*
 * GumballMachine.java created on 10 Sep 2007 17:58:21 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.proxy.remoteproxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.suggs.sandbox.patterns.structural.proxy.remoteproxy.states.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * State machine for a gumball machine object. This is a remote object so that clients can interface with it.
 */
public class GumballMachine extends UnicastRemoteObject implements IGumballMachineRemote {

    private static final long serialVersionUID = 4200893775209124128L;
    private static final Logger LOG = LoggerFactory.getLogger(GumballMachine.class);

    private IState soldOutState;
    private IState noQuarterState;
    private IState hasQuarterState;
    private IState soldState;
    private IState winnerState;

    private IState state = soldOutState;
    private int count = 0;
    private String location;

    public GumballMachine(String aLocation, int aCount) throws RemoteException {
        soldOutState = new SoldOutState(this);
        noQuarterState = new NoQuarterState(this);
        hasQuarterState = new HasQuarterState(this);
        soldState = new SoldState(this);
        winnerState = new WinnerState(this);

        location = aLocation;
        count = aCount;
        if (count > 0) {
            state = noQuarterState;
        }
    }

    public void insertQuarter() {
        state.insertQuarter();
    }

    public void ejectQuarter() {
        state.ejectQuarter();
    }

    public void turnCrank() {
        state.turnCrank();
        state.dispense();
    }

    public void releaseBall() {
        LOG.info("A gumball comes rolling into the slot");
        if (count != 0) {
            --count;
        }
    }

    @Override
    public String toString() {
        return new StringBuilder("Gumball Machine: gumball count=[" + count + "];  state=["
                + state.getClass().getSimpleName() + "]").toString();
    }

    public void setState(IState aState) {
        state = aState;
    }

    @Override
    public IState getState() {
        return state;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public String getLocation() {
        return location;
    }

    public IState getSoldOutState() {
        return soldOutState;
    }

    public IState getNoQuarterState() {
        return noQuarterState;
    }

    public IState getHasQuarterState() {
        return hasQuarterState;
    }

    public IState getSoldState() {
        return soldState;
    }

    public IState getWinnerState() {
        return winnerState;
    }
}
