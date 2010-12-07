/*
 * LockMutexDao.java created on 6 Dec 2010 19:41:04 by suggitpe for project sandbox-spring
 * 
 */
package org.suggs.sandbox.spring.jdbctemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * DAO over the top of the Lock Mutex table in the database.
 * 
 * @author suggitpe
 * @version 1.0 6 Dec 2010
 */
public class LockMutexDao {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( LockMutexDao.class );

    private JdbcTemplate jdbcTemplate;

    private static final String COUNT_ROWS_IN_TABLE = "select count(*) from TEST_TABLE";
    private static final String ID_EXISTS = "select 1 from TEST_TABLE where id = ?";

    public LockMutexDao( final JdbcTemplate aJdbcTemplate ) {
        jdbcTemplate = aJdbcTemplate;
    }

    @SuppressWarnings("boxing")
    public int countLockEntries() {
        List<Integer> results = jdbcTemplate.query( COUNT_ROWS_IN_TABLE, new RowMapper<Integer>() {

            @Override
            public Integer mapRow( ResultSet aRs, int aRowNum ) throws SQLException {
                return new Integer( aRs.getInt( 1 ) );
            }
        } );
        return results.get( 0 );
    }

    @SuppressWarnings("boxing")
    public boolean idExistsInLock( int aId ) {
        List<Integer> results = jdbcTemplate.query( ID_EXISTS,
                                                    new Object[] { aId },
                                                    new RowMapper<Integer>() {

                                                        @Override
                                                        public Integer mapRow( ResultSet aRs, int aRowNum )
                                                                        throws SQLException {
                                                            return new Integer( aRs.getInt( 1 ) );
                                                        }
                                                    } );

        if ( results.size() > 0 ) {
            return true;
        }
        return false;
    }

}
