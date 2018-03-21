/*
 * Polling.java created on 27 Feb 2008 19:41:26 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.jmxbook.components.polling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

/**
 * Runnable polling MBean impl
 */
public class Polling extends NotificationBroadcasterSupport implements PollingMBean, Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(Polling.class);

    private boolean stop = true;
    private int index = 0;
    private long interval = 1000;

    public Polling() {
        super();
    }

    @Override
    public void setInterval(long time) {
        long tmp = interval;
        interval = time;
        sendNotification(new AttributeChangeNotification(this,
                0,
                System.currentTimeMillis(),
                "Attribute change for interval",
                "interval",
                "long",
                Long.valueOf(tmp),
                Long.valueOf(time)));
    }

    @Override
    public long getInterval() {
        return interval;
    }

    @Override
    public void start() {
        if (!stop) {
            LOG.warn("Already running");
            return;
        }

        LOG.debug("Starting polling ...");
        stop = false;
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void stop() {
        LOG.debug("Stopping polling ...");
        stop = true;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                LOG.debug("    ...");
                Thread.sleep(interval);
            } catch (InterruptedException ie) {
                LOG.warn("Exception caught during sleep [" + ie.getMessage() + "]");
            }
            sendNotification(new Notification("PollingBean.counter", this, ++index));
        }
    }

    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        String[] type = {"PollingBean.counter"};
        String[] attChanges = {AttributeChangeNotification.ATTRIBUTE_CHANGE};

        MBeanNotificationInfo[] info = new MBeanNotificationInfo[2];

        info[0] = new MBeanNotificationInfo(type,
                "javax.management.Notification",
                "The polling MBean notification");
        info[1] = new MBeanNotificationInfo(attChanges,
                "javax.management.AttributeChangeNotification",
                "The Polling MBean counter");
        return info;
    }

}
