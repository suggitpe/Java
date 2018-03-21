/*
 * HelloWorldMBean.java created on 11 Feb 2008 19:32:40 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.helloworld;

/**
 * MBean interface for a simple Hello world bean
 */
public interface HelloWorldMBean {

    void setGreeting( String aGreeting );

    String getGreeting();

    void printGreeting();

}
