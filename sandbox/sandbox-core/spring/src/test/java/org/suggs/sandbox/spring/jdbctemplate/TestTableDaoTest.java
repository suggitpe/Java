/*
 * TestTableDaoTest.java created on 6 Dec 2010 19:43:21 by suggitpe for project sandbox-spring
 * 
 */
package org.suggs.sandbox.spring.jdbctemplate;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.jdbc.core.JdbcTemplate;
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

    private static final Log LOG = LogFactory.getLog( TestTableDaoTest.class );

    @Resource(name = "jdbcTemplate")
    protected JdbcTemplate jdbcTemplate;

    private TestTableDao dao;

    private static final String DROP_TEST_TABLE = "drop table " + TestTableDao.TABLE_NAME;
    private static final String CREATE_TEST_TABLE = "create table " + TestTableDao.TABLE_NAME
                                                    + " (ID NUMBER NOT NULL)";
    private static final String ADD_TEST_DATA = "insert into " + TestTableDao.TABLE_NAME + " values(?)";

    @SuppressWarnings("boxing")
    @Before
    public void onSetup() {

        LOG.debug( "--------------------- setup " );
        if ( jdbcTemplate == null ) {
            throw new IllegalStateException( "Null jdbctemplate in test suite" );
        }
        dao = new TestTableDao( jdbcTemplate );

        try {

            jdbcTemplate.execute( DROP_TEST_TABLE );
        }
        catch ( Exception sqlException ) {
            sqlException.printStackTrace();
            LOG.debug( "Table does not exit so cannot drop it" );
        }

        jdbcTemplate.execute( CREATE_TEST_TABLE );
        jdbcTemplate.update( ADD_TEST_DATA, 1234 );
        jdbcTemplate.update( ADD_TEST_DATA, 4567 );

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
