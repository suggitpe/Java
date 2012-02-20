/*
 * DBSourceMBean.java created on 20 Feb 2008 19:31:07 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.jmxbook.components.dbmanager;

import java.sql.Connection;

/**
 * MBean interface that will allow access to a datasource to set various attributes on it.
 * 
 * @author suggitpe
 * @version 1.0 20 Feb 2008
 */
public interface DBSourceMBean {

    /**
     * @param name
     */
    public void resetDataSource( String name );

    /**
     * @param commit
     */
    public void setAutoCommit( boolean commit );

    /**
     * @return true if auto comit uis set
     */
    public boolean getAutoCommit();

    /**
     * @return teh connection
     */
    public Connection getConnection();

}
