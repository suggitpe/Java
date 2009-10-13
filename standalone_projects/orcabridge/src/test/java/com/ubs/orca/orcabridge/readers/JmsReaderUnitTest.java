/*
 * JmsReaderTest.java created on 24 Sep 2009 06:54:48 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.readers;

import javax.jms.Message;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymock.EasyMock;
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
        mMockJmsClient_ = EasyMock.createMock( IJmsReaderClient.class );
        mMockMessageProcessor_ = EasyMock.createMock( IMessageProcessor.class );
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

        EasyMock.replay( mMockJmsClient_ );

        mJmsReader_.afterPropertiesSet();
        Assert.assertSame( "JmsReader state is not correct:",
                           mJmsReader_.getState(),
                           AbstractMessageReader.STATE_UNINITIALISED );
        mJmsReader_.startReader();
        Assert.assertSame( "JmsReader state is not correct:",
                           mJmsReader_.getState(),
                           AbstractMessageReader.STATE_RUNNING );

        EasyMock.verify( mMockJmsClient_ );
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
        mMockJmsClient_.connect();
        EasyMock.expectLastCall()
            .andThrow( new JmsClientException( "This is an expected exception thrown from the OrcaBridge" ) );

        EasyMock.replay( mMockJmsClient_ );

        mJmsReader_.afterPropertiesSet();
        Assert.assertSame( "JmsReader state is not correct:",
                           mJmsReader_.getState(),
                           AbstractMessageReader.STATE_UNINITIALISED );
        mJmsReader_.startReader();
        Assert.fail( "Test should not have reached this part of the test" );

        EasyMock.verify( mMockJmsClient_ );
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

        EasyMock.replay( mMockJmsClient_ );

        mJmsReader_.afterPropertiesSet();
        mJmsReader_.stopReader();

        EasyMock.verify( mMockJmsClient_ );
    }

    /**
     * Tests that when the client stops badly, that all exceptions are
     * handled correctly.
     * 
     * @throws Exception
     */
    @Test(expected = OrcaBridgeException.class)
    public void testBadStop() throws Exception
    {
        mMockJmsClient_.stopDurbleSubscription();
        EasyMock.expectLastCall().once();
        mMockJmsClient_.disconnect();
        EasyMock.expectLastCall()
            .andThrow( new JmsClientException( "This is an expected exception to be thrown" ) );

        EasyMock.replay( mMockJmsClient_ );

        mJmsReader_.afterPropertiesSet();
        mJmsReader_.stopReader();

        Assert.fail( "The test should not have reached this part of the code" );

        EasyMock.verify( mMockJmsClient_ );
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

        EasyMock.replay( mMockMessageProcessor_ );

        callback.onReceived( msg );

        EasyMock.verify( mMockMessageProcessor_ );
    }
}
