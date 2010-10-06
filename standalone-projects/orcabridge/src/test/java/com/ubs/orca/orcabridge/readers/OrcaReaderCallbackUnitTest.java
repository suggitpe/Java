/*
 * OrcaReaderCallbackUnitTest.java created on 9 Nov 2009 08:26:31 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.readers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ubs.orca.client.api.IAttributesConversationMessage;
import com.ubs.orca.client.api.ITextConversationMessage;
import com.ubs.orca.orcabridge.IMessageFacade;
import com.ubs.orca.orcabridge.IMessageProcessor;
import com.ubs.orca.orcabridge.OrcaBridgeException;

import static org.easymock.EasyMock.createControl;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.isA;
import static org.junit.Assert.fail;

/**
 * Test suite for teh Orca reader callback.
 * 
 * @author suggitpe
 * @version 1.0 9 Nov 2009
 */
public class OrcaReaderCallbackUnitTest {

    private static final Log LOG = LogFactory.getLog( OrcaReaderCallbackUnitTest.class );

    private OrcaSingleMessageReaderCallback orcaCallback;
    private IMocksControl ctrl;
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

        orcaCallback = new OrcaSingleMessageReaderCallback( mockMessageProcessor );
    }

    /**
     * Tests that the message callback is called and that it then delegates to the message processor.
     * 
     * @throws Throwable
     */
    @Test
    public void testOrcaCallbackWithAttributesMessage() throws Throwable {
        mockMessageProcessor.processMessage( isA( IMessageFacade.class ) );
        expectLastCall().once();

        IAttributesConversationMessage msg = ctrl.createMock( IAttributesConversationMessage.class );

        ctrl.replay();

        orcaCallback.onReceived( msg );

        ctrl.verify();
    }

    /**
     * Tests that the message callback is called and that it then delegates to the message processor.
     * 
     * @throws Throwable
     */
    @Test
    public void testOrcaCallbackWithTextMessage() throws Throwable {
        mockMessageProcessor.processMessage( isA( IMessageFacade.class ) );
        expectLastCall().once();

        ITextConversationMessage msg = ctrl.createMock( ITextConversationMessage.class );

        ctrl.replay();

        orcaCallback.onReceived( msg );

        ctrl.verify();
    }

    /**
     * Test that the correc texception pops out of teh top of the stack when there is an issue in teh message
     * processor layer.
     * 
     * @throws Throwable
     */
    @Test(expected = OrcaBridgeException.class)
    public void testOrcaCallbackWithProcessFailure() throws Throwable {
        mockMessageProcessor.processMessage( isA( IMessageFacade.class ) );
        expectLastCall().andThrow( new OrcaBridgeException( "This is all part of the test" ) );

        IAttributesConversationMessage msg = ctrl.createMock( IAttributesConversationMessage.class );

        ctrl.replay();

        orcaCallback.onReceived( msg );
        fail( "Test test should not have reached this far" );

        ctrl.verify();
    }
}
