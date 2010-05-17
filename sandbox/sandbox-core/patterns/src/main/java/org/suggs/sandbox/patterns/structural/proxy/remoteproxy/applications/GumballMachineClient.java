/*
 * GumballMachineClient.java created on 12 Sep 2007 16:47:56 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.proxy.remoteproxy.applications;

import org.suggs.sandbox.patterns.structural.proxy.remoteproxy.GumballMonitor;
import org.suggs.sandbox.patterns.structural.proxy.remoteproxy.IGumballMachineRemote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Client application that will remotely call up the monitor object.
 * 
 * @author suggitpe
 * @version 1.0 12 Sep 2007
 */
public class GumballMachineClient {

    private static final Log LOG = LogFactory.getLog( GumballMachineClient.class );

    /**
     * @param args
     */
    public static void main( String[] args ) {
        String[] locations = { "//localhost:7234/gumballMachine" };
        GumballMonitor[] monitors = new GumballMonitor[locations.length];

        for ( int i = 0; i < locations.length; ++i ) {
            try {
                IGumballMachineRemote machine = (IGumballMachineRemote) Naming.lookup( locations[i] );
                monitors[i] = new GumballMonitor( machine );
                LOG.debug( monitors[i] );
            }
            catch ( RemoteException re ) {
                LOG.error( "REmote excption caught when trying to lookup [" + locations[i] + "]" );
                re.printStackTrace();
            }
            catch ( NotBoundException nbe ) {
                LOG.error( "Failed to bind to url with name [" + locations[i] + "]" );
                nbe.printStackTrace();
            }
            catch ( MalformedURLException mue ) {
                LOG.error( "URL [" + mue.getMessage() + "] is not valid form " );
                mue.printStackTrace();
            }
        }

        for ( GumballMonitor mon : monitors ) {
            mon.report();
        }
    }

}
