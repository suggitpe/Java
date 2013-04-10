/*
 * SingletonTestCase.java created on 24 Aug 2007 06:07:47 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.creational;

import org.suggs.sandbox.patterns.AbstractPatternTest;
import org.suggs.sandbox.patterns.creational.singleton.SingletonImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;

/**
 * TestCase for the singleton pattern.
 */
public class SingletonTest extends AbstractPatternTest {

    private static final Logger LOG = LoggerFactory.getLogger( SingletonTest.class );

    @Test
    public void testSingletonCreate() {
        LOG.debug( "Calling Singleton for the first time" );

        long initial = new Long( SingletonImpl.instance().getConstructionTime() ).longValue();

        try {
            LOG.debug( "Sleeping" );
            Thread.sleep( 500 );
        }
        catch ( InterruptedException ie ) {
            // nadda
        }

        long last = new Long( SingletonImpl.instance().getConstructionTime() ).longValue();
        long now = new Long( System.currentTimeMillis() ).longValue();

        LOG.debug( "Initial=[" + initial + "] and last=[" + last + "] now=[" + now + "], should be=["
                   + ( now - initial ) + "], diff=[" + ( last - initial )
                   + "] - we would expect this to be a zero diff" );
    }
}
