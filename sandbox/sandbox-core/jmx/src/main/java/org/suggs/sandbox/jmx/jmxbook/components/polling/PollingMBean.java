/*
 * PollingMBean.java created on 27 Feb 2008 19:39:17 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.jmxbook.components.polling;

/**
 * Interface for a poller.
 * 
 * @author suggitpe
 * @version 1.0 27 Feb 2008
 */
public interface PollingMBean {

    /**
     * Start the poller
     */
    public void start();

    /**
     * Stop the poller
     */
    public void stop();

    /**
     * Accessor to the interval variable
     * 
     * @param time
     *            the interval time to set
     */
    public void setInterval( long time );

    /**
     * Accessor to the interval attribute
     * 
     * @return the interval attribute
     */
    public long getInterval();

}
