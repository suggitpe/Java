/*
 * OrcaMessageSenderTest.java created on 4 Nov 2009 06:52:46 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ubs.orca.client.api.IConversationMessage;
import com.ubs.orca.client.api.IOrcaClient;
import com.ubs.orca.client.api.OrcaException;
import com.ubs.orca.orcabridge.IMessageFacade;
import com.ubs.orca.orcabridge.OrcaBridgeException;
import com.ubs.orca.orcabridge.OrcaBridgeMessageConversionException;

import static org.easymock.EasyMock.createControl;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;

/**
 * Test suite for the Orca message sender processor.
 * 
 * @author suggitpe
 * @version 1.0 4 Nov 2009
 */
public class OrcaMessageSenderTest {

    private static final Logger LOG = LoggerFactory.getLogger( OrcaMessageSenderTest.class );

    private IMocksControl ctrl;
    private OrcaMessageSender sender;

    private IMessageFacade mockMessageFacade;
    private IOrcaClient mockOrcaClient;
    private IConversationMessage mockOrcaMessage;

    /** */
    @BeforeClass
    public static void doBeforeClass() {
        LOG.debug( "=================== " + OrcaMessageSenderTest.class.getSimpleName() );
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
        mockMessageFacade = ctrl.createMock( IMessageFacade.class );
        mockOrcaClient = ctrl.createMock( IOrcaClient.class );
        mockOrcaMessage = ctrl.createMock( IConversationMessage.class );

        sender = new OrcaMessageSender( mockOrcaClient );
    }

    /**
     * Test the normal send process for an Orca message.
     * 
     * @throws Exception
     */
    @Test
    public void testNormalSend() throws Exception {
        expect( mockMessageFacade.buildOrcaMesage( mockOrcaClient ) ).andReturn( mockOrcaMessage ).once();

        expect( mockOrcaClient.send( mockOrcaMessage ) ).andReturn( "test rubbish" );

        ctrl.replay();
        sender.processMessage( mockMessageFacade );
        ctrl.verify();
    }

    /**
     * Tests that when we call init we call the connect method
     * 
     * @throws Exception
     */
    @Test
    public void testSuccessfulInit() throws Exception {
        mockOrcaClient.connect();
        expectLastCall().once();

        ctrl.replay();
        sender.init();
        ctrl.verify();
    }

    /**
     * Tests that when we get a connection failure in the init process we get the right exception thrown up
     * the stack.
     * 
     * @throws Exception
     */
    @Test(expected = OrcaBridgeException.class)
    public void testFailOnInit() throws Exception {
        mockOrcaClient.connect();
        expectLastCall().andThrow( new OrcaException( "Failed on connect: this is all part of the test" ) );

        ctrl.replay();
        sender.init();
        ctrl.verify();
    }

    /**
     * Test the normal send process for an Orca message.
     * 
     * @throws OrcaException
     * @throws OrcaBridgeException
     * @throws OrcaBridgeMessageConversionException
     */
    @Test(expected = OrcaBridgeException.class)
    public void testSendButFailOnMessageCreation() throws OrcaBridgeException,
                    OrcaBridgeMessageConversionException, OrcaException {
        mockOrcaClient.connect();
        expectLastCall().once();

        expect( mockMessageFacade.buildOrcaMesage( mockOrcaClient ) ).andThrow( new OrcaBridgeMessageConversionException( "Failed on msg creation: this is all part of the test" ) );

        ctrl.replay();
        sender.processMessage( mockMessageFacade );
        ctrl.verify();
    }

    /**
     * Test that if there is an error in the send process, that the correct exception is passed up the stack.
     * 
     * @throws OrcaBridgeException
     * @throws OrcaBridgeMessageConversionException
     * @throws OrcaException
     */
    @Test(expected = OrcaBridgeException.class)
    public void testSendButFailOnSend() throws OrcaBridgeException, OrcaBridgeMessageConversionException,
                    OrcaException {
        mockOrcaClient.connect();
        expectLastCall().once();

        expect( mockMessageFacade.buildOrcaMesage( mockOrcaClient ) ).andReturn( mockOrcaMessage ).once();

        expect( mockOrcaClient.send( mockOrcaMessage ) ).andThrow( new OrcaException( "Failed on send: this is all part of the test" ) );

        ctrl.replay();
        sender.processMessage( mockMessageFacade );
        ctrl.verify();
    }

}
