/*
 * OrcaReaderTest.java created on 24 Sep 2009 06:54:48 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.readers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ubs.orca.client.api.IOrcaClient;
import com.ubs.orca.client.api.OrcaException;
import com.ubs.orca.orcabridge.OrcaBridgeException;

import static org.easymock.EasyMock.createControl;
import static org.easymock.EasyMock.expectLastCall;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Test suite for the orca reader.
 * 
 * @author suggitpe
 * @version 1.0 24 Sep 2009
 */
public class OrcaReaderUnitTest
{

    private static final Log LOG = LogFactory.getLog( OrcaReaderUnitTest.class );
    private IMocksControl ctrl;
    private OrcaSingleMessageReader orcaReader;
    private IOrcaClient mockOrcaClient;

    /** */
    @BeforeClass
    public static void doBeforeClass()
    {
        LOG.debug( "=================== " + OrcaReaderUnitTest.class.getSimpleName() );
    }

    /**
     * Test setup
     * 
     * @throws Exception
     */
    @Before
    public void doBefore() throws Exception
    {
        LOG.debug( "-----------------" );
        ctrl = createControl();
        mockOrcaClient = ctrl.createMock( IOrcaClient.class );

        orcaReader = new OrcaSingleMessageReader();
        orcaReader.setOrcaClient( mockOrcaClient );

        orcaReader.afterPropertiesSet();
    }

    /**
     * Tests that when a client starts correctly, that no exceptions
     * are thrown etc
     * 
     * @throws Exception
     */
    @Test
    public void testCleanStart() throws Exception
    {
        mockOrcaClient.connect();
        expectLastCall().once();
        mockOrcaClient.start();
        expectLastCall().once();

        ctrl.replay();

        assertThat( orcaReader.getState(), equalTo( AbstractMessageReader.STATE_UNINITIALISED ) );
        orcaReader.startReader();
        assertThat( orcaReader.getState(), equalTo( AbstractMessageReader.STATE_RUNNING ) );

        ctrl.verify();
    }

    /**
     * Tests that when the client starts badly, that the exceptions
     * are handled correctly.
     * 
     * @throws Exception
     */
    @Test(expected = OrcaBridgeException.class)
    public void testStartButFailOnConnect() throws Exception
    {
        mockOrcaClient.connect();
        expectLastCall().andThrow( new OrcaException( "This is an expected exception thrown from the OrcaBridge" ) );

        ctrl.replay();

        orcaReader.startReader();
        fail( "Test should not have reached this part of the test" );

        ctrl.verify();
    }

    /**
     * Tests that when the client stops cleanly that the application
     * is deemed to be in a good running state
     * 
     * @throws Exception
     */
    @Test
    public void testCleanStop() throws Exception
    {
        mockOrcaClient.stop();
        expectLastCall().once();
        mockOrcaClient.disconnect();
        expectLastCall().once();

        ctrl.replay();

        orcaReader.stopReader();

        ctrl.verify();
    }

    /**
     * Tests that when the client stops badly, that all exceptions are
     * handled correctly.
     * 
     * @throws Exception
     */
    @Test
    public void testBadStopFromOrcaStop() throws Exception
    {
        mockOrcaClient.stop();
        expectLastCall().andThrow( new OrcaException( "This is all part of the tests" ) );
        mockOrcaClient.disconnect();
        expectLastCall().once();

        ctrl.replay();

        orcaReader.stopReader();

        ctrl.verify();
    }

    /**
     * Tests that when the client stops badly, that all exceptions are
     * handled correctly.
     * 
     * @throws Exception
     */
    @Test(expected = OrcaBridgeException.class)
    public void testStopButFailFromDisconnect() throws Exception
    {
        mockOrcaClient.stop();
        expectLastCall().once();
        mockOrcaClient.disconnect();
        expectLastCall().andThrow( new OrcaException( "This is an expected exception to be thrown" ) );

        ctrl.replay();

        orcaReader.stopReader();
        fail( "The test should not have reached this part of the code" );

        ctrl.verify();
    }

}
