package org.suggs.sandbox.test.multithreadedtc;

import edu.umd.cs.mtc.MultithreadedTestCase;
import edu.umd.cs.mtc.TestFramework;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Example of MTC using ticks and locks against a number of threads.  A tick happens when all threads are blocked.  The
 * advancement of a tick happens automatically.
 */

public class MtcThreadOrdering extends MultithreadedTestCase {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( MtcThreadOrdering.class );

    private AtomicInteger ai;

    @Override
    public void initialize() {
        ai = new AtomicInteger( 0 );
    }

    @SuppressWarnings("unused")
    public void threadFoo() {
        assertThat( ai.compareAndSet( 0, 1 ), is( true ) );
        waitForTick( 3 );
        LOG.debug( "Tick 3 attained" );
        assertThat( ai.get(), equalTo( 3 ) );
    }

    @SuppressWarnings("unused")
    public void threadBar() {
        waitForTick( 1 );
        LOG.debug( "Tick 1 attained" );
        assertThat( ai.compareAndSet( 1, 2 ), is( true ) );
        waitForTick( 3 );
        LOG.debug( "Tick 3 attained" );
        assertThat( ai.get(), equalTo( 3 ) );
    }

    @SuppressWarnings("unused")
    public void threadYay() {
        waitForTick( 2 );
        LOG.debug( "Tick 2 attained" );
        assertThat( ai.compareAndSet( 2, 3 ), is( true ) );
    }

    @Override
    public void finish() {
        assertThat( ai.get(), equalTo( 3 ) );
        LOG.debug( "All threads complete" );
    }

    @Test
    public void applicationThreadsCanBeOrderedUsingTicksAndBlocking() throws Throwable {
        TestFramework.runOnce( new MtcThreadOrdering() );
    }
}
