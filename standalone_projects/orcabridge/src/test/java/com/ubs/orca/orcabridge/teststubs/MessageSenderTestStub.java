/*
 * MessageReaderTestStub.java created on 27 Sep 2009 10:10:10 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.teststubs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.orca.orcabridge.IMessageSender;
import com.ubs.orca.orcabridge.MessageFacade;
import com.ubs.orca.orcabridge.OrcaBridgeException;

/**
 * Test stub class for Message Senders
 * 
 * @author suggitpe
 * @version 1.0 27 Sep 2009
 */
public class MessageSenderTestStub implements IMessageSender
{

    private static final Log LOG = LogFactory.getLog( MessageSenderTestStub.class );

    /**
     * @see com.ubs.orca.orcabridge.IMessageSender#sendMessage(com.ubs.orca.orcabridge.MessageFacade)
     */
    @Override
    public void sendMessage( MessageFacade aMessageFacade ) throws OrcaBridgeException
    {
        LOG.debug( "Test sending a message on the Test Stub Message Sender" );
    }

    /**
     * @see com.ubs.orca.orcabridge.IMessageSender#startSender()
     */
    @Override
    public void startSender() throws OrcaBridgeException
    {
        LOG.debug( "Starting sender on the Test Stub Message Sender" );
    }

    /**
     * @see com.ubs.orca.orcabridge.IMessageSender#stopSender()
     */
    @Override
    public void stopSender() throws OrcaBridgeException
    {
        LOG.debug( "Stopping sender on the Test Stub Message Sender" );
    }

}
