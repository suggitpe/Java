/*
 * JmsMessageReaderIntegrationTest.java created on 11 Nov 2009 06:14:36 by suggitpe for project Orca Bridge
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

import com.ubs.orca.orcabridge.IMessageReader;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

    private static final Log LOG = LogFactory.getLog( JmsMessageReaderIntegrationTest.class );

    @Resource(name = "jmsMessageReader")
    private IMessageReader jmsMessageReader_;

    /** */
    @BeforeClass
    public static void doBeforeClass()
    {
        LOG.debug( "=================== " + JmsMessageReaderIntegrationTest.class.getSimpleName() );
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
     * Tests that the Spring injection has worked correctly
     * 
     * @throws Exception
     */
    @Test
    public void testSpringInjection() throws Exception
    {}
}
