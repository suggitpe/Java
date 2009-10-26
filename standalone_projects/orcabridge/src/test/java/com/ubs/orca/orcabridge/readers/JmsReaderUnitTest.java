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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ubs.orca.orcabridge.IMessageFacade;
import com.ubs.orca.orcabridge.IMessageProcessor;
import com.ubs.orca.orcabridge.OrcaBridgeException;
import com.ubs.orca.orcabridge.jmsclient.IJmsAction;
import com.ubs.orca.orcabridge.jmsclient.IJmsClient;
import com.ubs.orca.orcabridge.jmsclient.JmsClientException;
import com.ubs.orca.orcabridge.readers.JmsSingleMessageReader.JmsMessageProcessorCallback;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import static org.hamcrest.CoreMatchers.equalTo;

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
    private IMocksControl mCtrl_;
    private IMessageProcessor mMockProcessor_;
    private IJmsClient mMockJmsClient_;
    private IJmsAction mMockJmsAction_;

    /** */
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
        mCtrl_ = EasyMock.createControl();
        mMockProcessor_ = mCtrl_.createMock( IMessageProcessor.class );
        mMockJmsClient_ = mCtrl_.createMock( IJmsClient.class );
        mMockJmsAction_ = mCtrl_.createMock( IJmsAction.class );

        mJmsReader_ = new JmsSingleMessageReader();
        mJmsReader_.setMessageProcessor( mMockProcessor_ );
        mJmsReader_.setJmsClient( mMockJmsClient_ );
        mJmsReader_.setJmsAction( mMockJmsAction_ );
        mJmsReader_.afterPropertiesSet();
    }

    /**
     * Tests the normal execution of starting the reader
     * 
     * @throws OrcaBridgeException
     * @throws JmsClientException
     */
    @Test
    public void testStartReader() throws OrcaBridgeException, JmsClientException
    {
        mMockJmsClient_.connect();
        EasyMock.expectLastCall().once();
        mMockJmsClient_.processInTransaction( mMockJmsAction_ );
        EasyMock.expectLastCall().once();

        mCtrl_.replay();

        assertThat( mJmsReader_.getState(), equalTo( AbstractMessageReader.STATE_UNINITIALISED ) );
        mJmsReader_.startReader();
        assertThat( mJmsReader_.getState(), equalTo( AbstractMessageReader.STATE_RUNNING ) );

        mCtrl_.verify();
    }

    /**
     * Tests that the reader behaves correctly when faced with an
     * exception when connecting to the JMS broker
     * 
     * @throws OrcaBridgeException
     * @throws JmsClientException
     */
    @Test(expected = OrcaBridgeException.class)
    public void testStartReaderAndFailOnConnect() throws OrcaBridgeException, JmsClientException
    {
        mMockJmsClient_.connect();
        EasyMock.expectLastCall()
            .andThrow( new JmsClientException( "Connect failed: This is all part of the test" ) );

        mCtrl_.replay();
        mJmsReader_.startReader();
        fail( "test should not have reached here" );
        mCtrl_.verify();
    }

    /**
     * Tests that the reader behaves correctly when faced with an
     * exception with processing the action
     * 
     * @throws OrcaBridgeException
     * @throws JmsClientException
     */
    @Test(expected = OrcaBridgeException.class)
    public void testStartReaderAndFailOnProcess() throws OrcaBridgeException, JmsClientException
    {
        mMockJmsClient_.connect();
        EasyMock.expectLastCall().once();
        mMockJmsClient_.processInTransaction( mMockJmsAction_ );
        EasyMock.expectLastCall()
            .andThrow( new JmsClientException( "ProcessInTransaction failed: This is all part of the test" ) );

        mCtrl_.replay();
        mJmsReader_.startReader();
        fail( "Test should not have reached here" );
        mCtrl_.verify();
    }

    /**
     * Tests the normal execution of stopping the reader
     * 
     * @throws OrcaBridgeException
     * @throws JmsClientException
     */
    @Test
    public void testStopReader() throws OrcaBridgeException, JmsClientException
    {
        mMockJmsClient_.disconnect();
        EasyMock.expectLastCall().once();

        mCtrl_.replay();
        mJmsReader_.stopReader();
        mCtrl_.verify();
    }

    /**
     * Tests the normal execution of stopping the reader
     * 
     * @throws OrcaBridgeException
     * @throws JmsClientException
     */
    @Test(expected = OrcaBridgeException.class)
    public void testStopReaderAndFailOnDisconnect() throws OrcaBridgeException, JmsClientException
    {
        mMockJmsClient_.disconnect();
        EasyMock.expectLastCall()
            .andThrow( new JmsClientException( "Disconnect failed: This is all part of the test" ) );

        mCtrl_.replay();
        mJmsReader_.stopReader();

        fail( "Test should not have reached here" );
        mCtrl_.verify();
    }

    /**
     * Tests that the message callback is called and that it then
     * delegates to the message processor.
     * 
     * @throws JmsClientException
     * @throws OrcaBridgeException
     */
    @Test
    public void testJmsCallback() throws JmsClientException, OrcaBridgeException
    {
        JmsMessageProcessorCallback callback = mJmsReader_.new JmsMessageProcessorCallback();
        mMockProcessor_.processMessage( EasyMock.isA( IMessageFacade.class ) );
        EasyMock.expectLastCall().once();

        Message msg = EasyMock.createMock( Message.class );

        mCtrl_.replay();
        callback.onReceived( msg );
        mCtrl_.verify();
    }

    /**
     * @throws JmsClientException
     * @throws OrcaBridgeException
     */
    @Test(expected = JmsClientException.class)
    public void testJmsCallbackWithProcessFailure() throws JmsClientException, OrcaBridgeException
    {
        JmsMessageProcessorCallback callback = mJmsReader_.new JmsMessageProcessorCallback();
        mMockProcessor_.processMessage( EasyMock.isA( IMessageFacade.class ) );
        EasyMock.expectLastCall()
            .andThrow( new OrcaBridgeException( "ProcessMessage failed: This is all part of the test" ) );

        Message msg = EasyMock.createMock( Message.class );

        mCtrl_.replay();

        callback.onReceived( msg );
        fail( "Test test should not have reached this far" );

        mCtrl_.verify();
    }
}
