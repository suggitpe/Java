/*
 * HelloWorld.java created on 11 Feb 2008 19:38:07 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.helloworld;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Hello World MBean implementation
 * 
 * @author suggitpe
 * @version 1.0 11 Feb 2008
 */
public class HelloWorld extends NotificationBroadcasterSupport implements HelloWorldMBean {

    private static final Log LOG = LogFactory.getLog( HelloWorld.class );

    private String greeting;

    /**
     * Constructs a new instance.
     */
    public HelloWorld() {
        greeting = "Hello I am a standard MBean impl";
    }

    /**
     * Constructs a new instance.
     * 
     * @param aGreeting
     *            the greeting to set
     */
    public HelloWorld( String aGreeting ) {
        greeting = aGreeting;
    }

    /**
     * @see org.suggs.sandbox.jmx.helloworld.HelloWorldMBean#getGreeting()
     */
    @Override
    public String getGreeting() {
        return greeting;
    }

    /**
     * @see org.suggs.sandbox.jmx.helloworld.HelloWorldMBean#printGreeting()
     */
    @Override
    public void printGreeting() {
        LOG.info( "Greeting on JMX managed object is [" + greeting + "]" );
    }

    /**
     * @see org.suggs.sandbox.jmx.helloworld.HelloWorldMBean#setGreeting(java.lang.String)
     */
    @Override
    public void setGreeting( String aGreeting ) {
        greeting = aGreeting;

        Notification notif = new Notification( "org.suggs.helloworld.test.notif",
                                               this,
                                               -1,
                                               System.currentTimeMillis(),
                                               greeting );
        sendNotification( notif );
    }

}
