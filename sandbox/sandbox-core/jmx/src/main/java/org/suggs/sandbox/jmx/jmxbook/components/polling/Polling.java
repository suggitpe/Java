/*
 * Polling.java created on 27 Feb 2008 19:41:26 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.jmxbook.components.polling;

import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Runnable polling MBean impl
 * 
 * @author suggitpe
 * @version 1.0 27 Feb 2008
 */
public class Polling extends NotificationBroadcasterSupport implements PollingMBean, Runnable {

    private static final Log LOG = LogFactory.getLog( Polling.class );

    private boolean stop = true;
    private int index = 0;
    private long interval = 1000;

    /**
     * Constructs a new instance.
     */
    public Polling() {
        super();
    }

    /**
     * sends a notification when the attribute has changed
     * 
     * @see org.suggs.sandbox.jmx.jmxbook.components.polling.PollingMBean#setInterval(long)
     */
    public void setInterval( long time ) {
        long tmp = interval;
        interval = time;
        sendNotification( new AttributeChangeNotification( this,
                                                           0,
                                                           System.currentTimeMillis(),
                                                           "Attribute change for interval",
                                                           "interval",
                                                           "long",
                                                           new Long( tmp ),
                                                           new Long( time ) ) );
    }

    /**
     * @see org.suggs.sandbox.jmx.jmxbook.components.polling.PollingMBean#getInterval()
     */
    public long getInterval() {
        return interval;
    }

    /**
     * @see org.suggs.sandbox.jmx.jmxbook.components.polling.PollingMBean#start()
     */
    public void start() {
        if ( !stop ) {
            LOG.warn( "Already running" );
            return;
        }

        LOG.debug( "Starting polling ..." );
        stop = false;
        Thread t = new Thread( this );
        t.start();
    }

    /**
     * @see org.suggs.sandbox.jmx.jmxbook.components.polling.PollingMBean#stop()
     */
    public void stop() {
        LOG.debug( "Stopping polling ..." );
        stop = true;
    }

    /**
     * @see java.lang.Runnable#run()
     */
    public void run() {
        while ( !stop ) {
            try {
                LOG.debug( "    ..." );
                Thread.sleep( interval );
            }
            catch ( InterruptedException ie ) {
                LOG.warn( "Exception caught during sleep [" + ie.getMessage() + "]" );
            }
            sendNotification( new Notification( "PollingBean.counter", this, ++index ) );
        }
    }

    /**
     * @see javax.management.NotificationBroadcasterSupport#getNotificationInfo()
     */
    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        String[] type = { "PollingBean.counter" };
        String[] attChanges = { AttributeChangeNotification.ATTRIBUTE_CHANGE };

        MBeanNotificationInfo[] info = new MBeanNotificationInfo[2];

        info[0] = new MBeanNotificationInfo( type,
                                             "javax.management.Notification",
                                             "The polling MBean notification" );
        info[1] = new MBeanNotificationInfo( attChanges,
                                             "javax.management.AttributeChangeNotification",
                                             "The Polling MBean counter" );
        return info;
    }

}
