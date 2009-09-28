/*
 * MessageReaderTestStub.java created on 27 Sep 2009 10:10:10 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.teststubs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.orca.orcabridge.IMessageProcessor;
import com.ubs.orca.orcabridge.MessageFacade;
import com.ubs.orca.orcabridge.OrcaBridgeException;

/**
 * Test stub class for Message Senders
 * 
 * @author suggitpe
 * @version 1.0 27 Sep 2009
 */
public class MessageProcessorTestStub implements IMessageProcessor
{

    private static final Log LOG = LogFactory.getLog( MessageProcessorTestStub.class );

    /**
     * @see com.ubs.orca.orcabridge.IMessageProcessor#processMessage(com.ubs.orca.orcabridge.MessageFacade)
     */
    @Override
    public void processMessage( MessageFacade aMessageFacade ) throws OrcaBridgeException
    {
        LOG.debug( "Test sending a message on the Test Stub Message Sender" );
    }

    /**
     * @see com.ubs.orca.orcabridge.IMessageProcessor#startProcessor()
     */
    @Override
    public void startProcessor() throws OrcaBridgeException
    {
        LOG.debug( "Starting sender on the Test Stub Message Sender" );
    }

    @Override
    public void stopProcessor() throws OrcaBridgeException
    {
        LOG.debug( "Stopping sender on the Test Stub Message Sender" );
    }

}
