/*
 * JmsSenderTest.java created on 14 Oct 2009 07:05:15 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.jmsclient.impl;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.Context;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ubs.orca.orcabridge.IMessageFacade;
import com.ubs.orca.orcabridge.jmsclient.IJmsSenderClient;
import com.ubs.orca.orcabridge.jmsclient.JmsClientException;

/**
 * Test suite for the JMS sender client.
 * 
 * @author suggitpe
 * @version 1.0 14 Oct 2009
 */
public class JmsSenderTest
{

    private static final Log LOG = LogFactory.getLog( JmsSenderTest.class );

    private IMocksControl mCtrl_;
    private IJmsSenderClient mSender_;
    private Context mMockContext_;
    private Connection mMockConnection_;
    private Destination mMockDestination_;
    private IMessageFacade mMockMessage_;
    private static final String CONNECTION_FACTORY_NAME = "test.connfact.name";
    private static final String DESTINATION_NAME = "test.destination.name";

    @BeforeClass
    public static void doBeforeClass()
    {
        LOG.debug( "=================== " + JmsSenderTest.class.getSimpleName() );
    }

    @Before
    public void doBefore()
    {
        LOG.debug( "-------------" );
        mCtrl_ = EasyMock.createControl();
        mMockContext_ = mCtrl_.createMock( Context.class );
        mSender_ = new JmsSenderClient( mMockContext_, CONNECTION_FACTORY_NAME, DESTINATION_NAME );
        mMockConnection_ = mCtrl_.createMock( Connection.class );
        mMockDestination_ = mCtrl_.createMock( Destination.class );
        mMockMessage_ = mCtrl_.createMock( IMessageFacade.class );

        ( (JmsSenderClient) mSender_ ).setConnection( mMockConnection_ );
        ( (JmsSenderClient) mSender_ ).setDestination( mMockDestination_ );
    }

    @Test(expected = JmsClientException.class)
    public void testSendMessageOnNullConnection() throws JmsClientException
    {
        ( (JmsSenderClient) mSender_ ).setConnection( null );
        mCtrl_.replay();

        mSender_.sendMessageToDestination( mMockMessage_ );

        mCtrl_.verify();

    }

    @Test
    public void testSendMessage() throws JmsClientException, JMSException
    {
        Session mockSession = mCtrl_.createMock( Session.class );
        MessageProducer mockProducer = mCtrl_.createMock( MessageProducer.class );
        Message mockJmsMessage = mCtrl_.createMock( Message.class );

        EasyMock.expect( mMockConnection_.createSession( true, Session.CLIENT_ACKNOWLEDGE ) )
            .andReturn( mockSession )
            .once();
        EasyMock.expect( mockSession.createProducer( mMockDestination_ ) )
            .andReturn( mockProducer )
            .once();
        mockSession.close();
        EasyMock.expectLastCall().once();
        mockSession.commit();
        EasyMock.expectLastCall().once();

        EasyMock.expect( mMockMessage_.buildJmsMessage( mockSession ) ).andReturn( mockJmsMessage );
        mockProducer.send( mockJmsMessage );
        EasyMock.expectLastCall().once();

        mockProducer.close();
        EasyMock.expectLastCall().once();

        mCtrl_.replay();

        mSender_.sendMessageToDestination( mMockMessage_ );

        mCtrl_.verify();
    }

    @Test(expected = JmsClientException.class)
    public void testSendButFailAtSessionCreate() throws JmsClientException, JMSException
    {
        EasyMock.expect( mMockConnection_.createSession( true, Session.CLIENT_ACKNOWLEDGE ) )
            .andThrow( new JMSException( "This is all part of the test" ) );

        mCtrl_.replay();

        mSender_.sendMessageToDestination( mMockMessage_ );

        mCtrl_.verify();
    }

    @Test(expected = JmsClientException.class)
    public void testSendButFailAtProducerCreate() throws JmsClientException, JMSException
    {
        Session mockSession = mCtrl_.createMock( Session.class );

        EasyMock.expect( mMockConnection_.createSession( true, Session.CLIENT_ACKNOWLEDGE ) )
            .andReturn( mockSession )
            .once();
        EasyMock.expect( mockSession.createProducer( mMockDestination_ ) )
            .andThrow( new JMSException( "This is all part of the test" ) );
        mockSession.close();
        EasyMock.expectLastCall().once();

        mCtrl_.replay();

        mSender_.sendMessageToDestination( mMockMessage_ );

        mCtrl_.verify();
    }

    @Test(expected = JmsClientException.class)
    public void testSendButFailAtMessageSendFollowedByExceptionsThroughCloses()
                    throws JmsClientException, JMSException
    {
        Session mockSession = mCtrl_.createMock( Session.class );
        MessageProducer mockProducer = mCtrl_.createMock( MessageProducer.class );
        Message mockJmsMessage = mCtrl_.createMock( Message.class );

        EasyMock.expect( mMockConnection_.createSession( true, Session.CLIENT_ACKNOWLEDGE ) )
            .andReturn( mockSession )
            .once();
        EasyMock.expect( mockSession.createProducer( mMockDestination_ ) )
            .andReturn( mockProducer )
            .once();

        EasyMock.expect( mMockMessage_.buildJmsMessage( mockSession ) )
            .andReturn( mockJmsMessage )
            .once();

        mockProducer.send( mockJmsMessage );
        EasyMock.expectLastCall().andThrow( new JMSException( "This is all part of the test" ) );

        mockProducer.close();
        EasyMock.expectLastCall().andThrow( new JMSException( "This is all part of the test" ) );

        mockSession.close();
        EasyMock.expectLastCall().andThrow( new JMSException( "This is all part of the test" ) );

        mCtrl_.replay();

        mSender_.sendMessageToDestination( mMockMessage_ );

        Assert.fail( "Test shoudl not have reached this part" );

        mCtrl_.verify();
    }
}
