/*
 * SingletonTestCase.java created on 24 Aug 2007 06:07:47 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.creational;

import org.suggs.sandbox.patterns.AbstractPatternTestCase;
import org.suggs.sandbox.patterns.creational.singleton.SingletonImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * TestCase for the singleton pattern.
 * 
 * @author suggitpe
 * @version 1.0 24 Aug 2007
 */
public class SingletonTestCase extends AbstractPatternTestCase
{

    private static final Log LOG = LogFactory.getLog( SingletonTestCase.class );

    @Test
    public void testSingletonCreate()
    {
        LOG.debug( "Calling Singleton for the first time" );

        long initial = new Long( SingletonImpl.instance().getConstructionTime() ).longValue();

        try
        {
            LOG.debug( "Sleeping" );
            Thread.sleep( 500 );
        }
        catch ( InterruptedException ie )
        {
            // nadda
        }

        long last = new Long( SingletonImpl.instance().getConstructionTime() ).longValue();
        long now = new Long( System.currentTimeMillis() ).longValue();

        LOG.debug( "Initial=[" + initial + "] and last=[" + last + "] now=[" + now
                   + "], should be=[" + ( now - initial ) + "], diff=[" + ( last - initial )
                   + "] - we would expect this to be a zero diff" );
    }
}
