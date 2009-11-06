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
public class JmsMessageSenderTest
{

    private static final Log LOG = LogFactory.getLog( JmsMessageSenderTest.class );

    private IMocksControl ctrl_;
    private JmsMessageSender sender_;

    private IJmsClient mockJmsClient_;
    private IMessageFacade mockMessageFacade_;

    /** */
    @BeforeClass
    public static void doBeforeClass()
    {
        LOG.debug( "=================== " + JmsMessageSenderTest.class.getSimpleName() );
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
        mockJmsClient_ = ctrl_.createMock( IJmsClient.class );
        mockMessageFacade_ = ctrl_.createMock( IMessageFacade.class );

        sender_ = new JmsMessageSender();
        sender_.setJmsClientCore( mockJmsClient_ );

        sender_.afterPropertiesSet();
    }

    /**
     * Called after the tests
     * 
     * @throws Exception
     */
    @After
    public void doAfter() throws Exception
    {
        sender_.tearDown();
        ctrl_.verify();
    }

    /**
     * Tests message sending in the normal case.
     * 
     * @throws Exception
     */
    @Test
    public void testNormalSend() throws Exception
    {
        mockJmsClient_.connect();
        expectLastCall().once();

        mockJmsClient_.processAction( isA( IJmsAction.class ) );
        expectLastCall().once();

        mockJmsClient_.disconnect();
        expectLastCall().once();

        ctrl_.replay();

        sender_.init();
        sender_.processMessage( mockMessageFacade_ );

    }

    /**
     * Tests that the correct exceptions are thrown when we have an
     * issue in the sending process.
     * 
     * @throws Exception
     */
    @Test(expected = OrcaBridgeException.class)
    public void testFailedSend() throws Exception
    {
        mockJmsClient_.connect();
        expectLastCall().once();

        mockJmsClient_.processAction( isA( IJmsAction.class ) );
        expectLastCall().andThrow( new JmsClientException( "Failed to process send action: this is all part of the test" ) );

        mockJmsClient_.disconnect();
        expectLastCall().once();

        ctrl_.replay();

        sender_.init();
        sender_.processMessage( mockMessageFacade_ );
    }

}
