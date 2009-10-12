/*
 * IJmsReaderClient.java created on 7 Oct 2009 19:36:08 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.jmsclient;

/**
 * Extension of the JMS client that will allow the reading of message
 * from destinations on the broker.
 * 
 * @author suggitpe
 * @version 1.0 7 Oct 2009
 */
public interface IJmsReaderClient extends IJmsClient
{

    /**
     * Starts a durable subscription on a destination on the broker.
     * For each message received, the callback will be called allowing
     * for the processing of the message.
     * 
     * @param aCallback
     *            the callback to be called each time a message is
     *            received.
     * @throws JmsClientException
     */
    void startDurableSubscription( IJmsClientSingleMsgCallback aCallback )
                    throws JmsClientException;

    /**
     * Stops the existing durable subscription on the broker.
     * 
     * @throws JmsClientException
     */
    void stopDurbleSubscription() throws JmsClientException;

}
