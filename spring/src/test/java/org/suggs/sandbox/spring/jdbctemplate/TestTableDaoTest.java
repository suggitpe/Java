/*
 * TestTableDaoTest.java created on 6 Dec 2010 19:43:21 by suggitpe for project sandbox-spring
 * 
 */
package org.suggs.sandbox.spring.jdbctemplate;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Test suite for the TestTableDao class.
 * 
 * @author suggitpe
 * @version 1.0 6 Dec 2010
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:xml/database.xml" })
public class TestTableDaoTest {

    private static final Logger LOG = LoggerFactory.getLogger( TestTableDaoTest.class );

    @Resource(name = "namedParameterJdbcTemplate")
    protected NamedParameterJdbcTemplate jdbcTemplate;

    private TestTableDao dao;

    private static final String DROP_TEST_TABLE = "drop table " + TestTableDao.TABLE_NAME;
    private static final String CREATE_TEST_TABLE = "create table " + TestTableDao.TABLE_NAME
                                                    + " (ID INTEGER NOT NULL)";
    private static final String ADD_TEST_DATA = "insert into " + TestTableDao.TABLE_NAME + " values( :id )";

    private static final Map<String, String> EMPTY_MAP = new HashMap<String, String>();

    @Before
    public void onSetup() {

        LOG.debug( "--------------------- setup " );
        if ( jdbcTemplate == null ) {
            throw new IllegalStateException( "Null jdbctemplate in test suite" );
        }
        dao = new TestTableDao( jdbcTemplate );

        try {

            jdbcTemplate.update( DROP_TEST_TABLE, EMPTY_MAP );
        }
        catch ( Exception sqlException ) {
            LOG.debug( "Table does not exit so cannot drop it" );
        }

        jdbcTemplate.update( CREATE_TEST_TABLE, EMPTY_MAP );
        Map<String, String> params = new HashMap<String, String>();
        params.put( "id", "1234" );
        jdbcTemplate.update( ADD_TEST_DATA, params );

        params.clear();
        params.put( "id", "4567" );
        jdbcTemplate.update( ADD_TEST_DATA, params );

        LOG.debug( "--------------------- test" );
    }

    @After
    public void onTeardown() {
        LOG.debug( "--------------------- end" );
    }

    @SuppressWarnings("boxing")
    @Test
    public void countLockEntriesReturnsCorrectNumberOfEntries() {
        int count = dao.countLockEntries();
        assertThat( count, equalTo( 2 ) );
    }

    @SuppressWarnings("boxing")
    @Test
    public void idExistsCorrectlySeesExistentLock() {
        boolean result = dao.idExistsInLock( 1234 );
        assertThat( result, equalTo( true ) );
    }

    @SuppressWarnings("boxing")
    @Test
    public void idExistsCorrectlyIgnoresNonExistentLock() {
        boolean result = dao.idExistsInLock( 9876 );
        assertThat( result, equalTo( false ) );
    }

}
