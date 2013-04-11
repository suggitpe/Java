/*
 * TestPublishToTopic.java created on 15 Apr 2009 06:58:07 by suggitpe for project SandBox - JMS
 * 
 */
package org.suggs.sandbox.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerContext;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.Topic;
import java.util.Map;

public class TestPublishToTopic {

    private static final Logger LOG = LoggerFactory.getLogger(TestPublishToTopic.class);
    public static final String TEST_TOPIC = "dynamicTopics/TestTopic";
    public static final String BIND_ADDRESS = "tcp://localhost:61616";
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
    public void testSendTextMessageToTopic() throws Exception {
        SimplePublisher p = new SimplePublisher();

        LOG.debug("Testing that we can send to a Topic");

        p.sendMessageToTopic(TEST_TOPIC);
    }

}
