/*
 * JmsMessageSenderTest.java created on 4 Nov 2009 06:52:21 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.processors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymock.IMocksControl;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ubs.orca.orcabridge.IMessageFacade;
import com.ubs.orca.orcabridge.OrcaBridgeException;
import com.ubs.orca.orcabridge.jmsclient.IJmsAction;
import com.ubs.orca.orcabridge.jmsclient.IJmsClient;
import com.ubs.orca.orcabridge.jmsclient.JmsClientException;

import static org.easymock.EasyMock.createControl;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.isA;

/**
 * Test suite for the JMS message sender processor.
 * 
 * @author suggitpe
 * @version 1.0 4 Nov 2009
 */
public class JmsMessageSenderTest {

    private static final Log LOG = LogFactory.getLog( JmsMessageSenderTest.class );

    private IMocksControl ctrl;
    private JmsMessageSender sender;

    private IJmsClient mockJmsClient;
    private IMessageFacade mockMessageFacade;

    /** */
    @BeforeClass
    public static void doBeforeClass() {
        LOG.debug( "=================== " + JmsMessageSenderTest.class.getSimpleName() );
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
        mockMessageFacade = ctrl.createMock( IMessageFacade.class );

        sender = new JmsMessageSender( mockJmsClient );
    }

    /**
     * Called after the tests
     * 
     * @throws Exception
     */
    @After
    public void doAfter() throws Exception {
        sender.tearDown();
        ctrl.verify();
    }

    /**
     * Tests message sending in the normal case.
     * 
     * @throws Exception
     */
    @Test
    public void testNormalSend() throws Exception {
        mockJmsClient.connect();
        expectLastCall().once();

        mockJmsClient.processAction( isA( IJmsAction.class ) );
        expectLastCall().once();

        mockJmsClient.disconnect();
        expectLastCall().once();

        ctrl.replay();

        sender.init();
        sender.processMessage( mockMessageFacade );

    }

    /**
     * Tests that the correct exceptions are thrown when we have an issue in the sending process.
     * 
     * @throws Exception
     */
    @Test(expected = OrcaBridgeException.class)
    public void testFailedSend() throws Exception {
        mockJmsClient.connect();
        expectLastCall().once();

        mockJmsClient.processAction( isA( IJmsAction.class ) );
        expectLastCall().andThrow( new JmsClientException( "Failed to process send action: this is all part of the test" ) );

        mockJmsClient.disconnect();
        expectLastCall().once();

        ctrl.replay();

        sender.init();
        sender.processMessage( mockMessageFacade );
    }

}
