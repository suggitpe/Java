/*
 * JmsReaderTest.java created on 14 Oct 2009 07:05:28 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.jmsclient.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ubs.orca.orcabridge.jmsclient.IJmsAction;
import com.ubs.orca.orcabridge.jmsclient.IJmsClientSingleMsgCallback;
import com.ubs.orca.orcabridge.jmsclient.JmsClientException;

import static org.easymock.EasyMock.createControl;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;

/**
 * Test suite for the JMS durable reader action.
 * 
 * @author suggitpe
 * @version 1.0 14 Oct 2009
 */
public class JmsDurableReaderActionTest
{

    private static final Log LOG = LogFactory.getLog( JmsDurableReaderActionTest.class );

    private IMocksControl ctrl_;
    private IJmsAction jmsReceiverAction_;
    private IJmsClientSingleMsgCallback mockCallback_;
    private Session mockSession_;
    private Topic mockDestination_;
    private TopicSubscriber mockSubscriber_;
    private Message mockMessage_;

    private static final String DURABLE_NAME = "## Durable Name ##";
    private static final String MSG_SELECTOR = "## Message selector ##";

    /** */
    @BeforeClass
    public static void doBeforeClass()
    {
        LOG.debug( "=================== " + JmsDurableReaderActionTest.class.getSimpleName() );
    }

    /** */
    @Before
    public void doBefore()
    {
        LOG.debug( "-------------" );
        ctrl_ = createControl();
        mockCallback_ = ctrl_.createMock( IJmsClientSingleMsgCallback.class );
        mockSession_ = ctrl_.createMock( Session.class );
        mockDestination_ = ctrl_.createMock( Topic.class );
        mockSubscriber_ = ctrl_.createMock( TopicSubscriber.class );
        mockMessage_ = ctrl_.createMock( Message.class );

        jmsReceiverAction_ = new JmsDurableReaderAction( mockCallback_, DURABLE_NAME, MSG_SELECTOR );
    }

    /**
     * Tests the normal flow of received messages into the durable
     * reader and processes until it needs to stop.
     * 
     * @throws JmsClientException
     * @throws JMSException
     */
    @Test
    public void testNormalReceiveProcess() throws JmsClientException, JMSException
    {
        // dropping a strict control in here so that we can do a loop
        // and come out again
        expect( mockSession_.createDurableSubscriber( mockDestination_,
                                                      DURABLE_NAME,
                                                      MSG_SELECTOR,
                                                      true ) ).andReturn( mockSubscriber_ );

        expect( mockSubscriber_.receiveNoWait() ).andReturn( mockMessage_ ).once();

        mockCallback_.onReceived( mockMessage_ );
        expectLastCall().once();

        mockSession_.commit();
        expectLastCall().once();

        expect( mockSubscriber_.receiveNoWait() ).andReturn( null ).once();

        mockSubscriber_.close();
        expectLastCall();

        ctrl_.replay();

        jmsReceiverAction_.actionInTransaction( mockSession_, mockDestination_ );

        ctrl_.verify();
    }

    /**
     * Tests that if you try and create a durable on a none Topic
     * object then it will create the correct type of exception
     * 
     * @throws JmsClientException
     */
    @Test(expected = JmsClientException.class)
    public void testRecieveAgainstNonTopicDestination() throws JmsClientException
    {
        Queue mockQueue = ctrl_.createMock( Queue.class );

        ctrl_.replay();
        jmsReceiverAction_.actionInTransaction( mockSession_, mockQueue );
        ctrl_.verify();
    }

    /**
     * Tests that if we fail to create the durable subscriber, that
     * the correct exception type is propagated to the top of the
     * stack.
     * 
     * @throws JmsClientException
     * @throws JMSException
     */
    @Test(expected = JmsClientException.class)
    public void testReceiveButFailCreateSubscription() throws JmsClientException, JMSException
    {
        expect( mockSession_.createDurableSubscriber( mockDestination_,
                                                      DURABLE_NAME,
                                                      MSG_SELECTOR,
                                                      true ) ).andThrow( new JMSException( "Failed to create subscriber: this is all a part of the test" ) );

        ctrl_.replay();
        jmsReceiverAction_.actionInTransaction( mockSession_, mockDestination_ );
        ctrl_.verify();
    }

    /**
     * Tests that if there is a failure in the closing of the
     * subscriber that nothing is impacted in terms of flow.
     * Expectation in this case is that the close is not the big issue
     * and that when we tray and reconnect, then will see the other
     * issues.
     * 
     * @throws JmsClientException
     * @throws JMSException
     */
    @Test
    public void testReceiveButFailOnSubscriberClose() throws JmsClientException, JMSException
    {
        expect( mockSession_.createDurableSubscriber( mockDestination_,
                                                      DURABLE_NAME,
                                                      MSG_SELECTOR,
                                                      true ) ).andReturn( mockSubscriber_ );

        expect( mockSubscriber_.receiveNoWait() ).andReturn( mockMessage_ ).once();

        mockCallback_.onReceived( mockMessage_ );
        expectLastCall().once();

        mockSession_.commit();
        expectLastCall().once();

        expect( mockSubscriber_.receiveNoWait() ).andReturn( null ).once();

        mockSubscriber_.close();
        expectLastCall().andThrow( new JMSException( "Failed to close subscriber: this is all part of the test" ) );

        ctrl_.replay();

        jmsReceiverAction_.actionInTransaction( mockSession_, mockDestination_ );

        ctrl_.verify();
    }

    /**
     * Test that when we get an exception through the receive method,
     * that it is correctly propagated up the stack
     * 
     * @throws JmsClientException
     * @throws JMSException
     */
    @Test(expected = JmsClientException.class)
    public void testReceiveButFailOnReceive() throws JmsClientException, JMSException
    {
        expect( mockSession_.createDurableSubscriber( mockDestination_,
                                                      DURABLE_NAME,
                                                      MSG_SELECTOR,
                                                      true ) ).andReturn( mockSubscriber_ );

        expect( mockSubscriber_.receiveNoWait() ).andThrow( new JMSException( "Fail on receive: this is all part of the test" ) );

        mockSubscriber_.close();
        expectLastCall().once();

        ctrl_.replay();

        jmsReceiverAction_.actionInTransaction( mockSession_, mockDestination_ );

        ctrl_.verify();
    }

}
