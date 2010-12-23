/*
 * OrcaMessageSender.java created on 29 Oct 2009 17:39:26 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubs.orca.client.api.IConversationMessage;
import com.ubs.orca.client.api.IOrcaClient;
import com.ubs.orca.client.api.OrcaException;
import com.ubs.orca.orcabridge.IMessageFacade;
import com.ubs.orca.orcabridge.IMessageProcessor;
import com.ubs.orca.orcabridge.OrcaBridgeException;
import com.ubs.orca.orcabridge.OrcaBridgeMessageConversionException;

/**
 * Class to send messages to an Orca Token.
 * 
 * @author suggitpe
 * @version 1.0 29 Oct 2009
 */
public class OrcaMessageSender implements IMessageProcessor {

    private static final Logger LOG = LoggerFactory.getLogger( OrcaMessageSender.class );

    private IOrcaClient orcaClient;

    /**
     * Constructs a new instance.
     */
    public OrcaMessageSender( IOrcaClient aOrcaClient ) {
        orcaClient = aOrcaClient;
    }

    /**
     * Initialise the sender.
     * 
     * @throws OrcaBridgeException
     */
    public void init() throws OrcaBridgeException {
        LOG.info( "Initialising the sender Orca connection" );

        try {
            orcaClient.connect();
        }
        catch ( OrcaException oe ) {
            throw new OrcaBridgeException( "Failed to initialise the Orca client", oe );
        }
    }

    /**
     * @see com.ubs.orca.orcabridge.IMessageProcessor#processMessage(com.ubs.orca.orcabridge.IMessageFacade)
     */
    @Override
    public void processMessage( IMessageFacade aMessageFacade ) throws OrcaBridgeException {
        try {
            IConversationMessage orcaMessage = aMessageFacade.buildOrcaMesage( orcaClient );
            orcaClient.send( orcaMessage );
        }
        catch ( OrcaException oe ) {
            throw new OrcaBridgeException( "Failed to send Orca message", oe );
        }
        catch ( OrcaBridgeMessageConversionException obmce ) {
            throw new OrcaBridgeException( "Failed to create Orca message from message facade", obmce );
        }

    }

    /**
     * Sets the orcaClient. This method is here to support replacing the orca client with mocks for unit
     * tests.
     * 
     * @param aOrcaClient
     *            the orca client
     */
    public void setOrcaClient( IOrcaClient aOrcaClient ) {
        orcaClient = aOrcaClient;
    }

}
