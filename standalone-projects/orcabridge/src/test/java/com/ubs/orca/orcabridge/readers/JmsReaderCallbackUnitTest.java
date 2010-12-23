/*
 * JmsReaderCallbackUnitTest.java created on 9 Nov 2009 08:26:16 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.readers;

import javax.jms.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ubs.orca.orcabridge.IMessageFacade;
import com.ubs.orca.orcabridge.IMessageProcessor;
import com.ubs.orca.orcabridge.OrcaBridgeException;
import com.ubs.orca.orcabridge.jmsclient.JmsClientException;

import static org.easymock.EasyMock.createControl;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.isA;
import static org.junit.Assert.fail;

/**
 * Test suite for the JMS Reader callback.
 * 
 * @author suggitpe
 * @version 1.0 9 Nov 2009
 */
public class JmsReaderCallbackUnitTest {

    private static final Logger LOG = LoggerFactory.getLogger( JmsReaderCallbackUnitTest.class );

    private IMocksControl ctrl;
    private JmsSingleMessageReaderCallback jmsCallback;
    private IMessageProcessor mockMessageProcessor;

    /** */
    @BeforeClass
    public static void doBeforeClass() {
        LOG.debug( "=================== " + JmsReaderCallbackUnitTest.class.getSimpleName() );
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
        mockMessageProcessor = ctrl.createMock( IMessageProcessor.class );

        jmsCallback = new JmsSingleMessageReaderCallback( mockMessageProcessor );
    }

    /**
     * Tests that the message callback is called and that it then delegates to the message processor.
     * 
     * @throws JmsClientException
     * @throws OrcaBridgeException
     */
    @Test
    public void testJmsCallback() throws JmsClientException, OrcaBridgeException {
        mockMessageProcessor.processMessage( isA( IMessageFacade.class ) );
        expectLastCall().once();

        Message msg = ctrl.createMock( Message.class );

        ctrl.replay();
        jmsCallback.onReceived( msg );
        ctrl.verify();
    }

    /**
     * @throws JmsClientException
     * @throws OrcaBridgeException
     */
    @Test(expected = JmsClientException.class)
    public void testJmsCallbackWithProcessFailure() throws JmsClientException, OrcaBridgeException {
        mockMessageProcessor.processMessage( isA( IMessageFacade.class ) );
        expectLastCall().andThrow( new OrcaBridgeException( "ProcessMessage failed: This is all part of the test" ) );

        Message msg = ctrl.createMock( Message.class );

        ctrl.replay();

        jmsCallback.onReceived( msg );
        fail( "Test test should not have reached this far" );

        ctrl.verify();
    }
}
