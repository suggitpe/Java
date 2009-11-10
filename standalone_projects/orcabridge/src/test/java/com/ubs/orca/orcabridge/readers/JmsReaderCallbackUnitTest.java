/*
 * JmsReaderCallbackUnitTest.java created on 9 Nov 2009 08:26:16 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.readers;

import javax.jms.Message;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
public class JmsReaderCallbackUnitTest
{

    private static final Log LOG = LogFactory.getLog( JmsReaderCallbackUnitTest.class );

    private IMocksControl ctrl_;
    private JmsSingleMessageReaderCallback jmsCallback_;
    private IMessageProcessor mockMessageProcessor_;

    /** */
    @BeforeClass
    public static void doBeforeClass()
    {
        LOG.debug( "=================== " + JmsReaderCallbackUnitTest.class.getSimpleName() );
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
        mockMessageProcessor_ = ctrl_.createMock( IMessageProcessor.class );

        jmsCallback_ = new JmsSingleMessageReaderCallback();
        jmsCallback_.setMessageProcessor( mockMessageProcessor_ );

        jmsCallback_.afterPropertiesSet();
    }

    /**
     * Tests that the message callback is called and that it then
     * delegates to the message processor.
     * 
     * @throws JmsClientException
     * @throws OrcaBridgeException
     */
    @Test
    public void testJmsCallback() throws JmsClientException, OrcaBridgeException
    {
        mockMessageProcessor_.processMessage( isA( IMessageFacade.class ) );
        expectLastCall().once();

        Message msg = ctrl_.createMock( Message.class );

        ctrl_.replay();
        jmsCallback_.onReceived( msg );
        ctrl_.verify();
    }

    /**
     * @throws JmsClientException
     * @throws OrcaBridgeException
     */
    @Test(expected = JmsClientException.class)
    public void testJmsCallbackWithProcessFailure() throws JmsClientException, OrcaBridgeException
    {
        mockMessageProcessor_.processMessage( isA( IMessageFacade.class ) );
        expectLastCall().andThrow( new OrcaBridgeException( "ProcessMessage failed: This is all part of the test" ) );

        Message msg = ctrl_.createMock( Message.class );

        ctrl_.replay();

        jmsCallback_.onReceived( msg );
        fail( "Test test should not have reached this far" );

        ctrl_.verify();
    }
}
