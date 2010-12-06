/*
 * DatabaseDistributedMutex.java created on 3 Dec 2010 17:36:38 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.distributedmutex.impl;

import org.suggs.sandbox.hibernate.distributedmutex.DistributedMutex;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;

/**
 * Distributed Mutex implementation that utliised a database entry to form the mutex context.
 * 
 * @author suggitpe
 * @version 1.0 3 Dec 2010
 */
public class DatabaseDistributedMutex implements DistributedMutex {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( DatabaseDistributedMutex.class );

    private JdbcTemplate jdbcTemplate;
    private static final String LOCK_SQL = "select 1 from LOCK_MUTEX where ID = ? for update";

    private boolean disabled = false;

    /**
     * Constructs a new instance.
     * 
     * @param aJdbcTemplate
     *            a jdbctemplate that has access to the database
     */
    public DatabaseDistributedMutex( JdbcTemplate aJdbcTemplate ) {
        if ( aJdbcTemplate == null ) {
            throw new IllegalArgumentException( "Must initialise the Database Distributed Mutex with a valid JDBC Template" );
        }
        jdbcTemplate = aJdbcTemplate;
    }

    /**
     * @see org.suggs.sandbox.hibernate.distributedmutex.DistributedMutex#synchronise(int)
     */
    @Override
    public void synchronise( final int aId ) {
        if ( disabled ) {
            return;
        }

        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {

            @Override
            public void setValues( PreparedStatement aStatement ) throws SQLException {
                aStatement.setInt( 1, aId );
            }
        };

        jdbcTemplate.query( LOCK_SQL, preparedStatementSetter, resultSetExractor );

    }

    public void setDisable( boolean isDisabled ) {
        disabled = isDisabled;
    }

    private ResultSetExtractor resultSetExractor = new ResultSetExtractor() {

        @Override
        public Object extractData( ResultSet aResultSet ) throws SQLException, DataAccessException {
            for ( ; aResultSet.next(); ) {
                return Boolean.TRUE;
            }
            throw new IllegalStateException( "Unable to acquire mutex for ID" );
        }
    };
}
