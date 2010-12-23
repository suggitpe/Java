/*
 * TestPublishToTopic.java created on 15 Apr 2009 06:58:07 by suggitpe for project SandBox - JMS
 * 
 */
package org.suggs.sandbox.jms;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test to verify that we can subscribe to a topic on a known broker.
 * 
 * @author suggitpe
 * @version 1.0 15 Apr 2009
 */
public class TestSubscribeToTopic {

    private static final Logger LOG = LoggerFactory.getLogger( TestSubscribeToTopic.class );

    /**
     * @throws Exception
     */
    @Test
    public void testSubscribeToSingleMessage() throws Exception {
        SimpleSubscriber s = new SimpleSubscriber();

        LOG.debug( "Testing that we can subscribe to a Topic" );

        s.subscribeToTopic( "TestTopic" );
    }

}
