/*
 * JmxBookAgent.java created on 13 Feb 2008 07:20:19 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.jmxbook.agent;

import org.suggs.sandbox.jmx.jmxbook.ExceptionUtil;
import org.suggs.sandbox.jmx.jmxbook.config.JmxBookConfig;

import java.io.IOException;

import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sun.jdmk.comm.HtmlAdaptorServer;

/**
 * JMX Agent for the JmxBook project.
 * 
 * @author suggitpe
 * @version 1.0 13 Feb 2008
 */
public class JmxBookAgent {

    private static final Log LOG = LogFactory.getLog( JmxBookAgent.class );

    private MBeanServer server;

    /**
     * Constructs a new instance.
     */
    public JmxBookAgent() {
        LOG.debug( "Creating MBean server " );
        String svrName = JmxBookConfig.getInstance().getCfgProperty( JmxBookConfig.MBEAN_SERVERNAME );
        server = MBeanServerFactory.createMBeanServer( svrName );

        if ( !startHtmlAdapter() ) {
            throw new IllegalStateException( "failed to start html adapter" );
        }

        if ( !startRmiConnector() ) {
            throw new IllegalStateException( "failed to start rmi connector" );
        }
        LOG.info( "JMX Book started" );
    }

    /**
     * Instantiate and start an HTML adapter for this agent
     */
    private boolean startHtmlAdapter() {
        int httpPort;
        try {
            httpPort = Integer.parseInt( JmxBookConfig.getInstance().getCfgProperty( JmxBookConfig.HTTP_PORT ) );
        }
        catch ( NumberFormatException nfe ) {
            throw new IllegalStateException( "Failed to convert http port number", nfe );
        }
        LOG.debug( "Starting the HTML adapter on port [" + httpPort + "]" );
        HtmlAdaptorServer adapter = new HtmlAdaptorServer();
        adapter.setPort( httpPort );

        ObjectName adapterName;
        try {
            String svrName = JmxBookConfig.getInstance().getCfgProperty( JmxBookConfig.MBEAN_SERVERNAME );
            adapterName = new ObjectName( svrName + ":name=html,port=" + httpPort );
            server.registerMBean( adapter, adapterName );
            adapter.start();
        }
        catch ( MalformedObjectNameException mone ) {
            LOG.error( "Error creating object name: " + mone.getMessage() );
            ExceptionUtil.printException( mone );
            return false;
        }
        catch ( JMException e ) {
            LOG.error( "Failed to register MBean: " + e.getMessage() );
            ExceptionUtil.printException( e );
            return false;
        }
        return true;
    }

    /**
     * Instantiate and start a RMI Connector for this agent
     */
    private boolean startRmiConnector() {

        String rmiUrl = JmxBookConfig.getInstance().getRmiUrl();
        // create the rmi connector server
        JMXConnectorServer conn;
        LOG.debug( "Starting the RMI connector on port [" + rmiUrl + "]" );

        try {
            JMXServiceURL url = new JMXServiceURL( rmiUrl );
            conn = JMXConnectorServerFactory.newJMXConnectorServer( url, null, server );
        }
        catch ( IOException ioe ) {
            LOG.error( "Error creating RMI Connector: " + ioe.getMessage() );
            ExceptionUtil.printException( ioe );
            return false;
        }

        // now register the connector server with the MBean server
        ObjectName connectorName;
        try {
            String svrName = JmxBookConfig.getInstance().getCfgProperty( JmxBookConfig.MBEAN_SERVERNAME );
            connectorName = new ObjectName( svrName + ":name=RMIConnector" );
            server.registerMBean( conn, connectorName );
        }
        catch ( MalformedObjectNameException mone ) {
            LOG.error( "Error creating object name: " + mone.getMessage() );
            ExceptionUtil.printException( mone );
            return false;
        }
        catch ( JMException e ) {
            LOG.error( "Failed to register MBean: " + e.getMessage() );
            ExceptionUtil.printException( e );
            return false;
        }

        // now we start the rmi connector
        try {
            conn.start();
        }
        catch ( IOException ioe ) {
            LOG.error( "Failed to start the RMIConnectorServer: " + ioe.getMessage() );
            ExceptionUtil.printException( ioe );
            return false;
        }

        return true;
    }
}
