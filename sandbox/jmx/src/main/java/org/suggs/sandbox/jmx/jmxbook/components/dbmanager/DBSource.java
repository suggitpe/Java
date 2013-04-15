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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MBean class to encapsulate a datasource functionality
 */
public class DBSource implements DBSourceMBean {

    private static final Logger LOG = LoggerFactory.getLogger( DBSource.class );

    private DataSource dataSource;
    private boolean commit = false;

    public DBSource( String jndiName ) {
        resetDataSource( jndiName );
    }

    @Override
    public final void resetDataSource( String name ) {
        LOG.debug( "Setting the datasource with name [" + name + "]" );
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup( name );
        }
        catch ( Exception e ) {
            LOG.error( "exception caught when looking up the datasource [" + name + "]", e );
        }
    }

    @Override
    public Connection getConnection() {
        Connection ret;

        try {
            ret = dataSource.getConnection();
            ret.setAutoCommit( commit );
            return ret;
        }
        catch ( Exception e ) {
            ExceptionUtil.printException( e );
        }
        return null;
    }

    @Override
    public boolean getAutoCommit() {
        return commit;
    }

    @Override
    public void setAutoCommit( boolean aCommit ) {
        commit = aCommit;
    }
}
