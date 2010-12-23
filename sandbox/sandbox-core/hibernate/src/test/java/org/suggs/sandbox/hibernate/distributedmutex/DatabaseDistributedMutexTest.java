/*
 * DatabaseDistributedMutexTest.java created on 3 Dec 2010 17:39:52 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.distributedmutex;

import org.suggs.sandbox.hibernate.distributedmutex.impl.DatabaseDistributedMutex;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Test to prove that the mutex is actually enforcing an ordered execution of the critical region.
 * 
 * @author suggitpe
 * @version 1.0 3 Dec 2010
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:xml/ut-databse-distributedmutex.xml" })
public class DatabaseDistributedMutexTest {

    private static final Logger LOG = LoggerFactory.getLogger( DatabaseDistributedMutexTest.class );
    private static final int MUTEX_CONTEXT_1 = 123456;
    private static final int MUTEX_CONTEXT_2 = 456789;
    private static final long SLEEP_TIME = 500L;

    private static final Boolean IN = Boolean.TRUE;
    private static final Boolean OUT = Boolean.FALSE;

    private static List<Boolean> callList;
    private Object callListLock = new Object();

    private static final String INSERT_LOCK = "insert into LOCK_MUTEX values (?)";
    private static final String LOCK_COUNT = "select l.*, lt.name from v$lock l, v$lock_type lt where l.type = lt.type and l.type in ('TM','TX')";

    @Resource(name = "jdbcTemplate")
    protected volatile JdbcTemplate jdbcTemplate;

    @SuppressWarnings("boxing")
    @Before
    public void onSetup() {
        LOG.debug( "####################################" );
        callList = new ArrayList<Boolean>();
        jdbcTemplate.update( "delete from LOCK_MUTEX" );
        jdbcTemplate.update( INSERT_LOCK, new Object[] { MUTEX_CONTEXT_1 } );
        jdbcTemplate.update( INSERT_LOCK, new Object[] { MUTEX_CONTEXT_2 } );
        LOG.debug( "----------------------------- test" );
    }

    @After
    public void onTeadDown() {
        LOG.debug( "----------------------------- end" );
    }

    @Test
    public void oneThreadSynchronisesOnAnId() {
        WorkerThread worker1 = new WorkerThread( "worker 1", MUTEX_CONTEXT_1 );
        worker1.start();
        while ( worker1.isAlive() ) {
            try {
                debugLockCount();
                Thread.sleep( 100L );
            }
            catch ( InterruptedException e ) {
                // nadda
            }
        }
        assessOrderVersusExpected( "Mutex should have synchronised because using the same ID",
                                   new Boolean[] { IN, OUT } );
    }

    @Ignore
    @Test
    public void twoThreadsSynchroniseOnAnId() {
        WorkerThread worker1 = new WorkerThread( "worker 1", MUTEX_CONTEXT_1 );
        WorkerThread worker2 = new WorkerThread( "worker 2", MUTEX_CONTEXT_1 );
        worker1.start();
        worker2.start();

        while ( worker1.isAlive() && worker2.isAlive() ) {
            try {
                debugLockCount();
                Thread.sleep( 100L );
            }
            catch ( InterruptedException e ) {
                // nadda
            }
        }

        assessOrderVersusExpected( "Mutex should have synchronised because using the same ID",
                                   new Boolean[] { IN, OUT, IN, OUT } );
    }

    @Ignore
    @Test
    public void twoThreadsDoNotSynchroniseOnDifferentIds() {
        WorkerThread worker1 = new WorkerThread( "worker 1", MUTEX_CONTEXT_1 );
        WorkerThread worker2 = new WorkerThread( "worker 2", MUTEX_CONTEXT_2 );
        worker1.start();
        worker2.start();

        while ( worker1.isAlive() && worker2.isAlive() ) {
            try {
                debugLockCount();
                Thread.sleep( 100L );
            }
            catch ( InterruptedException e ) {
                // nadda
            }
        }
        assessOrderVersusExpected( "Mutex should not have synchronised due to different IDs",
                                   new Boolean[] { IN, IN, OUT, OUT } );
    }

    @SuppressWarnings("boxing")
    private void assessOrderVersusExpected( String aFailureComment, Boolean[] aExpectedOrder ) {
        assertThat( "Expected number of mutex safe calls does not match the actual:",
                    callList.size(),
                    equalTo( aExpectedOrder.length ) );
        synchronized ( callListLock ) {
            for ( int i = 0; i < callList.size(); ++i ) {
                assertThat( "Entry [" + i + "] should have been [" + aExpectedOrder[i]
                                            + "] but was actually [" + callList.get( i ) + "]",
                            callList.get( i ),
                            equalTo( aExpectedOrder[i] ) );
            }
        }
    }

    /**
     * Returns the value of jdbcTemplate.
     * 
     * @return Returns the jdbcTemplate.
     */
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    /**
     * Sets the jdbcTemplate field to the specified value.
     * 
     * @param aJdbcTemplate
     *            The jdbcTemplate to set.
     */
    public void setJdbcTemplate( JdbcTemplate aJdbcTemplate ) {
        jdbcTemplate = aJdbcTemplate;
    }

    private void debugLockCount() {
        if ( jdbcTemplate == null ) {
            throw new IllegalStateException( "No jdbcTemplate defined in the test" );
        }

        LockBean bean = jdbcTemplate.query( LOCK_COUNT, resultSetExtractor );
        LOG.debug( "Lock details are: " + bean.toString() );

    }

    private ResultSetExtractor<LockBean> resultSetExtractor = new ResultSetExtractor<LockBean>() {

        @SuppressWarnings("unused")
        @Override
        public LockBean extractData( ResultSet aResultSet ) throws SQLException, DataAccessException {
            return new LockBean();
            // aResultSet.next();
            // for ( ; aResultSet.next(); ) {
            // aResultSet.get

            // }
            // LOG.error( "Failed to acquire lock on mutex" );
            // throw new IllegalStateException( "Unable to acquire mutex for ID" );
        }
    };

    private static class LockBean {

    }

    // #####################################################
    // ## ---------------- WORKER THREAD ---------------- ##
    // #####################################################
    private class WorkerThread extends Thread {

        private String name;
        private int synchronisationContext;

        public WorkerThread( String aName, int aSynchronisationContext ) {
            name = aName;
            synchronisationContext = aSynchronisationContext;
        }

        @Override
        public void run() {
            DistributedMutex mutex = new DatabaseDistributedMutex( jdbcTemplate );
            LOG.debug( "WorkerThread [" + name + "] entering mutex ..." );
            mutex.synchronise( synchronisationContext );
            synchronized ( callListLock ) {
                callList.add( IN );
            }
            LOG.debug( "WorkerThread [" + name + "] entered mutex ... small sleep" );
            try {
                Thread.sleep( SLEEP_TIME );
            }
            catch ( InterruptedException e ) {
                // nadda
            }
            LOG.debug( "WorkerThread [" + name + "] exiting mutex ... all done" );
            synchronized ( callListLock ) {
                callList.add( OUT );
            }
        }
    }

}
