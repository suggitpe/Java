/*
 * IJmsClientCallback.java created on 29 Sep 2009 20:08:44 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.jmsclient;

import java.util.List;

import javax.jms.Message;

/**
 * Main callback into the JMS Client. For each batch of messages
 * received by the JMS client, this callback will be called.
 * 
 * @author suggitpe
 * @version 1.0 29 Sep 2009
 */
public interface IJmsClientBatchMsgCallback
{

    /**
     * This method will be called by the JMS client when a batch of
     * messages are received from the destination.
     * 
     * @param aListOfJmsMessages
     *            the messages that were received by the JMSClient
     * @throws Throwable
     */
    public void onReceived( List<Message> aListOfJmsMessages ) throws Throwable;

}
