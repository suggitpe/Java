/*
 * GumballMachineClient.java created on 12 Sep 2007 16:47:56 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.proxy.remoteproxy.applications;

import org.suggs.sandbox.patterns.structural.proxy.remoteproxy.GumballMonitor;
import org.suggs.sandbox.patterns.structural.proxy.remoteproxy.IGumballMachineRemote;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Client application that will remotely call up the monitor object.
 * 
 * @author suggitpe
 * @version 1.0 12 Sep 2007
 */
public final class GumballMachineClient {

    private static final Logger LOG = LoggerFactory.getLogger( GumballMachineClient.class );

    private GumballMachineClient() {}

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
                LOG.debug( monitors[i].toString() );
            }
            catch ( IOException ioe ) {
                LOG.error( "REmote excption caught when trying to lookup [" + locations[i] + "]", ioe );
            }
            catch ( NotBoundException nbe ) {
                LOG.error( "Failed to bind to url with name [" + locations[i] + "]", nbe );
            }
        }

        for ( GumballMonitor mon : monitors ) {
            mon.report();
        }
    }

}
