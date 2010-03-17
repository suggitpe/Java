/*
 * JmsMessageSenderSprintInjectionTest.java created on 4 Nov 2009 09:19:25 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.processors;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ubs.orca.orcabridge.IMessageProcessor;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Tests that the JMS sender spring injection works OK. This relies on
 * stub implementations of the key parts that are injected.
 * 
 * @author suggitpe
 * @version 1.0 4 Nov 2009
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:xml/ut-jms-message-sender-spring-injection-test.xml" })
public class JmsMessageSenderSprintInjectionTest
{

    private static final Log LOG = LogFactory.getLog( JmsMessageSenderSprintInjectionTest.class );

    @Resource(name = "jmsMessageSender")
    private IMessageProcessor jmsMessageSender;

    /** */
    @BeforeClass
    public static void doBeforeClass()
    {
        LOG.debug( "=================== "
                   + JmsMessageSenderSprintInjectionTest.class.getSimpleName() );
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
     * Test that the spring injected JMS message sender works.
     */
    @Test
    public void testSpringInjectionJmsMessageSender()
    {
        assertThat( jmsMessageSender, notNullValue() );
    }
}
