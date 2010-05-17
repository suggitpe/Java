/*
 * PollingNotificationListener.java created on 28 Feb 2008 07:51:08 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.jmxbook.client;

import org.suggs.sandbox.jmx.jmxbook.config.JmxBookConfig;

import java.io.IOException;

import javax.management.InstanceNotFoundException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class is a JMX notification listener. The core functionality is to connect to the MBean server ad then
 * register with a managed object to receive notifications.The MBean server acts as a proxy between the
 * listener and the underlying object that is publishing the notifications.
 * 
 * @author suggitpe
 * @version 1.0 28 Feb 2008
 */
public class PollingNotificationListener implements NotificationListener {

    private static final Log LOG = LogFactory.getLog( PollingNotificationListener.class );

    /**
     * Constructs a new instance.
     */
    public PollingNotificationListener() {
        try {

            // Register this as a listener through the MBean server
            // itself rather than directly at the object level.
            MBeanServerConnection conn = RmiClientFactory.getClient();
            String svrName = JmxBookConfig.getInstance().getCfgProperty( JmxBookConfig.MBEAN_SERVERNAME );

            conn.addNotificationListener( new ObjectName( svrName + ":name=polling" ), this, null, null );
        }
        catch ( IOException e ) {
            LOG.error( "Failed to connect to mbean server", e );
        }
        catch ( MalformedObjectNameException mone ) {
            LOG.error( "Failed to create a proper object name", mone );
        }
        catch ( InstanceNotFoundException infe ) {
            LOG.error( "Failed to find the object instance", infe );
        }
    }

    /**
     * @see javax.management.NotificationListener#handleNotification(javax.management.Notification,
     *      java.lang.Object)
     */
    public void handleNotification( Notification notification, Object handback ) {
        String type = notification.getType();
        LOG.debug( "Notif of type " + type );
    }

    /**
     * Start the main thread
     * 
     * @param args
     */
    public static void main( String[] args ) {
        LOG.debug( "Starting a new polling notification listener" );
        new PollingNotificationListener();
        while ( true ) {
            try {
                Thread.sleep( 10000 );
            }
            catch ( InterruptedException e ) {
                // nadda
            }
        }
    }

}
