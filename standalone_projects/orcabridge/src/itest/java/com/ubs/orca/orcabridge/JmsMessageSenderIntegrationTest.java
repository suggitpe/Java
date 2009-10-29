/*
 * JmsMessageSenderIntegrationTest.java created on 29 Oct 2009 07:16:16 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * TODO Write javadoc for JmsMessageSenderIntegrationTest
 * 
 * @author suggitpe
 * @version 1.0 29 Oct 2009
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:xml/it-jms-message-sender-test.xml" })
public class JmsMessageSenderIntegrationTest
{

    private static final Log LOG = LogFactory.getLog( JmsMessageSenderIntegrationTest.class );

    @Resource(name = "jmsMessageSender")
    IMessageProcessor jmsMessageSender_;

    /** */
    @BeforeClass
    public static void doBeforeClass()
    {
        LOG.debug( "=================== " + JmsMessageSenderIntegrationTest.class.getSimpleName() );
    }

    /** */
    @Before
    public void doBefore()
    {
        LOG.debug( "------------------- " );
    }

    /** */
    @After
    public void doAfter()
    {
        LOG.debug( "------------------- " );
    }

    @Test
    public void testSendMessageToJmsBroker()
    {
        LOG.debug( "Testing the send of a JMS message to the broker" );
        assertThat( jmsMessageSender_, notNullValue() );
    }
}
