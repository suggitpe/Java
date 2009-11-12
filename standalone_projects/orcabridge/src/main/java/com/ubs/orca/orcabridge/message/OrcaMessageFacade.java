/*
 * OrcaMessageFacade.java created on 7 Oct 2009 19:14:33 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.message;

import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.orca.client.api.IConversationMessage;
import com.ubs.orca.client.api.IOrcaClient;
import com.ubs.orca.common.bus.IOrcaMessage;
import com.ubs.orca.orcabridge.IMessageFacade;

/**
 * Message facade to encapsulate an Orca message.
 * 
 * @author suggitpe
 * @version 1.0 7 Oct 2009
 */
class OrcaMessageFacade implements IMessageFacade
{

    private static final Log LOG = LogFactory.getLog( OrcaMessageFacade.class );

    @SuppressWarnings("unused")
    private final IOrcaMessage orcaMessage_;

    /**
     * Constructs a new instance.
     * 
     * @param aOrcaMessage
     *            orca message to construct the facade
     */
    OrcaMessageFacade( IOrcaMessage aOrcaMessage )
    {
        super();
        orcaMessage_ = aOrcaMessage;
    }

    /**
     * @see com.ubs.orca.orcabridge.IMessageFacade#buildJmsMessage(javax.jms.Session)
     */
    @Override
    public Message buildJmsMessage( Session aSession )
    {
        LOG.debug( "Building a JMS message" );
        return null;
    }

    /**
     * @see com.ubs.orca.orcabridge.IMessageFacade#buildOrcaMesage(com.ubs.orca.client.api.IOrcaClient)
     */
    @Override
    public IConversationMessage buildOrcaMesage( IOrcaClient aClient )
    {
        LOG.debug( "Building an OrcaMessage" );
        return null;
    }
}
