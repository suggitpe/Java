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

import com.ubs.orca.orcabridge.jmsclient.IJmsClient;
import com.ubs.orca.orcabridge.jmsclient.JmsClientException;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Tests that we can inject a client core, connect and then disconnect
 * from the broker.
 * 
 * @author suggitpe
 * @version 1.0 29 Oct 2009
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:xml/it-jms-client-core-test.xml" })
public class JmsClientCoreIntegrationTest
{

    private static final Log LOG = LogFactory.getLog( JmsClientCoreIntegrationTest.class );

    @Resource(name = "jmsClient")
    private IJmsClient jmsClient_;

    /** */
    @BeforeClass
    public static void doBeforeClass()
    {
        LOG.debug( "=================== " + JmsClientCoreIntegrationTest.class.getSimpleName() );
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
     * Tests that we can connect to the broker
     * 
     * @throws JmsClientException
     */
    @Test
    public void testConnectToBroker() throws JmsClientException
    {
        LOG.debug( "Testing the send of a JMS message to the broker" );
        assertThat( jmsClient_, notNullValue() );

        LOG.debug( "Connecting to the JMS broker" );
        jmsClient_.connect();

        LOG.debug( "Now disconncting from the broker" );
        jmsClient_.disconnect();
    }
}
