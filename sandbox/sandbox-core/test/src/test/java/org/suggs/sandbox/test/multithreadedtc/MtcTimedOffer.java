package org.suggs.sandbox.test.multithreadedtc;

import edu.umd.cs.mtc.MultithreadedTestCase;
import edu.umd.cs.mtc.TestFramework;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Example odf using the MTC clock freeze to force a timeout within a thread.
 * <p/>
 * User: suggitpe Date: 05/04/11 Time: 09:11
 */

public class MtcTimedOffer extends MultithreadedTestCase {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( MtcTimedOffer.class );

    ArrayBlockingQueue<Object> queue;

    @Override
    public void initialize() {
        queue = new ArrayBlockingQueue<Object>( 2 );
    }

    @SuppressWarnings("unused")
    public void thread1() {
        try {
            LOG.debug("Putting objects on queue");
            queue.put( new Object() );
            queue.put( new Object() );

            LOG.debug("Objects put on queue .. freezing");
            freezeClock();
            LOG.debug("Offering");
            assertThat( queue.offer( new Object(), 25, TimeUnit.MILLISECONDS ), is( false ) );
            unfreezeClock();

            LOG.debug("Offering");
            queue.offer( new Object(), 2500, TimeUnit.MILLISECONDS );
            fail( "Should have thrown an exception" );
        }
        catch ( InterruptedException success ) {
            assertTick( 1 );
        }
    }

    @SuppressWarnings("unused")
    public void thread2() {
        LOG.debug("Waiting for tick 1");
        waitForTick( 1 );
        LOG.debug("Tick 1 attained .. interrupting thread 1");
        getThread( 1 ).interrupt();
    }

    @Test
    public void internalClockThreadCanBeManipulated() throws Throwable{
        TestFramework.runOnce( new MtcTimedOffer());
    }
}
