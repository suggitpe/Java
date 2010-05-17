/*
 * Server.java created on 18 Feb 2008 19:27:53 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.jmxbook;

import org.suggs.sandbox.jmx.jmxbook.agent.JmxBookAgent;
import org.suggs.sandbox.jmx.jmxbook.config.JmxBookConfig;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class to wrap up the execution of the jmx book agent
 * 
 * @author suggitpe
 * @version 1.0 18 Feb 2008
 */
public class JmxServer {

    private static final Log LOG = LogFactory.getLog( JmxServer.class );

    /**
     * Starts an rmi registry instance
     */
    public static void startRmiRegistry() {
        LOG.debug( "Starting the RMI registry" );

        String strPort = JmxBookConfig.getInstance().getCfgProperty( JmxBookConfig.RMI_PORT );
        try {
            LocateRegistry.createRegistry( Integer.parseInt( strPort ) );
        }
        catch ( NumberFormatException nfe ) {
            throw new IllegalArgumentException( "Failed to cvonvert RMI port number to correct type", nfe );
        }
        catch ( RemoteException re ) {
            throw new IllegalStateException( "Failed to create RMI registry", re );
        }
    }

    /**
     * @param args
     */
    public static void main( String[] args ) {
        startRmiRegistry();
        LOG.debug( "Starting Agent ..." );
        new JmxBookAgent();
    }

}
