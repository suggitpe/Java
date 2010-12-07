/*
 * LockMutexDaoTest.java created on 6 Dec 2010 19:43:21 by suggitpe for project sandbox-spring
 * 
 */
package org.suggs.sandbox.spring.jdbctemplate;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Test suite for the LockMutexDao class.
 * 
 * @author suggitpe
 * @version 1.0 6 Dec 2010
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:xml/database.xml" })
public class LockMutexDaoTest {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( LockMutexDaoTest.class );

    @Resource(name = "jdbcTemplate")
    protected JdbcTemplate jdbcTemplate;

    private LockMutexDao dao;

    private static final String CLEAR_MUTEX_TABLE = "delete from LOCK_MUTEX";
    private static final String ADD_TEST_DATA = "insert into LOCK_MUTEX values(?)";

    @SuppressWarnings("boxing")
    @Before
    public void onSetup() {
        if ( jdbcTemplate == null ) {
            throw new IllegalStateException( "Null jdbctemplate in test suite" );
        }
        dao = new LockMutexDao( jdbcTemplate );

        jdbcTemplate.update( CLEAR_MUTEX_TABLE );
        jdbcTemplate.update( ADD_TEST_DATA, new Object[] { 1234 } );
        jdbcTemplate.update( ADD_TEST_DATA, new Object[] { 4567 } );
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
