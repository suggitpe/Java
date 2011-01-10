/*
 * DatabaseDistributedMutex.java created on 3 Dec 2010 17:36:38 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.spring.distributedmutex.impl;

import org.suggs.sandbox.spring.distributedmutex.DistributedMutex;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger LOG = LoggerFactory.getLogger( DatabaseDistributedMutex.class );

    private JdbcTemplate jdbcTemplate;
    public static final String TABLE_NAME = "LOCK_MUTEX";
    private static final String LOCK_SQL = "select 1 from " + TABLE_NAME + "where ID = ? for update";
    private static final int QUERY_TIMEOUT = 500;

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
        jdbcTemplate.setQueryTimeout( QUERY_TIMEOUT );
    }

    /**
     * @see org.suggs.sandbox.spring.distributedmutex.DistributedMutex#synchronise(int)
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

        jdbcTemplate.query( LOCK_SQL, preparedStatementSetter, resultSetExtractor );
    }

    private ResultSetExtractor<Integer> resultSetExtractor = new ResultSetExtractor<Integer>() {

        @Override
        public Integer extractData( ResultSet aResultSet ) throws SQLException, DataAccessException {
            for ( ; aResultSet.next(); ) {
                return Integer.valueOf( 1 );
            }
            LOG.error( "Failed to acquire lock on mutex" );
            throw new IllegalStateException( "Unable to acquire mutex for ID" );
        }
    };

    public void setDisable( boolean isDisabled ) {
        disabled = isDisabled;
    }

}
