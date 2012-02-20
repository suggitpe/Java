/*
 * GumballMonitor.java created on 12 Sep 2007 07:25:20 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.proxy.remoteproxy;

import java.rmi.RemoteException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to act as the RMI client in the proxy pattern.
 * 
 * @author suggitpe
 * @version 1.0 12 Sep 2007
 */
public class GumballMonitor {

    private static final Logger LOG = LoggerFactory.getLogger( GumballMonitor.class );
    private IGumballMachineRemote gumballMachine;

    /**
     * Constructs a new instance.
     * 
     * @param aMachine
     *            the interface to the remote object
     */
    public GumballMonitor( IGumballMachineRemote aMachine ) {
        gumballMachine = aMachine;
    }

    /**
     * Get the state of the remote object
     */
    public void report() {
        try {
            LOG.debug( "Gumball machine: " + gumballMachine.getLocation() );
            LOG.debug( "Current invetory: " + gumballMachine.getCount() );
            LOG.debug( "Current state: " + gumballMachine.getState() );
        }
        catch ( RemoteException e ) {
            LOG.error( "remote exception caught when calling against the gumball machine", e );
        }
    }
}
