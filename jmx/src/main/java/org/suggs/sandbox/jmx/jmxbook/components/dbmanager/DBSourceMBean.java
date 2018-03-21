/*
 * DBSourceMBean.java created on 20 Feb 2008 19:31:07 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.jmxbook.components.dbmanager;

import java.sql.Connection;

/**
 * MBean interface that will allow access to a datasource to set various attributes on it.
 */
public interface DBSourceMBean {

    public void resetDataSource( String name );

    public void setAutoCommit( boolean commit );

    public boolean getAutoCommit();

    public Connection getConnection();

}
