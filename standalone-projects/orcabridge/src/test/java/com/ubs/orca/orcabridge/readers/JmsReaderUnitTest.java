/*
 * JmsReaderTest.java created on 24 Sep 2009 06:54:48 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.readers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ubs.orca.orcabridge.OrcaBridgeException;
import com.ubs.orca.orcabridge.jmsclient.IJmsAction;
import com.ubs.orca.orcabridge.jmsclient.IJmsClient;
import com.ubs.orca.orcabridge.jmsclient.IJmsClientSingleMsgCallback;
import com.ubs.orca.orcabridge.jmsclient.JmsClientException;

import static org.easymock.EasyMock.createControl;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.isA;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Test suite for the JMS reader.
 * 
 * @author suggitpe
 * @version 1.0 24 Sep 2009
 */
public class JmsReaderUnitTest {

    private static final Log LOG = LogFactory.getLog( JmsReaderUnitTest.class );

    private static final String DURABLE_NAME = "TestDurable";
    private static final String MESSAGE_SELECTOR = "test selector";

    private JmsSingleMessageReader jmsReader;
    private IMocksControl ctrl;
    private IJmsClient mockJmsClient;
    private IJmsClientSingleMsgCallback mockJmsCallback;

    /** */
    @BeforeClass
    public static void doBeforeClass() {
        LOG.debug( "=================== " + JmsReaderUnitTest.class.getSimpleName() );
    }

    /**
     * Test setup
     * 
     * @throws Exception
     */
    @Before
    public void doBefore() throws Exception {
        LOG.debug( "-----------------" );
        ctrl = createControl();
        mockJmsClient = ctrl.createMock( IJmsClient.class );
        mockJmsCallback = ctrl.createMock( IJmsClientSingleMsgCallback.class );

        jmsReader = new JmsSingleMessageReader( mockJmsClient,
                                                mockJmsCallback,
                                                DURABLE_NAME,
                                                MESSAGE_SELECTOR );
    }

    /**
     * Tests the normal execution of starting the reader
     * 
     * @throws OrcaBridgeException
     * @throws JmsClientException
     */
    @Test
    public void testStartReader() throws OrcaBridgeException, JmsClientException {
        mockJmsClient.connect();
        expectLastCall().once();
        mockJmsClient.processAction( isA( IJmsAction.class ) );
        expectLastCall().once();

        ctrl.replay();

        assertThat( jmsReader.getState(), equalTo( AbstractMessageReader.STATE_UNINITIALISED ) );
        jmsReader.startReader();
        assertThat( jmsReader.getState(), equalTo( AbstractMessageReader.STATE_RUNNING ) );

        ctrl.verify();
    }

    /**
     * Tests that the reader behaves correctly when faced with an exception when connecting to the JMS broker
     * 
     * @throws OrcaBridgeException
     * @throws JmsClientException
     */
    @Test(expected = OrcaBridgeException.class)
    public void testStartReaderAndFailOnConnect() throws OrcaBridgeException, JmsClientException {
        mockJmsClient.connect();
        expectLastCall().andThrow( new JmsClientException( "Connect failed: This is all part of the test" ) );

        ctrl.replay();
        jmsReader.startReader();
        fail( "test should not have reached here" );
        ctrl.verify();
    }

    /**
     * Tests that the reader behaves correctly when faced with an exception with processing the action
     * 
     * @throws OrcaBridgeException
     * @throws JmsClientException
     */
    @Test(expected = OrcaBridgeException.class)
    public void testStartReaderAndFailOnProcess() throws OrcaBridgeException, JmsClientException {
        mockJmsClient.connect();
        expectLastCall().once();
        mockJmsClient.processAction( isA( IJmsAction.class ) );
        expectLastCall().andThrow( new JmsClientException( "ProcessInTransaction failed: This is all part of the test" ) );

        ctrl.replay();
        jmsReader.startReader();
        fail( "Test should not have reached here" );
        ctrl.verify();
    }

    /**
     * Tests the normal execution of stopping the reader
     * 
     * @throws OrcaBridgeException
     * @throws JmsClientException
     */
    @Test
    public void testStopReader() throws OrcaBridgeException, JmsClientException {
        mockJmsClient.disconnect();
        expectLastCall().once();

        ctrl.replay();
        jmsReader.stopReader();
        ctrl.verify();
    }

    /**
     * Tests the normal execution of stopping the reader
     * 
     * @throws OrcaBridgeException
     * @throws JmsClientException
     */
    @Test(expected = OrcaBridgeException.class)
    public void testStopReaderAndFailOnDisconnect() throws OrcaBridgeException, JmsClientException {
        mockJmsClient.disconnect();
        expectLastCall().andThrow( new JmsClientException( "Disconnect failed: This is all part of the test" ) );

        ctrl.replay();
        jmsReader.stopReader();

        fail( "Test should not have reached here" );
        ctrl.verify();
    }

}
