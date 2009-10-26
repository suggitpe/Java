/*
 * OrcaReaderTest.java created on 24 Sep 2009 06:54:48 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.readers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Assert;
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

/**
 * Test suite for the orca reader.
 * 
 * @author suggitpe
 * @version 1.0 24 Sep 2009
 */
public class OrcaReaderUnitTest
{

    private static final Log LOG = LogFactory.getLog( OrcaReaderUnitTest.class );
    private IMocksControl mCtrl_;
    private OrcaSingleMessageReader mOrcaReader_;
    private IOrcaClient mMockOrcaClient_;
    private IMessageProcessor mMockMessageProcessor_;

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
        mCtrl_ = EasyMock.createControl();
        mMockOrcaClient_ = mCtrl_.createMock( IOrcaClient.class );
        mMockMessageProcessor_ = mCtrl_.createMock( IMessageProcessor.class );
        mOrcaReader_ = new OrcaSingleMessageReader();
        mOrcaReader_.setOrcaIdentity( new OrcaIdentity( ORCA_TOKEN ) );
        mOrcaReader_.setOrcaConnectionUrl( ORCA_URL );
        mOrcaReader_.setMessageProcessor( mMockMessageProcessor_ );
        mOrcaReader_.afterPropertiesSet();
        mOrcaReader_.init();
        mOrcaReader_.setOrcaClient( mMockOrcaClient_ );
    }

    /**
     * Test that the correct exception is thrown when there is no orca
     * client set on the class.
     * 
     * @throws Exception
     */
    @Test(expected = OrcaBridgeException.class)
    public void testOrcaReaderWithNullOrcaClient() throws Exception
    {
        mOrcaReader_.setOrcaClient( null );

        mCtrl_.replay();

        mOrcaReader_.startReader();

        mCtrl_.verify();

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
        mMockOrcaClient_.connect();
        EasyMock.expectLastCall().once();
        mMockOrcaClient_.start();
        EasyMock.expectLastCall().once();

        mCtrl_.replay();

        Assert.assertSame( "OrcaReader state is not correct:",
                           mOrcaReader_.getState(),
                           AbstractMessageReader.STATE_UNINITIALISED );
        mOrcaReader_.startReader();
        Assert.assertSame( "OrcaReader state is not correct:",
                           mOrcaReader_.getState(),
                           AbstractMessageReader.STATE_RUNNING );

        mCtrl_.verify();
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
        mMockOrcaClient_.connect();
        EasyMock.expectLastCall()
            .andThrow( new OrcaException( "This is an expected exception thrown from the OrcaBridge" ) );

        mCtrl_.replay();

        Assert.assertSame( "OrcaReader state is not correct:",
                           mOrcaReader_.getState(),
                           AbstractMessageReader.STATE_UNINITIALISED );
        mOrcaReader_.startReader();
        Assert.fail( "Test should not have reached this part of the test" );

        mCtrl_.verify();
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
        mMockOrcaClient_.stop();
        EasyMock.expectLastCall().once();
        mMockOrcaClient_.disconnect();
        EasyMock.expectLastCall().once();

        mCtrl_.replay();

        mOrcaReader_.stopReader();

        mCtrl_.verify();
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
        mMockOrcaClient_.stop();
        EasyMock.expectLastCall().once();
        mMockOrcaClient_.disconnect();
        EasyMock.expectLastCall()
            .andThrow( new OrcaException( "This is an expected exception to be thrown" ) );

        mCtrl_.replay();

        mOrcaReader_.stopReader();

        Assert.fail( "The test should not have reached this part of the code" );

        mCtrl_.verify();
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
        mMockOrcaClient_.stop();
        EasyMock.expectLastCall().andThrow( new OrcaException( "This is all part of the tests" ) );
        mMockOrcaClient_.disconnect();
        EasyMock.expectLastCall().once();

        mCtrl_.replay();

        mOrcaReader_.stopReader();

        mCtrl_.verify();
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
        OrcaReaderCallback callback = mOrcaReader_.new OrcaReaderCallback();
        mMockMessageProcessor_.processMessage( EasyMock.isA( IMessageFacade.class ) );
        EasyMock.expectLastCall().once();

        IAttributesConversationMessage msg = mCtrl_.createMock( IAttributesConversationMessage.class );

        mCtrl_.replay();

        callback.onReceived( msg );

        mCtrl_.verify();
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
        OrcaReaderCallback callback = mOrcaReader_.new OrcaReaderCallback();
        mMockMessageProcessor_.processMessage( EasyMock.isA( IMessageFacade.class ) );
        EasyMock.expectLastCall().once();

        ITextConversationMessage msg = mCtrl_.createMock( ITextConversationMessage.class );

        mCtrl_.replay();

        callback.onReceived( msg );

        mCtrl_.verify();
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
        OrcaReaderCallback callback = mOrcaReader_.new OrcaReaderCallback();
        mMockMessageProcessor_.processMessage( EasyMock.isA( IMessageFacade.class ) );
        EasyMock.expectLastCall()
            .andThrow( new OrcaBridgeException( "This is all part of the test" ) );

        IAttributesConversationMessage msg = EasyMock.createMock( IAttributesConversationMessage.class );

        mCtrl_.replay();

        callback.onReceived( msg );

        Assert.fail( "Test test should not have reached this far" );

        mCtrl_.verify();
    }
}
