/*
 * GumballMonitor.java created on 12 Sep 2007 07:25:20 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.proxy.remoteproxy;

import java.rmi.RemoteException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class to act as the RMI client in the proxy pattern.
 * 
 * @author suggitpe
 * @version 1.0 12 Sep 2007
 */
public class GumballMonitor
{

    private static final Log LOG = LogFactory.getLog( GumballMonitor.class );

    IGumballMachineRemote mGumballMachine_;

    /**
     * Constructs a new instance.
     * 
     * @param aMachine
     *            the interface to the remote object
     */
    public GumballMonitor( IGumballMachineRemote aMachine )
    {
        mGumballMachine_ = aMachine;
    }

    /**
     * Get the state of the remote object
     */
    public void report()
    {
        try
        {
            LOG.debug( "Gumball machine: " + mGumballMachine_.getLocation() );
            LOG.debug( "Current invetory: " + mGumballMachine_.getCount() );
            LOG.debug( "Current state: " + mGumballMachine_.getState() );
        }
        catch ( RemoteException e )
        {
            e.printStackTrace();
        }
    }
}
