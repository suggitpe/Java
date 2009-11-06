/*
 * OrcaMessageSenderSpringInjectionTest.java created on 4 Nov 2009 09:38:28 by suggitpe for project Orca Bridge
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
 * Tests that the Orca sender spring injection works OK.
 * 
 * @author suggitpe
 * @version 1.0 4 Nov 2009
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:xml/ut-orca-message-sender-spring-injection-test.xml" })
public class OrcaMessageSenderSpringInjectionTest
{

    private static final Log LOG = LogFactory.getLog( OrcaMessageSenderSpringInjectionTest.class );

    @Resource(name = "orcaMessageSender")
    private IMessageProcessor orcaMessageSender_;

    /** */
    @BeforeClass
    public static void doBeforeClass()
    {
        LOG.debug( "=================== "
                   + OrcaMessageSenderSpringInjectionTest.class.getSimpleName() );
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
     * Test that the spring injected Orca message sender works.
     */
    @Test
    public void testSpringInjectionOrcaMessageSender()
    {
        assertThat( orcaMessageSender_, notNullValue() );
    }

}
