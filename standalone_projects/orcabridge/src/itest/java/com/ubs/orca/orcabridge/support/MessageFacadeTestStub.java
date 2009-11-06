/*
 * MessageFacadeTestStub.java created on 30 Oct 2009 07:03:44 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.support;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.orca.client.api.IConversation;
import com.ubs.orca.client.api.IConversationMessage;
import com.ubs.orca.client.api.IOrcaClient;
import com.ubs.orca.client.api.OrcaException;
import com.ubs.orca.common.bus.MessageType;
import com.ubs.orca.orcabridge.IMessageFacade;
import com.ubs.orca.orcabridge.OrcaBridgeMessageConversionException;

/**
 * This is a test class to be used only in conjunction with testing
 * the Orca Bridge.
 * 
 * @author suggitpe
 * @version 1.0 30 Oct 2009
 */
public class MessageFacadeTestStub implements IMessageFacade
{

    private static final Log LOG = LogFactory.getLog( MessageFacadeTestStub.class );

    private static final String MSG_BODY = "This is a test message created by the Message Facade Test Stub";

    /**
     * @see com.ubs.orca.orcabridge.IMessageFacade#buildJmsMessage(javax.jms.Session)
     */
    @Override
    public Message buildJmsMessage( Session aSession ) throws OrcaBridgeMessageConversionException
    {
        LOG.debug( "Creating a test JMS message with default content" );
        try
        {
            Message message = aSession.createTextMessage( MSG_BODY );
            message.setJMSCorrelationID( "ID:12345678901234567890" );
            return message;
        }
        catch ( JMSException jmse )
        {
            LOG.error( "Failed to create the test message" );
            throw new OrcaBridgeMessageConversionException( "Failed to create the test JMS message" );
        }
    }

    /**
     * @see com.ubs.orca.orcabridge.IMessageFacade#buildOrcaMesage(com.ubs.orca.client.api.IOrcaClient)
     */
    @Override
    public IConversationMessage buildOrcaMesage( IOrcaClient aClient )
                    throws OrcaBridgeMessageConversionException
    {
        LOG.debug( "Creating a test Orca message with default content" );
        try
        {
            IConversation conversation = aClient.createConversation( "Terribly dull conversation" );
            IConversationMessage conversationMessage = aClient.createTextMessage( conversation,
                                                                                  MessageType.REQUEST,
                                                                                  "suggs.test",
                                                                                  MSG_BODY );
            return conversationMessage;
        }
        catch ( OrcaException oe )
        {
            throw new OrcaBridgeMessageConversionException( "Failed to create the test Orca message" );
        }
    }
}
