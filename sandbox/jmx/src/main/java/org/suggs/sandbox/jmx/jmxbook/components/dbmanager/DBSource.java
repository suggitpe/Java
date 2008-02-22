/*
 * DBSource.java created on 20 Feb 2008 19:33:31 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.jmxbook.components.dbmanager;

import org.suggs.sandbox.jmx.jmxbook.ExceptionUtil;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * MBean class to encapsulate a datasource functionality
 * 
 * @author suggitpe
 * @version 1.0 20 Feb 2008
 */
public class DBSource implements DBSourceMBean
{

    private static final Log LOG = LogFactory.getLog( DBSource.class );

    private DataSource mDataSource_;
    private boolean mCommit_ = false;

    /**
     * Constructs a new instance.
     * 
     * @param jndiName
     *            the jndi name of the resource
     */
    public DBSource( String jndiName )
    {
        resetDataSource( jndiName );
    }

    /**
     * @see org.suggs.sandbox.jmx.jmxbook.components.dbmanager.DBSourceMBean#resetDataSource(java.lang.String)
     */
    public void resetDataSource( String name )
    {
        LOG.debug( "Setting the datasource with name [" + name + "]" );
        try
        {
            Context ctx = new InitialContext();
            mDataSource_ = (DataSource) ctx.lookup( name );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

    /**
     * @see org.suggs.sandbox.jmx.jmxbook.components.dbmanager.DBSourceMBean#getConnection()
     */
    public Connection getConnection()
    {
        Connection ret;

        try
        {
            ret = mDataSource_.getConnection();
            ret.setAutoCommit( mCommit_ );
            return ret;
        }
        catch ( Exception e )
        {
            ExceptionUtil.printException( e );
        }
        return null;
    }

    /**
     * @see org.suggs.sandbox.jmx.jmxbook.components.dbmanager.DBSourceMBean#getAutoCommit()
     */
    public boolean getAutoCommit()
    {
        return mCommit_;
    }

    /**
     * @see org.suggs.sandbox.jmx.jmxbook.components.dbmanager.DBSourceMBean#setAutoCommit()
     */
    public void setAutoCommit( boolean commit )
    {
        mCommit_ = commit;
    }
}
