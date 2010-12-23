/*
 * TestPublishToTopic.java created on 15 Apr 2009 06:58:07 by suggitpe for project SandBox - JMS
 * 
 */
package org.suggs.sandbox.jms;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test to verify that we can send to a topic on a known broker.
 * 
 * @author suggitpe
 * @version 1.0 15 Apr 2009
 */
public class TestPublishToTopic {

    private static final Logger LOG = LoggerFactory.getLogger( TestPublishToTopic.class );

    /**
     * @throws Exception
     */
    @Test
    public void testSendTextMessageToTopic() throws Exception {
        SimplePublisher p = new SimplePublisher();

        LOG.debug( "Testing that we can send to a Topic" );

        p.sendMessageToTopic( "TestTopic" );
    }

}
