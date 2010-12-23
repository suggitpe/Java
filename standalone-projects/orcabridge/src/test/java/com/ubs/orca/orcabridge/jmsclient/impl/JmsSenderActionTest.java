/*
 * JmsSenderActionTest.java created on 14 Oct 2009 07:05:15 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.jmsclient.impl;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ubs.orca.orcabridge.IMessageFacade;
import com.ubs.orca.orcabridge.OrcaBridgeMessageConversionException;
import com.ubs.orca.orcabridge.jmsclient.IJmsAction;
import com.ubs.orca.orcabridge.jmsclient.JmsClientException;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;

/**
 * Test suite for the JMS sender client.
 * 
 * @author suggitpe
 * @version 1.0 14 Oct 2009
 */
public class JmsSenderActionTest {

    private static final Logger LOG = LoggerFactory.getLogger( JmsSenderActionTest.class );

    private IMocksControl ctrl;
    private IJmsAction jmsSenderAction;
    private IMessageFacade mockMessageFacade;
    private Destination mockDestination;
    private Session mockSession;
    private MessageProducer mockMessageProducer;
    private Message mockJmsMessage;

    /** */
    @BeforeClass
    public static void doBeforeClass() {
        LOG.debug( "=================== " + JmsSenderActionTest.class.getSimpleName() );
    }

    /** */
    @Before
    public void doBefore() {
        LOG.debug( "-------------" );
        ctrl = EasyMock.createControl();
        mockMessageFacade = ctrl.createMock( IMessageFacade.class );
        mockDestination = ctrl.createMock( Destination.class );
        mockSession = ctrl.createMock( Session.class );
        mockJmsMessage = ctrl.createMock( Message.class );
        mockMessageProducer = ctrl.createMock( MessageProducer.class );

        jmsSenderAction = new JmsSenderAction( mockMessageFacade );
    }

    /**
     * Test that for the normal case, a message is sent correctly and no exceptions are raised
     * 
     * @throws JmsClientException
     * @throws JMSException
     * @throws OrcaBridgeMessageConversionException
     */
    @Test
    public void testNormalSend() throws JmsClientException, JMSException,
                    OrcaBridgeMessageConversionException {
        expect( mockSession.createProducer( mockDestination ) ).andReturn( mockMessageProducer ).once();
        expect( mockMessageFacade.buildJmsMessage( mockSession ) ).andReturn( mockJmsMessage ).once();

        mockMessageProducer.send( mockJmsMessage );
        expectLastCall().once();

        mockMessageProducer.close();
        expectLastCall().once();

        mockSession.commit();
        expectLastCall().once();

        ctrl.replay();
        jmsSenderAction.actionInTransaction( mockSession, mockDestination );
        ctrl.verify();
    }

    /**
     * Tests that when we fail to create a message producer we get an exception from the top of the stack
     * 
     * @throws JmsClientException
     * @throws JMSException
     */
    @Test(expected = JmsClientException.class)
    public void testSendWithFailInProducerCreate() throws JmsClientException, JMSException {
        expect( mockSession.createProducer( mockDestination ) ).andThrow( new JMSException( "Producer create fail: this is all part of the test" ) );

        ctrl.replay();
        jmsSenderAction.actionInTransaction( mockSession, mockDestination );
        ctrl.verify();
    }

    /**
     * Tests that when we have a message conversion issue that we correctly propogate the right type of
     * exception up the stack
     * 
     * @throws JmsClientException
     * @throws JMSException
     * @throws OrcaBridgeMessageConversionException
     */
    @Test(expected = JmsClientException.class)
    public void testSendWithFailInJmsMessageBuild() throws JmsClientException, JMSException,
                    OrcaBridgeMessageConversionException {
        expect( mockSession.createProducer( mockDestination ) ).andReturn( mockMessageProducer ).once();
        expect( mockMessageFacade.buildJmsMessage( mockSession ) ).andThrow( new OrcaBridgeMessageConversionException( "JMS Message build fail: this is all part of the test" ) );

        mockMessageProducer.close();
        expectLastCall().once();

        ctrl.replay();
        jmsSenderAction.actionInTransaction( mockSession, mockDestination );
        ctrl.verify();

    }

    /**
     * Tests that when there is a failure in the send process, that the correct exception type is passed up
     * the stack.
     * 
     * @throws JmsClientException
     * @throws JMSException
     * @throws OrcaBridgeMessageConversionException
     */
    @Test(expected = JmsClientException.class)
    public void testSendWithFailInSend() throws JmsClientException, JMSException,
                    OrcaBridgeMessageConversionException {
        expect( mockSession.createProducer( mockDestination ) ).andReturn( mockMessageProducer ).once();
        expect( mockMessageFacade.buildJmsMessage( mockSession ) ).andReturn( mockJmsMessage ).once();

        mockMessageProducer.send( mockJmsMessage );
        expectLastCall().andThrow( new JMSException( "Fail in JMS send: this is all part of the test" ) );

        mockMessageProducer.close();
        expectLastCall().once();

        ctrl.replay();
        jmsSenderAction.actionInTransaction( mockSession, mockDestination );
        ctrl.verify();
    }

    /**
     * Tests that if there is a failure in the closing of the producer then no exceptions are thrown.
     * 
     * @throws JmsClientException
     * @throws JMSException
     * @throws OrcaBridgeMessageConversionException
     */
    @Test
    public void testSendWithFailInProducerClose() throws JmsClientException, JMSException,
                    OrcaBridgeMessageConversionException {

        expect( mockSession.createProducer( mockDestination ) ).andReturn( mockMessageProducer ).once();
        expect( mockMessageFacade.buildJmsMessage( mockSession ) ).andReturn( mockJmsMessage ).once();

        mockMessageProducer.send( mockJmsMessage );
        expectLastCall();

        mockMessageProducer.close();
        expectLastCall().andThrow( new JMSException( "Producer Close fail: this is all part of the:w"
                                                     + " test" ) );

        mockSession.commit();
        expectLastCall().once();

        ctrl.replay();
        jmsSenderAction.actionInTransaction( mockSession, mockDestination );
        ctrl.verify();
    }

    /**
     * Tests that if we get an exception during the commit phase, that the correct exception is raise up the
     * stack.
     * 
     * @throws JmsClientException
     * @throws JMSException
     * @throws OrcaBridgeMessageConversionException
     */
    @Test(expected = JmsClientException.class)
    public void testSendWithFailInCommit() throws JmsClientException, JMSException,
                    OrcaBridgeMessageConversionException {

        expect( mockSession.createProducer( mockDestination ) ).andReturn( mockMessageProducer ).once();
        expect( mockMessageFacade.buildJmsMessage( mockSession ) ).andReturn( mockJmsMessage ).once();

        mockMessageProducer.send( mockJmsMessage );
        expectLastCall();

        mockMessageProducer.close();
        expectLastCall().once();

        mockSession.commit();
        expectLastCall().andThrow( new JMSException( "" ) );

        ctrl.replay();
        jmsSenderAction.actionInTransaction( mockSession, mockDestination );
        ctrl.verify();
    }

}
