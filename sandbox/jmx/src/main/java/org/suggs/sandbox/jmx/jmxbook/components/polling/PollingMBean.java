/*
 * PollingMBean.java created on 27 Feb 2008 19:39:17 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.jmxbook.components.polling;

/**
 * Interface for a poller.
 */
public interface PollingMBean {

    public void start();

    public void stop();

    public void setInterval(long time);

    public long getInterval();

}
