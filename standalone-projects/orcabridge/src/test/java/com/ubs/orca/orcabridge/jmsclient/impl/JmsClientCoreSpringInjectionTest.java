/*
 * JmsClientCoreSpringInjectionTest.java created on 26 Oct 2009 20:47:32 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.jmsclient.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ubs.orca.orcabridge.jmsclient.IJmsClient;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Spring Injection test for the JMS Client Core.
 * 
 * @author suggitpe
 * @version 1.0 26 Oct 2009
 */

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:xml/ut-jms-client-core-spring-injection-test.xml" })
public class JmsClientCoreSpringInjectionTest {

    private static final Log LOG = LogFactory.getLog( JmsClientCoreSpringInjectionTest.class );

    @Resource(name = "jmsClientCore")
    private IJmsClient jmsClientCore;

    /** */
    @BeforeClass
    public static void doBeforeClass() {
        LOG.debug( "=================== " + JmsClientCoreSpringInjectionTest.class.getSimpleName() );
    }

    /** */
    @Before
    public void doBefore() {
        LOG.debug( "------------------- " );
    }

    /** */
    @After
    public void doAfter() {
        LOG.debug( "------------------- " );
    }

    /**
     * Tests that the JMS Client Core has been correctly built with spring injection.
     */
    @Test
    public void testSpringInjectedJmsClientCore() {
        LOG.debug( "Testing that the JMS Client Core has been injected correctly" );
        assertThat( jmsClientCore, notNullValue() );
    }
}
