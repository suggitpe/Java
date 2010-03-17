package com.ubs.orca.orcabridge.jmsclient.impl;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.naming.Context;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ubs.orca.orcabridge.jmsclient.JmsClientException;

import static org.easymock.EasyMock.createControl;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;

/**
 * Test suite to test the JMS client core.
 * 
 * @author suggitpe
 * @version 1.0 26 Oct 2009
 */
public class JmsClientCoreTestConnection
{

    private static final Log LOG = LogFactory.getLog( JmsClientCoreTestConnection.class );

    private IMocksControl ctrl;
    private JmsClientCore jmsClientCore;
    private Context mockInitialContext;
    private ConnectionFactory mockConnectionFactory;
    private Destination mockDestination;
    private Connection mockConnection;

    private static final String DEST_NAME = "TestDestination";
    private static final String CONNFACT_NAME = "TestConnectionFactory";

    /** */
    @BeforeClass
    public static void doBeforeClass()
    {
        LOG.debug( "=================== " + JmsDurableReaderActionTest.class.getSimpleName() );
    }

    /**
     * @throws Exception
     */
    @Before
    public void doBefore() throws Exception
    {
        LOG.debug( "-------------" );
        ctrl = createControl();
        mockInitialContext = ctrl.createMock( Context.class );
        mockConnectionFactory = ctrl.createMock( ConnectionFactory.class );
        mockDestination = ctrl.createMock( Destination.class );
        mockConnection = ctrl.createMock( Connection.class );

        jmsClientCore = new JmsClientCore();
        jmsClientCore.setInitialContext( mockInitialContext );
        jmsClientCore.setDestinationName( DEST_NAME );
        jmsClientCore.setConnectionFactoryName( CONNFACT_NAME );
        jmsClientCore.afterPropertiesSet();
    }

    /**
     * Test that we can connect to the JMS client when there are no
     * security credentials
     * 
     * @throws JmsClientException
     * @throws NamingException
     * @throws JMSException
     */
    @Test
    public void testConnectionWithNoCredentials() throws JmsClientException, NamingException,
                    JMSException
    {
        expect( mockInitialContext.lookup( CONNFACT_NAME ) ).andReturn( mockConnectionFactory )
            .once();
        expect( mockInitialContext.lookup( DEST_NAME ) ).andReturn( mockDestination ).once();

        expect( mockConnectionFactory.createConnection() ).andReturn( mockConnection ).once();

        ctrl.replay();

        jmsClientCore.connect();

        ctrl.verify();
    }

    /**
     * Test that we can connect to the JMS client when theer are
     * security credentials
     * 
     * @throws JmsClientException
     * @throws NamingException
     * @throws JMSException
     */
    @Test
    public void testConnectionWithCredentials() throws JmsClientException, NamingException,
                    JMSException
    {
        String user = "testUser";
        String pass = "testPassword";
        expect( mockInitialContext.lookup( CONNFACT_NAME ) ).andReturn( mockConnectionFactory )
            .once();
        expect( mockInitialContext.lookup( DEST_NAME ) ).andReturn( mockDestination ).once();

        expect( mockConnectionFactory.createConnection( user, pass ) ).andReturn( mockConnection )
            .once();

        ctrl.replay();

        jmsClientCore.connect( user, pass );

        ctrl.verify();
    }

    /**
     * Test that when there is a naming exception from the initial
     * context lookup, a local exception is propogated to the top of
     * the stack
     * 
     * @throws JmsClientException
     * @throws NamingException
     */
    @Test(expected = JmsClientException.class)
    public void testConnectionFailureFromJndiLookup() throws JmsClientException, NamingException
    {
        expect( mockInitialContext.lookup( CONNFACT_NAME ) ).andThrow( new NamingException( "This is all part of the test" ) )
            .once();
        ctrl.replay();

        jmsClientCore.connect();

        ctrl.verify();
    }

