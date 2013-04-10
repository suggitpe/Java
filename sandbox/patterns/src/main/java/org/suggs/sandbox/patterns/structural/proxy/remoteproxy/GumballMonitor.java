/*
 * GumballMonitor.java created on 12 Sep 2007 07:25:20 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.proxy.remoteproxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.RemoteException;

/**
 * Class to act as the RMI client in the proxy pattern.
 */
public class GumballMonitor {

    private static final Logger LOG = LoggerFactory.getLogger(GumballMonitor.class);
    private IGumballMachineRemote gumballMachine;

    public GumballMonitor(IGumballMachineRemote aMachine) {
        gumballMachine = aMachine;
    }

    public void report() {
        try {
            LOG.debug("Gumball machine: " + gumballMachine.getLocation());
            LOG.debug("Current invetory: " + gumballMachine.getCount());
            LOG.debug("Current state: " + gumballMachine.getState());
        } catch (RemoteException e) {
            LOG.error("remote exception caught when calling against the gumball machine", e);
        }
    }
}
