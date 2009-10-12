/*
 * IJmsSenderClient.java created on 7 Oct 2009 19:36:33 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.jmsclient;

import com.ubs.orca.orcabridge.IMessageFacade;

/**
 * Extension of the JMS Client that will allow for the sending of
 * messages to a destination on a JMS broker.
 * 
 * @author suggitpe
 * @version 1.0 7 Oct 2009
 */
public interface IJmsSenderClient extends IJmsClient
{

    /**
     * Send a message to a destination on a broker.
     * 
     * @param aMessageFacade
     *            the facade to allow for the building of the messages
     *            to send
     * @throws JmsClientException
     */
    void sendMessageToDestination( IMessageFacade aMessageFacade ) throws JmsClientException;

}
