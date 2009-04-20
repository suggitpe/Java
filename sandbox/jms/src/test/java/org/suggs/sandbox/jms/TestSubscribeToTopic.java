/*
 * TestPublishToTopic.java created on 15 Apr 2009 06:58:07 by suggitpe for project SandBox - JMS
 * 
 */
package org.suggs.sandbox.jms;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * Test to verify that we can subscribe to a topic on a known broker.
 * 
 * @author suggitpe
 * @version 1.0 15 Apr 2009
 */
public class TestSubscribeToTopic
{

    private static final Log LOG = LogFactory.getLog( TestSubscribeToTopic.class );

    @Test
    public void testSubscribeToSingleMessage() throws Exception
    {
        SimpleSubscriber s = new SimpleSubscriber();

        LOG.debug( "Testing that we can subscribe to a Topic" );

        s.subscribeToTopic( "TestTopic" );
    }

}
