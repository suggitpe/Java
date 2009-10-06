/*
 * IJmsClientCallback.java created on 29 Sep 2009 20:08:44 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.jmsclient;

import javax.jms.Message;

/**
 * Main callback into the JMS Client. For each single message received
 * by the JMS client, this callback will be called.
 * 
 * @author suggitpe
 * @version 1.0 29 Sep 2009
 */
public interface IJmsClientSingleMsgCallback
{

    /**
     * This method will be called by the JMS client when a message is
     * received from the destination.
     * 
     * @param aJmsMessage
     *            the message that was received by the JMSClient
     * @throws Throwable
     */
    public void onReceived( Message aJmsMessage ) throws Throwable;

}
