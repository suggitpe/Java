/*
 * HelloWorld.java created on 11 Feb 2008 19:38:07 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.helloworld;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello World MBean implementation
 */
public class HelloWorld extends NotificationBroadcasterSupport implements HelloWorldMBean {

    private static final Logger LOG = LoggerFactory.getLogger( HelloWorld.class );

    private String greeting;

    public HelloWorld() {
        greeting = "Hello I am a standard MBean impl";
    }

    public HelloWorld( String aGreeting ) {
        greeting = aGreeting;
    }

    @Override
    public String getGreeting() {
        return greeting;
    }

    @Override
    public void printGreeting() {
        LOG.info( "Greeting on JMX managed object is [" + greeting + "]" );
    }

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
