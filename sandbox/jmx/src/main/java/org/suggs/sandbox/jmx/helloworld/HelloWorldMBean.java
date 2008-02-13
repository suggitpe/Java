/*
 * HelloWorldMBean.java created on 11 Feb 2008 19:32:40 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.helloworld;

/**
 * MBean interface for a simple Hello world bean
 * 
 * @author suggitpe
 * @version 1.0 11 Feb 2008
 */
public interface HelloWorldMBean
{

    /**
     * Setter for the greeting
     * 
     * @param aGreeting
     *            the greeting to set
     */
    void setGreeting( String aGreeting );

    /**
     * Getter for the greeting
     * 
     * @return the greeting
     */
    String getGreeting();

    /**
     * Prints the greeting
     */
    void printGreeting();

}
