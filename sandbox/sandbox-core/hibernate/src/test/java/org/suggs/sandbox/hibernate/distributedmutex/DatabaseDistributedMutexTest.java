/*
 * DatabaseDistributedMutexTest.java created on 3 Dec 2010 17:39:52 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.distributedmutex;

import org.suggs.sandbox.hibernate.distributedmutex.impl.DatabaseDistributedMutex;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Test to prove that the mutex is actually enforcing an ordered execution of the critical region.
 * 
 * @author suggitpe
 * @version 1.0 3 Dec 2010
 */
public class DatabaseDistributedMutexTest {

    private static final Log LOG = LogFactory.getLog( DatabaseDistributedMutexTest.class );
    private static final int MUTEX_CONTEXT_1 = 123456;
    private static final int MUTEX_CONTEXT_2 = 456789;
    private static final long SLEEP_TIME = 1000L;

    private static final Boolean IN = Boolean.TRUE;
    private static final Boolean OUT = Boolean.FALSE;

    private static volatile List<Boolean> callList;

    @Before
    public void onSetup() {
        callList = new ArrayList<Boolean>();
    }

    @Test
    public void oneThreadSynchronisesOnAnId() {
        WorkerThread worker1 = new WorkerThread( "worker 1", MUTEX_CONTEXT_1 );
        worker1.start();
        while ( worker1.isAlive() ) {
            try {
                Thread.sleep( 100L );
            }
            catch ( InterruptedException e ) {
                // nadda
            }
        }
        assessOrderVersusExpected( "Mutex should have synchronised because using the same ID",
                                   new Boolean[] { IN, OUT } );
    }

    @Test
    public void twoThreadsSynchroniseOnAnId() {
        WorkerThread worker1 = new WorkerThread( "worker 1", MUTEX_CONTEXT_1 );
        WorkerThread worker2 = new WorkerThread( "worker 2", MUTEX_CONTEXT_1 );
        worker1.start();
        worker2.start();

        while ( worker1.isAlive() && worker2.isAlive() ) {
            try {
                Thread.sleep( 100L );
            }
            catch ( InterruptedException e ) {
                // nadda
            }
        }

        assessOrderVersusExpected( "Mutex should have synchronised because using the same ID",
                                   new Boolean[] { IN, OUT, IN, OUT } );
    }

    @Test
    public void twoThreadsDoNotSynchroniseOnDifferentIds() {
        WorkerThread worker1 = new WorkerThread( "worker 1", MUTEX_CONTEXT_1 );
        WorkerThread worker2 = new WorkerThread( "worker 2", MUTEX_CONTEXT_2 );
        worker1.start();
        worker2.start();

        while ( worker1.isAlive() && worker2.isAlive() ) {
            try {
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
        assertThat( callList.size(), equalTo( aExpectedOrder.length ) );
        for ( int i = 0; i < callList.size(); ++i ) {
            assertThat( "Entry [" + i + "] should have been [" + aExpectedOrder[i] + "] but was actually ["
                        + callList.get( i ) + "]", callList.get( i ), equalTo( aExpectedOrder[i] ) );
        }
    }

    private static class WorkerThread extends Thread {

        private String name;
        private int synchronisationContext;

        public WorkerThread( String aName, int aSynchronisationContext ) {
            name = aName;
            synchronisationContext = aSynchronisationContext;
        }

        @Override
        public void run() {
            DistributedMutex mutex = new DatabaseDistributedMutex();
            mutex.synchronise( synchronisationContext );
            callList.add( IN );
            LOG.debug( "WorkerThread [" + name + "] entered mutex" );
            try {
                Thread.sleep( SLEEP_TIME );
            }
            catch ( InterruptedException e ) {
                // nadda
            }
            LOG.debug( "WorkerThread [" + name + "] exiting mutex" );
            callList.add( OUT );
        }
    }

}
