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

import com.ubs.orca.client.api.IAttributesConversationMessage;
import com.ubs.orca.client.api.IOrcaClient;
import com.ubs.orca.client.api.ITextConversationMessage;
import com.ubs.orca.client.api.OrcaException;
import com.ubs.orca.client.api.OrcaIdentity;
import com.ubs.orca.orcabridge.IMessageFacade;
import com.ubs.orca.orcabridge.IMessageProcessor;
import com.ubs.orca.orcabridge.OrcaBridgeException;
import com.ubs.orca.orcabridge.readers.OrcaSingleMessageReader.OrcaReaderCallback;

import static org.easymock.EasyMock.createControl;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.isA;
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
    private IMocksControl ctrl_;
    private OrcaSingleMessageReader orcaReader_;
    private IOrcaClient mockOrcaClient_;
    private IMessageProcessor mockMessageProcessor_;

    private static String ORCA_TOKEN = "OrcaBridgeTestToken:1";
    private static String ORCA_URL = "tcp://localhost:7222";

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
        ctrl_ = createControl();
        mockOrcaClient_ = ctrl_.createMock( IOrcaClient.class );
        mockMessageProcessor_ = ctrl_.createMock( IMessageProcessor.class );
        orcaReader_ = new OrcaSingleMessageReader();
        orcaReader_.setOrcaIdentity( new OrcaIdentity( ORCA_TOKEN ) );
        orcaReader_.setOrcaConnectionUrl( ORCA_URL );
        orcaReader_.setMessageProcessor( mockMessageProcessor_ );
        orcaReader_.afterPropertiesSet();
        orcaReader_.init();
        orcaReader_.setOrcaClient( mockOrcaClient_ );
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
        mockOrcaClient_.connect();
        expectLastCall().once();
        mockOrcaClient_.start();
        expectLastCall().once();

        ctrl_.replay();

        assertThat( orcaReader_.getState(), equalTo( AbstractMessageReader.STATE_UNINITIALISED ) );
        orcaReader_.startReader();
        assertThat( orcaReader_.getState(), equalTo( AbstractMessageReader.STATE_RUNNING ) );

        ctrl_.verify();
    }

    /**
     * Tests that when the client starts badly, that the exceptions
     * are handled correctly.
     * 
     * @throws Exception
     */
    @Test(expected = OrcaBridgeException.class)
    public void testBadStart() throws Exception
    {
        mockOrcaClient_.connect();
        expectLastCall().andThrow( new OrcaException( "This is an expected exception thrown from the OrcaBridge" ) );

        ctrl_.replay();

        orcaReader_.startReader();
        fail( "Test should not have reached this part of the test" );

        ctrl_.verify();
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
        mockOrcaClient_.stop();
        expectLastCall().once();
        mockOrcaClient_.disconnect();
        expectLastCall().once();

        ctrl_.replay();

        orcaReader_.stopReader();

        ctrl_.verify();
    }

    /**
     * Tests that when the client stops badly, that all exceptions are
     * handled correctly.
     * 
     * @throws Exception
     */
    @Test(expected = OrcaBridgeException.class)
    public void testBadStopFromOrcaDisconnect() throws Exception
    {
        mockOrcaClient_.stop();
        expectLastCall().once();
        mockOrcaClient_.disconnect();
        expectLastCall().andThrow( new OrcaException( "This is an expected exception to be thrown" ) );

        ctrl_.replay();

        orcaReader_.stopReader();
        fail( "The test should not have reached this part of the code" );

        ctrl_.verify();
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
        mockOrcaClient_.stop();
        expectLastCall().andThrow( new OrcaException( "This is all part of the tests" ) );
        mockOrcaClient_.disconnect();
        expectLastCall().once();

        ctrl_.replay();

        orcaReader_.stopReader();

        ctrl_.verify();
    }

    /**
     * Tests that the message callback is called and that it then
     * delegates to the message processor.
     * 
     * @throws Throwable
     */
    @Test
    public void testOrcaCallbackWithAttributesMessage() throws Throwable
    {
        OrcaReaderCallback callback = orcaReader_.new OrcaReaderCallback();
        mockMessageProcessor_.processMessage( isA( IMessageFacade.class ) );
        expectLastCall().once();

        IAttributesConversationMessage msg = ctrl_.createMock( IAttributesConversationMessage.class );

        ctrl_.replay();

        callback.onReceived( msg );

        ctrl_.verify();
    }

    /**
     * Tests that the message callback is called and that it then
     * delegates to the message processor.
     * 
     * @throws Throwable
     */
    @Test
    public void testOrcaCallbackWithTextMessage() throws Throwable
    {
        OrcaReaderCallback callback = orcaReader_.new OrcaReaderCallback();
        mockMessageProcessor_.processMessage( isA( IMessageFacade.class ) );
        expectLastCall().once();

        ITextConversationMessage msg = ctrl_.createMock( ITextConversationMessage.class );

        ctrl_.replay();

        callback.onReceived( msg );

        ctrl_.verify();
    }

    /**
     * Test that the correc texception pops out of teh top of the
     * stack when there is an issue in teh message processor layer.
     * 
     * @throws Throwable
     */
    @Test(expected = OrcaBridgeException.class)
    public void testOrcaCallbackWithProcessFailure() throws Throwable
    {
        OrcaReaderCallback callback = orcaReader_.new OrcaReaderCallback();
        mockMessageProcessor_.processMessage( isA( IMessageFacade.class ) );
        expectLastCall().andThrow( new OrcaBridgeException( "This is all part of the test" ) );

        IAttributesConversationMessage msg = ctrl_.createMock( IAttributesConversationMessage.class );

        ctrl_.replay();

        callback.onReceived( msg );
        fail( "Test test should not have reached this far" );

        ctrl_.verify();
    }
}
