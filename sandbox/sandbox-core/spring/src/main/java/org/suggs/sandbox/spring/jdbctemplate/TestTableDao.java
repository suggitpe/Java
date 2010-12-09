/*
 * TestTableDao.java created on 6 Dec 2010 19:41:04 by suggitpe for project sandbox-spring
 * 
 */
package org.suggs.sandbox.spring.jdbctemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * DAO over the top of the Lock Mutex table in the database.
 * 
 * @author suggitpe
 * @version 1.0 6 Dec 2010
 */
public class TestTableDao {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( TestTableDao.class );

    private NamedParameterJdbcTemplate jdbcTemplate;

    public static final String TABLE_NAME = "TEST_TABLE";
    private static final String COUNT_ROWS_IN_TABLE = "select count(*) from " + TABLE_NAME;
    private static final String ID_EXISTS = "select 1 from " + TABLE_NAME + " where id = :id";

    public TestTableDao( final NamedParameterJdbcTemplate aJdbcTemplate ) {
        jdbcTemplate = aJdbcTemplate;
    }

    public int countLockEntries() {
        List<Integer> results = jdbcTemplate.query( COUNT_ROWS_IN_TABLE,
                                                    new HashMap<String, String>(),
                                                    new RowMapper<Integer>() {

                                                        @Override
                                                        public Integer mapRow( ResultSet aRs, int aRowNum )
                                                                        throws SQLException {
                                                            return Integer.valueOf( aRs.getInt( 1 ) );
                                                        }
                                                    } );
        return results.get( 0 ).intValue();
    }

    public boolean idExistsInLock( int aId ) {
        Map<String, String> params = new HashMap<String, String>();
        params.put( "id", Integer.toString( aId ) );
        List<Integer> results = jdbcTemplate.query( ID_EXISTS, params, new RowMapper<Integer>() {

            @Override
            public Integer mapRow( ResultSet aRs, int aRowNum ) throws SQLException {
                return new Integer( aRs.getInt( 1 ) );
            }
        } );

        if ( results.size() > 0 ) {
            return true;
        }
        return false;
    }
}
