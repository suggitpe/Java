package org.suggs.sandbox.test.multithreadedtc;

import edu.umd.cs.mtc.MultithreadedTest;
import edu.umd.cs.mtc.TestFramework;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Example of the MTC using compare and set against an atomic integer.  In this example it uses the compare and set
 * method on the atomic integer so that one thread is looking for the value to be 1 and the other to be 2.  It starts
 * with 1 so only the one thread will run and update, followed by the other, followed by teh finishing state where teh
 * integer is 3.<p/> Test cases need to have a method for each thread called threadx and then a initialize and a
 * finish.
 */

public class MtcCompareAndSet extends MultithreadedTest {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( MtcCompareAndSet.class );

    AtomicInteger ai;

    @Override
    public void initialize() {
        ai = new AtomicInteger( 1 );
    }

    @SuppressWarnings("unused")
    public void thread1() {
        while ( !ai.compareAndSet( 2, 3 ) ) {
            Thread.yield();
        }
    }

    @SuppressWarnings("unused")
    public void thread2() {
        assertThat( ai.compareAndSet( 1, 2 ), is( true ) );
    }

    @Override
    public void finish() {
        assertThat( ai.get(), equalTo( 3 ) );
    }

    @Test
    public void atomicIntegerIsThreadSafeForSingleExecution() throws Throwable {
        TestFramework.runOnce( new MtcCompareAndSet() );
    }

    public void atomicIntegerIsThreadSafeForMultipleExecution() throws Throwable {
        TestFramework.runManyTimes( new MtcCompareAndSet(), 100 );
    }
}
