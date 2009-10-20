/*
 * JmsConnectionTests.java created on 14 Oct 2009 07:04:58 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.jmsclient.impl;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.naming.Context;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ubs.orca.orcabridge.jmsclient.IJmsClient;
import com.ubs.orca.orcabridge.jmsclient.JmsClientException;

/**
 * Tests suite for the connectivity part of the JMS client
 * 
 * @author suggitpe
 * @version 1.0 14 Oct 2009
 */
public class JmsConnectionTest
{

    private static final Log LOG = LogFactory.getLog( JmsConnectionTest.class );

    private Context mMockContext_;
    private IMocksControl mCtrl_;
    private IJmsClient mJmsClient_;
    private ConnectionFactory mMockConnectionFactory_;
    private Destination mMockDestination_;
    private static final String CONNECTION_FACTORY_NAME = "test.connfact.name";
    private static final String DESTINATION_NAME = "test.destination.name";

    @BeforeClass
    public static void doBeforeClass()
    {
        LOG.debug( "=================== " + JmsConnectionTest.class.getSimpleName() );
    }

    @Before
    public void doBefore()
    {
        LOG.debug( "-------------" );
        mCtrl_ = EasyMock.createControl();
        mMockContext_ = mCtrl_.createMock( Context.class );
        mMockConnectionFactory_ = mCtrl_.createMock( ConnectionFactory.class );
        mMockDestination_ = mCtrl_.createMock( Destination.class );
        mJmsClient_ = new JmsClientBase( mMockContext_, CONNECTION_FACTORY_NAME, DESTINATION_NAME );
    }

    /**
     * Tests that in the normal case, a connection is correctly made.
     */
    @Test
    public void testConnection() throws JmsClientException, NamingException, JMSException
    {
        Connection mockConn = mCtrl_.createMock( Connection.class );

        EasyMock.expect( mMockContext_.lookup( CONNECTION_FACTORY_NAME ) )
            .andReturn( mMockConnectionFactory_ )
            .once();
        EasyMock.expect( mMockContext_.lookup( EasyMock.matches( DESTINATION_NAME ) ) )
            .andReturn( mMockDestination_ )
            .once();

        EasyMock.expect( mMockConnectionFactory_.createConnection() ).andStubReturn( mockConn );

        mCtrl_.replay();

        mJmsClient_.connect();

        mCtrl_.verify();
    }

    /**
     * Tests that when we fail to look a JMS object up from th
     * context, that the correct type of exception is thrown.
     * 
     * @throws JmsClientException
     * @throws NamingException
     * @throws JMSException
     */
    @Test(expected = JmsClientException.class)
    public void testBadConnectionFromContextLookup() throws JmsClientException, NamingException,
                    JMSException
    {

        EasyMock.expect( mMockContext_.lookup( CONNECTION_FACTORY_NAME ) )
            .andReturn( mMockConnectionFactory_ )
            .once();
        EasyMock.expect( mMockContext_.lookup( EasyMock.matches( DESTINATION_NAME ) ) )
            .andThrow( new NamingException( "This is all part of the test" ) );

        mCtrl_.replay();

        mJmsClient_.connect( "username", "password" );
        Assert.fail( "Test should not reach here ... exception should have ben thrown" );

        mCtrl_.verify();
    }

    /**
     * Tests that when we try to instantiate the JMS connection and we
     * get an exception, that the system correctly handles itself.
     * 
     * @throws JmsClientException
     * @throws NamingException
     * @throws JMSException
     */
    @Test(expected = JmsClientException.class)
    public void testBadConnectionFromConnectionFail() throws JmsClientException, NamingException,
                    JMSException
    {
        EasyMock.expect( mMockContext_.lookup( CONNECTION_FACTORY_NAME ) )
            .andReturn( mMockConnectionFactory_ )
            .once();
        EasyMock.expect( mMockContext_.lookup( EasyMock.matches( DESTINATION_NAME ) ) )
            .andReturn( mMockDestination_ )
            .once();

        EasyMock.expect( mMockConnectionFactory_.createConnection( "foo", "bar" ) )
            .andThrow( new JMSException( "This is all part of the test" ) );

        mCtrl_.replay();

        mJmsClient_.connect( "foo", "bar" );

        Assert.fail( "You shoudl not reach here as an exception should have been thrown" );

        mCtrl_.verify();

    }

    @Test
    public void testDisconnection() throws JmsClientException, JMSException
    {
        Connection mockConn = mCtrl_.createMock( Connection.class );
        ( (JmsClientBase) mJmsClient_ ).setConnection( mockConn );

        mockConn.stop();
        EasyMock.expectLastCall().once();

        mockConn.close();
        EasyMock.expectLastCall().once();

        mCtrl_.replay();

        mJmsClient_.disconnect();

        mCtrl_.verify();
    }

    @Test(expected = JmsClientException.class)
    public void testDisconnectionWithExceptionFromStop() throws JmsClientException, JMSException
    {
        Connection mockConn = mCtrl_.createMock( Connection.class );
        ( (JmsClientBase) mJmsClient_ ).setConnection( mockConn );

        mockConn.stop();
        EasyMock.expectLastCall().andThrow( new JMSException( "This is all part of the test" ) );

        mockConn.close();
        EasyMock.expectLastCall().once();

        mCtrl_.replay();

        mJmsClient_.disconnect();

        mCtrl_.verify();
    }

    @Test
    public void testDisconnectionWithExceptionFromClose() throws JmsClientException, JMSException
    {
        Connection mockConn = mCtrl_.createMock( Connection.class );
        ( (JmsClientBase) mJmsClient_ ).setConnection( mockConn );

        mockConn.stop();
        EasyMock.expectLastCall().once();

        mockConn.close();
        EasyMock.expectLastCall().andThrow( new JMSException( "This is all part of the test" ) );

        mCtrl_.replay();

        mJmsClient_.disconnect();

        mCtrl_.verify();
    }

    @Test(expected = JmsClientException.class)
    public void testDisconnectionWithExceptionsFromStopAndClose() throws JmsClientException,
                    JMSException
    {
        Connection mockConn = mCtrl_.createMock( Connection.class );
        ( (JmsClientBase) mJmsClient_ ).setConnection( mockConn );

        mockConn.stop();
        EasyMock.expectLastCall().andThrow( new JMSException( "This is all part of the test" ) );

        mockConn.close();
        EasyMock.expectLastCall()
            .andThrow( new JMSException( "Again, this is a part of the test" ) );

        mCtrl_.replay();

        mJmsClient_.disconnect();

        mCtrl_.verify();
    }

}
