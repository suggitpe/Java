/*
 * GumballMachineServer.java created on 12 Sep 2007 16:19:31 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.proxy.remoteproxy.applications;

import org.suggs.sandbox.patterns.structural.proxy.remoteproxy.GumballMachine;
import org.suggs.sandbox.patterns.structural.proxy.remoteproxy.IGumballMachineRemote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Runnable class that allows us to run the underlying server
 * implementation.
 * 
 * @author suggitpe
 * @version 1.0 12 Sep 2007
 */
public class GumballMachineServer
{

    private static final Log LOG = LogFactory.getLog( GumballMachineServer.class );

    private static final int DEF_INVENTORY = 10;
    private static final int RMI_REG_PORT = 7234;

    /**
     * @param args
     */
    public static void main( String[] args )
    {
        // check the args
        if ( args.length < 2 )
        {
            LOG.error( "No enough args passed in [GumballMachineServer <name> <inventory>]" );
            System.exit( 1 );
        }

        // get the count into an int
        int count;
        try
        {
            count = Integer.parseInt( args[1] );
        }
        catch ( NumberFormatException nfe )
        {
            LOG.error( "Bad number format passed infor the inventory [" + args[1] + "] defaulting to 10" );
            count = DEF_INVENTORY;
        }

        // start the local rmi registry
        try
        {
            LocateRegistry.createRegistry( RMI_REG_PORT );
        }
        catch ( RemoteException re )
        {
            LOG.error( "Failed to start the RMI registry on port " + RMI_REG_PORT + ", [" + re.getMessage() + "]" );
            System.exit( 1 );
        }

        // now start the actual testable server
        IGumballMachineRemote remote = null;
        try
        {
            remote = new GumballMachine( args[0], count );
            StringBuffer url = new StringBuffer( "//" ).append( args[0] ).append( ":" ).append( RMI_REG_PORT ).append( "/gumballMachine" );
            LOG.debug( "Binding gumball machine to URL [" + url.toString() + "]" );
            Naming.rebind( url.toString(), remote );
        }
        catch ( RemoteException re )
        {
            LOG.error( "Remote exception caught [" + re.getMessage() + "]" );
            re.printStackTrace();
            System.exit( 1 );
        }
        catch ( MalformedURLException mue )
        {
            LOG.error( "Badly formed URL [" + mue.getMessage() + "]" );
            System.exit( 1 );
        }

        LOG.debug( "Server up and running" );
    }
}
