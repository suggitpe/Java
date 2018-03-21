/*
 * SoldOutState.java created on 10 Sep 2007 18:13:39 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.proxy.remoteproxy.states;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.suggs.sandbox.patterns.structural.proxy.remoteproxy.GumballMachine;
import org.suggs.sandbox.patterns.structural.proxy.remoteproxy.IState;

/**
 * State to represent the gumball machine is out of gumballs
 */
public class SoldOutState implements IState {

    private static final long serialVersionUID = -6406267581898956016L;
    private static final Logger LOG = LoggerFactory.getLogger(SoldOutState.class);

    @SuppressWarnings("unused")
    public SoldOutState(GumballMachine aMachine) {
    }

    @Override
    public void dispense() {
        LOG.warn("Nothing to dispense");
    }

    @Override
    public void ejectQuarter() {
        LOG.warn("No quarter in the machine to eject");
    }

    @Override
    public void insertQuarter() {
        LOG.warn("No allowing you toi insert a quarter as the machine is empty");
    }

    @Override
    public void turnCrank() {
        LOG.warn("Sorry the machine is empty and so we cannot turn the crank");
    }

}
