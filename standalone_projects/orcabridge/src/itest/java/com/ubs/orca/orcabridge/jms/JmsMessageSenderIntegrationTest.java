/*
 * JmsMessageSenderIntegrationTest.java created on 29 Oct 2009 07:16:16 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.jms;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ubs.orca.orcabridge.IMessageFacade;
import com.ubs.orca.orcabridge.IMessageProcessor;
import com.ubs.orca.orcabridge.OrcaBridgeException;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Tests suite that will work exactly like the way we expect the JMS
 * Message Sender to be used when fully deployed. Please see the
 * spring configuration for details on how this should be configured
 * in a non test based scenario.
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
    private IMessageProcessor jmsMessageSender_;

    @Resource(name = "messageFacade")
    private IMessageFacade messageFacade_;

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

    /**
     * Tests that we can indeed send a message to the JMS broker.
     * 
     * @throws OrcaBridgeException
     */
    @Test
    public void testSendMessageToJmsBroker() throws OrcaBridgeException
    {
        LOG.debug( "Testing the send of a JMS message to the broker" );
        assertThat( jmsMessageSender_, notNullValue() );

        LOG.debug( "Processing message ..." );
        jmsMessageSender_.processMessage( messageFacade_ );
    }
}
