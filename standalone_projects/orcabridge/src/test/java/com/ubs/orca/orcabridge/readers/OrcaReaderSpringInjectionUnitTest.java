/*
 * OrcaReaderSpringInjectionUnitTest.java created on 25 Sep 2009 18:14:12 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.readers;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ubs.orca.orcabridge.IMessageReader;
import com.ubs.orca.orcabridge.OrcaBridgeException;
import com.ubs.orca.orcabridge.support.OrcaClientTestStub;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test suite to test the Orca Reader Spring Injection.
 * 
 * @author suggitpe
 * @version 1.0 25 Sep 2009
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:xml/it-orca-bridge-spring-injection-test.xml" })
public class OrcaReaderSpringInjectionUnitTest
{

    private static final Log LOG = LogFactory.getLog( OrcaReaderSpringInjectionUnitTest.class );

    @Resource(name = "orcaReader")
    IMessageReader mOrcaReader_;

    @BeforeClass
    public static void doBeforeClass()
    {
        LOG.debug( "=================== " + OrcaReaderSpringInjectionUnitTest.class.getSimpleName() );
    }

    @Before
    public void doBefore()
    {
        LOG.debug( "------------------- " );
    }

    @After
    public void doAfter()
    {
        LOG.debug( "------------------- " );
    }

    /**
     * Tests that the Orca Reader will work correctly with Spring
     * injection.
     */
    @Test
    public void testSpringInjectedOrcaReader()
    {
        LOG.debug( "Testing that the Orca Reader has been injected correctly" );
        Assert.assertNotNull( mOrcaReader_ );
    }

    /**
     * Tests that the
     * 
     * @throws OrcaBridgeException
     */
    @Test
    public void testStartAndStopOrcaReader() throws OrcaBridgeException
    {
        OrcaSingleMessageReader reader = (OrcaSingleMessageReader) mOrcaReader_;
        reader.setOrcaClient( new OrcaClientTestStub() );
        mOrcaReader_.startReader();

    }
}