    /**
     * @throws JmsClientException
     * @throws NamingException
     * @throws JMSException
     */
    @Test(expected = JmsClientException.class)
    public void testConnectionFailureFromConnectionFactory() throws JmsClientException,
                    NamingException, JMSException
    {
        expect( mockInitialContext.lookup( CONNFACT_NAME ) ).andReturn( mockConnectionFactory )
            .once();
        expect( mockInitialContext.lookup( DEST_NAME ) ).andReturn( mockDestination ).once();
        expect( mockConnectionFactory.createConnection() ).andThrow( new JMSException( "This is all part of the test" ) );

        ctrl.replay();

        jmsClientCore.connect();

        ctrl.verify();
    }

    /**
     * Tests that if a connection already exists, that the existing
     * connection is closed correctly before we create a new
     * connection.
     * 
     * @throws JmsClientException
     * @throws NamingException
     * @throws JMSException
     */
    @Test
    public void testConnectionWhenOneAlreadyExists() throws JmsClientException, NamingException,
                    JMSException
    {
        jmsClientCore.setConnection( mockConnection );

        expect( mockInitialContext.lookup( CONNFACT_NAME ) ).andReturn( mockConnectionFactory )
            .once();
        expect( mockInitialContext.lookup( DEST_NAME ) ).andReturn( mockDestination ).once();
        mockConnection.stop();
        expectLastCall().once();
        mockConnection.close();
        expectLastCall().once();

        expect( mockConnectionFactory.createConnection() ).andReturn( mockConnection ).once();

        ctrl.replay();

        jmsClientCore.connect();

        ctrl.verify();
    }

    /**
     * Test that we can disconnect from the JMS client in a normal
     * fashion.
     * 
     * @throws JmsClientException
     * @throws JMSException
     */
    @Test
    public void testNormalDisconnect() throws JmsClientException, JMSException
    {
        jmsClientCore.setConnection( mockConnection );

        mockConnection.stop();
        expectLastCall().once();
        mockConnection.close();
        expectLastCall().once();

        ctrl.replay();

        jmsClientCore.disconnect();

        ctrl.verify();
    }

    /**
     * Test that if we call disconnect and we get an exception from
     * the stop, that the close is still called on the connection.
     * 
     * @throws JmsClientException
     * @throws JMSException
     */
    @Test(expected = JmsClientException.class)
    public void testDisconnectWithFailOnStop() throws JmsClientException, JMSException
    {
        jmsClientCore.setConnection( mockConnection );

        mockConnection.stop();
        expectLastCall().andThrow( new JMSException( "Fail from stop: this is all part of the test" ) );
        mockConnection.close();
        expectLastCall().once();

        ctrl.replay();

        jmsClientCore.disconnect();

        ctrl.verify();
    }

    /**
     * Test that if we call disconnect and we get an issue in the JMS
     * close, that we correctly report this.
     * 
     * @throws JmsClientException
     * @throws JMSException
     */
    @Test
    public void testDisconnectWithFailOnClose() throws JmsClientException, JMSException
    {
        jmsClientCore.setConnection( mockConnection );

        mockConnection.stop();
        expectLastCall().once();
        mockConnection.close();
        expectLastCall().andThrow( new JMSException( "Fail from close: this is all part of the test" ) );

        ctrl.replay();

        jmsClientCore.disconnect();

        ctrl.verify();
    }

    /**
     * Test that if we call disconnect
     * 
     * @throws JmsClientException
     * @throws JMSException
     */
    @Test(expected = JmsClientException.class)
    public void testDisconnectWithFailOnStopAndClose() throws JmsClientException, JMSException
    {
        jmsClientCore.setConnection( mockConnection );

        mockConnection.stop();
        expectLastCall().andThrow( new JMSException( "fail on stop: this is all part of the test" ) );
        ;
        mockConnection.close();
        expectLastCall().andThrow( new JMSException( "Fail from close: this is all part of the test" ) );

        ctrl.replay();

        jmsClientCore.disconnect();

        ctrl.verify();

    }

}
