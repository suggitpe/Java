/*
 * OrcaMessageSender.java created on 29 Oct 2009 17:39:26 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.processors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.orca.client.api.IConversationMessage;
import com.ubs.orca.client.api.IOrcaClient;
import com.ubs.orca.client.api.OrcaException;
import com.ubs.orca.orcabridge.IMessageFacade;
import com.ubs.orca.orcabridge.IMessageProcessor;
import com.ubs.orca.orcabridge.OrcaBridgeException;
import com.ubs.orca.orcabridge.OrcaBridgeMessageConversionException;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * Class to send messages to an Orca Token.
 * 
 * @author suggitpe
 * @version 1.0 29 Oct 2009
 */
public class OrcaMessageSender implements IMessageProcessor, InitializingBean
{

    private static final Log LOG = LogFactory.getLog( OrcaMessageSender.class );

    private IOrcaClient orcaClient_;

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception
    {
        Assert.notNull( orcaClient_, "No Orca client has been set on the Orca Message Sender" );
    }

    /**
     * Initialise the sender.
     * 
     * @throws OrcaBridgeException
     */
    public void init() throws OrcaBridgeException
    {
        LOG.info( "Initialising the sender Orca connection" );

        try
        {
            orcaClient_.connect();
        }
        catch ( OrcaException oe )
        {
            throw new OrcaBridgeException( "Failed to initialise the Orca client", oe );
        }
    }

    /**
     * @see com.ubs.orca.orcabridge.IMessageProcessor#processMessage(com.ubs.orca.orcabridge.IMessageFacade)
     */
    @Override
    public void processMessage( IMessageFacade aMessageFacade ) throws OrcaBridgeException
    {
        try
        {
            IConversationMessage orcaMessage = aMessageFacade.buildOrcaMesage( orcaClient_ );
            orcaClient_.send( orcaMessage );
        }
        catch ( OrcaException oe )
        {
            throw new OrcaBridgeException( "Failed to send Orca message", oe );
        }
        catch ( OrcaBridgeMessageConversionException obmce )
        {
            throw new OrcaBridgeException( "Failed to create Orca message from message facade",
                                           obmce );
        }

    }

    /**
     * Sets the orcaClient. This method is here to support replacing
     * the orca client with mocks for unit tests.
     * 
     * @param aOrcaClient
     *            the orca client
     */
    public void setOrcaClient( IOrcaClient aOrcaClient )
    {
        orcaClient_ = aOrcaClient;
    }

}
