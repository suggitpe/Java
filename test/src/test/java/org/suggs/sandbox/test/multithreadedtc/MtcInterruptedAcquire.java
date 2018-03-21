package org.suggs.sandbox.test.multithreadedtc;

import edu.umd.cs.mtc.MultithreadedTestCase;
import edu.umd.cs.mtc.TestFramework;

import java.util.concurrent.Semaphore;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Example of the MTC waiting for ticks against locks.
 */

public class MtcInterruptedAcquire extends MultithreadedTestCase {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( MtcInterruptedAcquire.class );

    Semaphore semaphore;

    @Override
    public void initialize() {
        semaphore = new Semaphore( 0 );
    }

    @SuppressWarnings("unused")
    public void thread1() {
        try {
            LOG.debug("Trying to acquire semaphore with tick ["+getTick()+"]");
            semaphore.acquire();
            fail( "Test should have thrown an exception here" );
        }
        catch ( InterruptedException ie ) {
            LOG.debug("Correctly failed to acquire semaphore");
            assertTick( 1 );
        }
    }


    @SuppressWarnings("unused")
    public void thread2() {
        LOG.debug("Waiting for tick 1");
        waitForTick( 1 );
        LOG.debug("Tick 1 attained .. interrupting");
        getThread( 1 ).interrupt();
        LOG.debug( "Interruped thread" );
    }

    @Override
    public void finish() {
    }

    @Test
    public void interruptedExceptionThrownInTheCorrectPlace() throws Throwable {
        TestFramework.runOnce( new MtcInterruptedAcquire() );
    }
}
