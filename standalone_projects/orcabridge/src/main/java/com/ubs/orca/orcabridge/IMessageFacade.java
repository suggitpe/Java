/*
 * IMessageAdapter.java created on 7 Oct 2009 18:04:16 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge;

import javax.jms.Message;
import javax.jms.Session;

import com.ubs.orca.client.api.IConversationMessage;
import com.ubs.orca.client.api.IOrcaClient;

/**
 * This interface encapsulates the fact that there are a number of
 * differing message types that will be utiloised in the OrcaBridge
 * and for each message type we would like the system related
 * behaviour to differ.
 * 
 * @author suggitpe
 * @version 1.0 7 Oct 2009
 */
public interface IMessageFacade
{

    /**
     * Build a JMS Message using a JMS session.
     * 
     * @param aSession
     *            the session from which to create the message
     * @return a new JMS message
     * @throws OrcaBridgeMessageConversionException
     */
    public Message buildJmsMessage( Session aSession ) throws OrcaBridgeMessageConversionException;

    /**
     * Build an Orca message using an Orca client
     * 
     * @param aClient
     *            the client from which to build the Orca Message
     * @return a new Orca message
     * @throws OrcaBridgeMessageConversionException
     */
    public IConversationMessage buildOrcaMesage( IOrcaClient aClient )
                    throws OrcaBridgeMessageConversionException;

}
