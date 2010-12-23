/*
 * JmsMessageReaderIntegrationTest.java created on 11 Nov 2009 06:14:36 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.jms;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ubs.orca.orcabridge.IMessageFacade;
import com.ubs.orca.orcabridge.IMessageReader;
import com.ubs.orca.orcabridge.jmsclient.IJmsClient;
import com.ubs.orca.orcabridge.jmsclient.impl.JmsSenderAction;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Test to validate that the JMS Reader process works correctly. There
 * is an implication in this test that the sender process works too,
 * so if this test fails in the before section, the issue is likely to
 * be in the JMS sender.
 * 
 * @author suggitpe
 * @version 1.0 11 Nov 2009
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:xml/it-jms-message-reader-test.xml" })
public class JmsMessageReaderIntegrationTest
{

    private static final Logger LOG = LoggerFactory.getLogger( JmsMessageReaderIntegrationTest.class );

    @Resource(name = "jmsMessageReader")
    private IMessageReader jmsMessageReader_;

    @Resource(name = "jmsClient")
    private IJmsClient jmsClient_;

    @Resource(name = "messageFacade")
    private IMessageFacade messageFacade_;

    /** */
    @BeforeClass
    public static void doBeforeClass()
    {
        LOG.debug( "=================== " + JmsMessageReaderIntegrationTest.class.getSimpleName() );
    }

    /** @throws Exception */
    @Before
    public void doBefore() throws Exception
    {
        LOG.debug( "------------------- " );
        jmsClient_.connect();

        jmsClient_.processAction( new JmsSenderAction( messageFacade_ ) );

        jmsClient_.disconnect();
    }

    /** */
    @After
    public void doAfter()
    {
        LOG.debug( "------------------- " );
    }

    /**
     * Tests that the Spring injection has worked correctly
     * 
     * @throws Exception
     */
    @Test
    public void testSpringInjection() throws Exception
    {
        assertThat( jmsMessageReader_, notNullValue() );
    }

    /**
     * Tests that we can connect to the broker and retrieve all of the
     * messages on tehe durable.
     * 
     * @throws Exception
     */
    @Test
    public void testReadAllMessagesFromDurable() throws Exception
    {
        jmsMessageReader_.startReader();
    }
}
