/*
 * OrcaClientIntegrationTest.java created on 4 Nov 2009 18:48:31 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.orca;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ubs.orca.client.api.IOrcaClient;
import com.ubs.orca.client.api.OrcaException;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Integration test that will instantiate an Orca Client through
 * spring injection.
 * 
 * @author suggitpe
 * @version 1.0 4 Nov 2009
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:xml/it-orca-client-test.xml" })
public class OrcaClientIntegrationTest
{

    private static final Log LOG = LogFactory.getLog( OrcaClientIntegrationTest.class );

    @Resource(name = "orcaClient")
    private IOrcaClient orcaClient_;

    /** */
    @BeforeClass
    public static void doBeforeClass()
    {
        LOG.debug( "=================== " + OrcaClientIntegrationTest.class.getSimpleName() );
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
     * Test that we can inject an instance of the Orca client
     * 
     * @throws OrcaException
     */
    @Test
    public void testSprintInjectionAndConnect() throws OrcaException
    {
        assertThat( orcaClient_, notNullValue() );
        orcaClient_.connect();
    }
}
