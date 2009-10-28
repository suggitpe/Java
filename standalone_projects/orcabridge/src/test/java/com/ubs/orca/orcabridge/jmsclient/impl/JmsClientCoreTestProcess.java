/*
 * JmsClientCoreTestProcess.java created on 27 Oct 2009 12:26:41 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.jmsclient.impl;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.naming.Context;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ubs.orca.orcabridge.jmsclient.IJmsAction;
import com.ubs.orca.orcabridge.jmsclient.JmsClientException;

import static org.easymock.EasyMock.createControl;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;

/**
 * Test class to test the processInTransaction functionality in the
 * JmsClientCore
 * 
 * @author suggitpe
 * @version 1.0 27 Oct 2009
 */
public class JmsClientCoreTestProcess
{

    private static final Log LOG = LogFactory.getLog( JmsClientCoreTestProcess.class );

    private IMocksControl ctrl_;
    private JmsClientCore jmsClientCore_;
    private Context mockInitialContext_;
    private Connection mockConnection_;
    private Session mockSession_;
    private IJmsAction mockAction_;
    private Destination mockDestination_;

    private static final String DEST_NAME = "TestDestination";
    private static final String CONNFACT_NAME = "TestConnectionFactory";

    /** */
    @BeforeClass
    public static void doBeforeClass()
    {
        LOG.debug( "=================== " + JmsClientCoreTestProcess.class.getSimpleName() );
    }

    /**
     * @throws Exception
     */
    @Before
    public void doBefore() throws Exception
    {
        LOG.debug( "-------------" );
        ctrl_ = createControl();
        mockInitialContext_ = ctrl_.createMock( Context.class );
        mockConnection_ = ctrl_.createMock( Connection.class );
        mockSession_ = ctrl_.createMock( Session.class );
        mockAction_ = ctrl_.createMock( IJmsAction.class );
        mockDestination_ = ctrl_.createMock( Destination.class );

        jmsClientCore_ = new JmsClientCore();
        jmsClientCore_.setInitialContext( mockInitialContext_ );
        jmsClientCore_.setDestinationName( DEST_NAME );
        jmsClientCore_.setConnectionFactoryName( CONNFACT_NAME );
        jmsClientCore_.afterPropertiesSet();
    }

    /**
     * Test that for the normal flow we can perform the process in
     * transaction actions correctly.
     * 
     * @throws JmsClientException
     * @throws JMSException
     */
    @Test
    public void testProcessInTransaction() throws JmsClientException, JMSException
    {
        jmsClientCore_.setConnection( mockConnection_ );
        jmsClientCore_.setDestination( mockDestination_ );

        expect( mockConnection_.createSession( true, Session.CLIENT_ACKNOWLEDGE ) ).andReturn( mockSession_ )
            .once();
        mockConnection_.start();
        expectLastCall().once();

        mockAction_.action( mockSession_, mockDestination_ );
        expectLastCall().once();

        mockSession_.close();
        expectLastCall().once();

        ctrl_.replay();
        jmsClientCore_.processInTransaction( mockAction_ );
        ctrl_.verify();
    }

    /**
     * Tests that if we get an exception raised from the execution of
     * the action, that we propogate the correct type of exception up
     * the stack.
     * 
     * @throws JmsClientException
     * @throws JMSException
     */
    @Test(expected = JmsClientException.class)
    public void testProcessInTransactionWithFailInAction() throws JmsClientException, JMSException
    {
        jmsClientCore_.setConnection( mockConnection_ );
        jmsClientCore_.setDestination( mockDestination_ );

        expect( mockConnection_.createSession( true, Session.CLIENT_ACKNOWLEDGE ) ).andReturn( mockSession_ )
            .once();
        mockConnection_.start();
        expectLastCall().once();

        mockAction_.action( mockSession_, mockDestination_ );
        expectLastCall().andThrow( new JmsClientException( "Action fail: this is all part of the test" ) );

        mockSession_.close();
        expectLastCall().once();

        ctrl_.replay();
        jmsClientCore_.processInTransaction( mockAction_ );
        ctrl_.verify();
    }

    /**
     * Tests that if you try and process anything outside of an active
     * connection, that an adequate exception is thrown.
     * 
     * @throws JmsClientException
     */
    @Test(expected = JmsClientException.class)
    public void testProcessInTransactionWithNoActiveConnection() throws JmsClientException
    {

        ctrl_.replay();
        jmsClientCore_.processInTransaction( mockAction_ );
        ctrl_.verify();
    }

    /**
     * Tests that if an exception is thrown from the session create,
     * that we will ensure that the correct exception type is
     * propagated up the stack.
     * 
     * @throws JmsClientException
     * @throws JMSException
     */
    @Test(expected = JmsClientException.class)
    public void testProcessInTransactionWithFailedSessionCreate() throws JmsClientException,
                    JMSException
    {
        jmsClientCore_.setConnection( mockConnection_ );
        jmsClientCore_.setDestination( mockDestination_ );

        expect( mockConnection_.createSession( true, Session.CLIENT_ACKNOWLEDGE ) ).andThrow( new JMSException( "Session create fail: this is all part of the test" ) );

        ctrl_.replay();
        jmsClientCore_.processInTransaction( mockAction_ );
        ctrl_.verify();
    }

    /**
     * Tests that when we are processing in transaction and an error
     * occurs in the session close, that we do not throw an exception
     * but we do log it.
     * 
     * @throws JmsClientException
     * @throws JMSException
     */
    @Test
    public void testProcessInTransactionWithFailedSessionClose() throws JmsClientException,
                    JMSException
    {
        expect( mockConnection_.createSession( true, Session.CLIENT_ACKNOWLEDGE ) ).andReturn( mockSession_ )
            .once();
        mockConnection_.start();
        expectLastCall().once();

        mockAction_.action( mockSession_, mockDestination_ );
        expectLastCall().once();

        mockSession_.close();
        expectLastCall().andThrow( new JMSException( "Session close fail: this is all part of the test" ) );

        jmsClientCore_.setConnection( mockConnection_ );
        jmsClientCore_.setDestination( mockDestination_ );

        ctrl_.replay();
        jmsClientCore_.processInTransaction( mockAction_ );
        ctrl_.verify();
    }

}
