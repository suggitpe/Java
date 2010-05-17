/*
 * LoggerMBean.java created on 21 Feb 2008 08:15:24 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.jmxbook.components.logmanager;

/**
 * Interface to access a logger and its underlying logging mechanism
 * 
 * @author suggitpe
 * @version 1.0 21 Feb 2008
 */
public interface LoggerMBean {

    /**
     * Setter for the log level
     * 
     * @param aLevel
     */
    public void setLogLevel( int aLevel );

    /**
     * Getter for the log level
     * 
     * @return the log level
     */
    public int getLogLevel();

    /**
     * Getter for the underlying log
     * 
     * @param aLinesBack
     * @return the log itself as a string
     */
    public String retrieveLog( int aLinesBack );

    /**
     * Writes to teh log
     * 
     * @param aMessage
     * @param aType
     */
    public void writeLog( String aMessage, int aType );

}
