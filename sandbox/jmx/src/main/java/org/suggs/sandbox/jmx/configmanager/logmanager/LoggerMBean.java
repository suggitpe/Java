/*
 * LoggerMBean.java created on 21 Feb 2008 08:15:24 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.configmanager.logmanager;

/**
 * Interface to access a logger and its underlying logging mechanism
 * 
 * @author suggitpe
 * @version 1.0 21 Feb 2008
 */
public interface LoggerMBean
{

    public void setLogLevel( int aLevel );

    public int getLogLevel();

    public String retrieveLog( int aLinesBack );

    public void writeLog( String aMessage, int aType );

}
