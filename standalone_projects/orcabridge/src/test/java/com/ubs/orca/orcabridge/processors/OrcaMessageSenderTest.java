/*
 * OrcaMessageSenderTest.java created on 4 Nov 2009 06:52:46 by suggitpe for project Orca Bridge
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

import com.ubs.orca.client.api.IConversationMessage;
import com.ubs.orca.client.api.IOrcaClient;
import com.ubs.orca.client.api.OrcaException;
import com.ubs.orca.client.api.OrcaIdentity;
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
public class OrcaMessageSenderTest
{

    private static final Log LOG = LogFactory.getLog( OrcaMessageSenderTest.class );

    private IMocksControl ctrl_;
    private OrcaMessageSender sender_;

    private IMessageFacade mockMessageFacade_;
    private IOrcaClient mockOrcaClient_;
    private IConversationMessage mockOrcaMessage_;

    private static String ORCA_TOKEN = "OrcaBridgeTestToken:1";
    private static String ORCA_URL = "tcp://localhost:7222";

    /** */
    @BeforeClass
    public static void doBeforeClass()
    {
        LOG.debug( "=================== " + OrcaMessageSenderTest.class.getSimpleName() );
    }

    /**
     * Test setup
     * 
     * @throws Exception
     */
    @Before
    public void doBefore() throws Exception
    {
        LOG.debug( "-----------------" );
        ctrl_ = createControl();
        mockMessageFacade_ = ctrl_.createMock( IMessageFacade.class );
        mockOrcaClient_ = ctrl_.createMock( IOrcaClient.class );
        mockOrcaMessage_ = ctrl_.createMock( IConversationMessage.class );

        sender_ = new OrcaMessageSender();
        sender_.setOrcaIdentity( new OrcaIdentity( ORCA_TOKEN ) );
        sender_.setOrcaConnectionUrl( ORCA_URL );
        sender_.setOrcaClient( mockOrcaClient_ );

        sender_.afterPropertiesSet();
    }

    /** */
    @After
    public void doAfter()
    {}

    /**
     * Test the normal send process for an Orca message.
     * 
     * @throws Exception
     */
    @Test
    public void testNormalSend() throws Exception
    {
        expect( mockMessageFacade_.buildOrcaMesage( mockOrcaClient_ ) ).andReturn( mockOrcaMessage_ )
            .once();

        expect( mockOrcaClient_.send( mockOrcaMessage_ ) ).andReturn( "test rubbish" );

        ctrl_.replay();
        sender_.processMessage( mockMessageFacade_ );

        ctrl_.verify();
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
                    OrcaBridgeMessageConversionException, OrcaException
    {
        mockOrcaClient_.connect();
        expectLastCall().once();

        expect( mockMessageFacade_.buildOrcaMesage( mockOrcaClient_ ) ).andThrow( new OrcaBridgeMessageConversionException( "Failed on msg creation: this is all part of the test" ) );

        ctrl_.replay();
        sender_.processMessage( mockMessageFacade_ );

        ctrl_.verify();
    }

    /**
     * Test that if there is an error in the send process, that the
     * correct exception is passed up the stack.
     * 
     * @throws OrcaBridgeException
     * @throws OrcaBridgeMessageConversionException
     * @throws OrcaException
     */
    @Test(expected = OrcaBridgeException.class)
    public void testSendButFailOnSend() throws OrcaBridgeException,
                    OrcaBridgeMessageConversionException, OrcaException
    {
        mockOrcaClient_.connect();
        expectLastCall().once();

        expect( mockMessageFacade_.buildOrcaMesage( mockOrcaClient_ ) ).andReturn( mockOrcaMessage_ )
            .once();

        expect( mockOrcaClient_.send( mockOrcaMessage_ ) ).andThrow( new OrcaException( "Failed on send: this is all part of the test" ) );

        ctrl_.replay();
        sender_.processMessage( mockMessageFacade_ );

        ctrl_.verify();
    }

}
