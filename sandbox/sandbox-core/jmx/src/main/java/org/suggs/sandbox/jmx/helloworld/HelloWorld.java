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
public class HelloWorld extends NotificationBroadcasterSupport implements HelloWorldMBean
{

    private static final Log LOG = LogFactory.getLog( HelloWorld.class );

    private String mGreeting_;

    /**
     * Constructs a new instance.
     */
    public HelloWorld()
    {
        mGreeting_ = "Hello I am a standard MBean impl";
    }

    /**
     * Constructs a new instance.
     * 
     * @param aGreeting
     *            the greeting to set
     */
    public HelloWorld( String aGreeting )
    {
        mGreeting_ = aGreeting;
    }

    /**
     * @see org.suggs.sandbox.jmx.helloworld.HelloWorldMBean#getGreeting()
     */
    public String getGreeting()
    {
        return mGreeting_;
    }

    /**
     * @see org.suggs.sandbox.jmx.helloworld.HelloWorldMBean#printGreeting()
     */
    public void printGreeting()
    {
        LOG.info( "Greeting on JMX managed object is [" + mGreeting_ + "]" );
    }

    /**
     * @see org.suggs.sandbox.jmx.helloworld.HelloWorldMBean#setGreeting(java.lang.String)
     */
    public void setGreeting( String greeting )
    {
        mGreeting_ = greeting;

        Notification notif = new Notification( "org.suggs.helloworld.test.notif",
                                               this,
                                               -1,
                                               System.currentTimeMillis(),
                                               greeting );
        sendNotification( notif );
    }

}
