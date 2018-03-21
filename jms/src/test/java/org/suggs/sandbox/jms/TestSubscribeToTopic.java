/*
 * TestPublishToTopic.java created on 15 Apr 2009 06:58:07 by suggitpe for project SandBox - JMS
 * 
 */
package org.suggs.sandbox.jms;

import org.apache.activemq.broker.BrokerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestSubscribeToTopic {

    private static final Logger LOG = LoggerFactory.getLogger( TestSubscribeToTopic.class );
    private static final String BIND_ADDRESS = "tcp://localhost:61616";
    public static final String DESTINATION_NAME = "dynamicTopics/TestTopic";
    private BrokerService broker;

    @Before
    public void setupTopic() throws Exception {
        broker = new BrokerService();
        broker.setPersistent(false);
        broker.addConnector(BIND_ADDRESS);
        broker.start();
    }

    @After
    public void tearDown() throws Exception {
        broker.stop();
    }



    @Test
    public void testSubscribeToSingleMessage() throws Exception {
        SimpleSubscriber s = new SimpleSubscriber();

        LOG.debug( "Testing that we can subscribe to a Topic" );

        s.subscribeToTopic(DESTINATION_NAME);
    }

}
