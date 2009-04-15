/*
 * TestPublishToTopic.java created on 15 Apr 2009 06:58:07 by suggitpe for project SandBox - JMS
 * 
 */
package org.suggs.sandbox.jms;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * Test to verify that we can send to a topic on a known broker.
 * 
 * @author suggitpe
 * @version 1.0 15 Apr 2009
 */
public class TestPublishToTopic
{

    private static final Log LOG = LogFactory.getLog( TestPublishToTopic.class );

    @Test
    public void testSendTextMessageToTopic() throws Exception
    {
        SimplePublisher p = new SimplePublisher();

        LOG.debug( "Testing that we can send to a Topic" );

        p.sendMessageToTopic( "TestTopic" );
    }

}
