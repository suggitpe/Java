/*
 * JmsReaderClient.java created on 7 Oct 2009 19:39:01 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.jmsclient.impl;

import javax.jms.Destination;
import javax.jms.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.orca.orcabridge.jmsclient.IJmsClientSingleMsgCallback;
import com.ubs.orca.orcabridge.jmsclient.IJmsProcessCallback;
import com.ubs.orca.orcabridge.jmsclient.JmsClientException;

/**
 * JMS client that only allows for the receiving of messages.
 * 
 * @author suggitpe
 * @version 1.0 7 Oct 2009
 */
public class JmsReadingCallback implements IJmsProcessCallback
{

    private static final Log LOG = LogFactory.getLog( JmsReadingCallback.class );

    /**
     * Constructs a new instance.
     */
    public JmsReadingCallback()
    {
        super();
    }

    /**
     * @see com.ubs.orca.orcabridge.jmsclient.IJmsProcessCallback#processInTransaction(javax.jms.Session,
     *      javax.jms.Destination)
     */
    @Override
    public void processInTransaction( Session aSession, Destination aDestinstion )
                    throws JmsClientException
    {

    }

    public void startDurableSubscription( IJmsClientSingleMsgCallback aCallback )
                    throws JmsClientException
    {}

    public void stopDurbleSubscription() throws JmsClientException

    {}

}
