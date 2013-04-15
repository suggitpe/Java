/*
 * HelloAgent.java created on 11 Feb 2008 19:47:11 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.helloworld;

import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jdmk.comm.HtmlAdaptorServer;

/**
 * JMX Agent class for the HelloWorld MBean
 */
public class HelloAgent implements NotificationListener {

    private static final Logger LOG = LoggerFactory.getLogger( HelloAgent.class );
    private static final int ADAPTER_PORT = 9092;

    private MBeanServer server;

    public HelloAgent() {
        LOG.debug( "Creating the MBean server ..." );
        // create an instance of an MBean Server
        server = MBeanServerFactory.createMBeanServer( "HelloAgent" );
    }

    public void runAgent() {

        // create the HTML adapter
        LOG.debug( "Creating the HTML adapter ..." );
        HtmlAdaptorServer adapter = new HtmlAdaptorServer();

        // creates the hello world mbean instance
        HelloWorld hw = new HelloWorld();

        ObjectName adapterName;
        ObjectName helloWorldName;
        try {
            LOG.debug( "Registering mbeans ..." );

            // register the hello world bean with a defined name
            helloWorldName = new ObjectName( "HelloAgent:name=helloWorld1" );
            server.registerMBean( hw, helloWorldName );

            // register the html adapter with a defined name
            adapterName = new ObjectName( "HelloAgent:name=htmladapter,port=" + ADAPTER_PORT );
            adapter.setPort( ADAPTER_PORT );
            server.registerMBean( adapter, adapterName );

            adapter.start();

            hw.addNotificationListener( this, null, null );
        }
        catch ( JMException mo ) {
            LOG.error( "Error registering mbeans with the server", mo );
        }
    }

    @Override
    public void handleNotification( Notification notif, Object handback ) {
        LOG.debug( "Receiving notification: [" + notif.getType() + "], [" + notif.getMessage() + "]" );
    }

    public static void main( String[] aArgs ) {
        LOG.debug( "Creating a new Hello Agent" );
        HelloAgent agent = new HelloAgent();
        agent.runAgent();
    }

}
