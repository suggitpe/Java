/*
 * JmsReaderTest.java created on 24 Sep 2009 06:54:48 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.readers;

import javax.jms.Message;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ubs.orca.orcabridge.IMessageFacade;
import com.ubs.orca.orcabridge.IMessageProcessor;
import com.ubs.orca.orcabridge.OrcaBridgeException;
import com.ubs.orca.orcabridge.jmsclient.IJmsClientSingleMsgCallback;
import com.ubs.orca.orcabridge.jmsclient.IJmsReaderClient;
import com.ubs.orca.orcabridge.jmsclient.JmsClientException;
import com.ubs.orca.orcabridge.readers.JmsSingleMessageReader.JmsReaderCallback;

/**
 * Test suite for the JMS reader.
 * 
 * @author suggitpe
 * @version 1.0 24 Sep 2009
 */
public class JmsReaderUnitTest
{

    private static final Log LOG = LogFactory.getLog( JmsReaderUnitTest.class );
    private IMocksControl mCtrl_ = EasyMock.createControl();
    private JmsSingleMessageReader mJmsReader_;
    private IJmsReaderClient mMockJmsClient_;
    private IMessageProcessor mMockMessageProcessor_;

    private static String EMS_URL = "tcp://localhost:7222";

    @BeforeClass
    public static void doBeforeClass()
    {
        LOG.debug( "=================== " + JmsReaderUnitTest.class.getSimpleName() );
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
        mMockJmsClient_ = mCtrl_.createMock( IJmsReaderClient.class );
        mMockMessageProcessor_ = mCtrl_.createMock( IMessageProcessor.class );
        mJmsReader_ = new JmsSingleMessageReader( JmsSingleMessageReader.DEFAULT_CONTEXT_FACTORY,
                                                  EMS_URL,
                                                  mMockMessageProcessor_ );
        mJmsReader_.setJmsClient( mMockJmsClient_ );

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
        mMockJmsClient_.connect();
        EasyMock.expectLastCall().once();

        mMockJmsClient_.startDurableSubscription( EasyMock.isA( IJmsClientSingleMsgCallback.class ) );
        EasyMock.expectLastCall().once();

        mCtrl_.replay();

        mJmsReader_.afterPropertiesSet();
        Assert.assertSame( "JmsReader state is not correct:",
                           mJmsReader_.getState(),
                           AbstractMessageReader.STATE_UNINITIALISED );
        mJmsReader_.startReader();
        Assert.assertSame( "JmsReader state is not correct:",
                           mJmsReader_.getState(),
                           AbstractMessageReader.STATE_RUNNING );

        mCtrl_.verify();
    }

    @Test(expected = OrcaBridgeException.class)
    public void testStartWithNoJmsClient() throws OrcaBridgeException
    {
        mJmsReader_.setJmsClient( null );
        mCtrl_.replay();
        mJmsReader_.startReader();
        mCtrl_.verify();
    }

    /**
     * Tests that when the client starts badly, that the exceptions
     * are handled correctly.
     * 
     * @throws Exception
     */
    @Test(expected = OrcaBridgeException.class)
    public void testBadStartFromConnect() throws Exception
    {
        mMockJmsClient_.connect();
        EasyMock.expectLastCall()
            .andThrow( new JmsClientException( "This is an expected exception thrown from the OrcaBridge" ) );

        mCtrl_.replay();

        mJmsReader_.afterPropertiesSet();
        Assert.assertSame( "JmsReader state is not correct:",
                           mJmsReader_.getState(),
                           AbstractMessageReader.STATE_UNINITIALISED );
        mJmsReader_.startReader();
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
        mMockJmsClient_.stopDurbleSubscription();
        EasyMock.expectLastCall().once();
        mMockJmsClient_.disconnect();
        EasyMock.expectLastCall().once();

        mCtrl_.replay();

        mJmsReader_.afterPropertiesSet();
        mJmsReader_.stopReader();

        mCtrl_.verify();
    }

    @Test
    public void testBadStopFromStopDurableButThenDisconnectOK() throws Exception
    {
        mMockJmsClient_.stopDurbleSubscription();
        EasyMock.expectLastCall()
            .andThrow( new JmsClientException( "This exception is all part of the test" ) );
        mMockJmsClient_.disconnect();
        EasyMock.expectLastCall().once();

        mCtrl_.replay();

        mJmsReader_.afterPropertiesSet();
        mJmsReader_.stopReader();

        mCtrl_.verify();
    }

    /**
     * Tests that when the client stops badly, that all exceptions are
     * handled correctly.
     * 
     * @throws Exception
     */
    @Test(expected = OrcaBridgeException.class)
    public void testBadStopFromDisconnect() throws Exception
    {
        mMockJmsClient_.stopDurbleSubscription();
        EasyMock.expectLastCall().once();
        mMockJmsClient_.disconnect();
        EasyMock.expectLastCall()
            .andThrow( new JmsClientException( "This is an expected exception to be thrown" ) );

        mCtrl_.replay();

        mJmsReader_.afterPropertiesSet();
        mJmsReader_.stopReader();

        Assert.fail( "The test should not have reached this part of the code" );

        mCtrl_.verify();
    }

    /**
     * Tests that the message callback is called and that it then
     * delegates to the message processor.
     * 
     * @throws Throwable
     */
    @Test
    public void testJmsCallback() throws Throwable
    {
        JmsReaderCallback callback = mJmsReader_.new JmsReaderCallback();
        mMockMessageProcessor_.processMessage( EasyMock.isA( IMessageFacade.class ) );
        EasyMock.expectLastCall().once();

        Message msg = EasyMock.createMock( Message.class );

        mCtrl_.replay();

        callback.onReceived( msg );

        mCtrl_.verify();
    }

    @Test(expected = OrcaBridgeException.class)
    public void testJmsCallbackWithProcessFailure() throws Throwable
    {
        JmsReaderCallback callback = mJmsReader_.new JmsReaderCallback();
        mMockMessageProcessor_.processMessage( EasyMock.isA( IMessageFacade.class ) );
        EasyMock.expectLastCall()
            .andThrow( new OrcaBridgeException( "This is all part of the test" ) );

        Message msg = EasyMock.createMock( Message.class );

        mCtrl_.replay();

        callback.onReceived( msg );

        Assert.fail( "Test test should not have reached this far" );

        mCtrl_.verify();
    }
}
