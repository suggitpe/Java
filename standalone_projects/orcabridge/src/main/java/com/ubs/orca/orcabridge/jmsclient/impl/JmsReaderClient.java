/*
 * JmsReaderClient.java created on 7 Oct 2009 19:39:01 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.jmsclient.impl;

import javax.naming.Context;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.orca.orcabridge.jmsclient.IJmsClientSingleMsgCallback;
import com.ubs.orca.orcabridge.jmsclient.IJmsReaderClient;
import com.ubs.orca.orcabridge.jmsclient.JmsClientException;

/**
 * JMS client that only allows for the receiving of messages.
 * 
 * @author suggitpe
 * @version 1.0 7 Oct 2009
 */
public class JmsReaderClient extends JmsClientBase implements IJmsReaderClient
{

    private static final Log LOG = LogFactory.getLog( JmsReaderClient.class );

    public JmsReaderClient( Context aInitialContext, String aConnectionFactoryName,
                            String aDestinationName )
    {
        super( aInitialContext, aConnectionFactoryName, aDestinationName );
    }

    /**
     * @see com.ubs.orca.orcabridge.jmsclient.IJmsReaderClient#startDurableSubscription(com.ubs.orca.orcabridge.jmsclient.IJmsClientSingleMsgCallback)
     */
    public void startDurableSubscription( IJmsClientSingleMsgCallback aCallback )
                    throws JmsClientException
    {
        if ( LOG.isDebugEnabled() )
        {
            LOG.debug( "Starting durable client against destination [" + getDestination() + "]" );
        }
    }

    /**
     * @see com.ubs.orca.orcabridge.jmsclient.IJmsReaderClient#stopDurbleSubscription()
     */
    public void stopDurbleSubscription() throws JmsClientException

    {
        if ( LOG.isDebugEnabled() )
        {
            LOG.debug( "Stopping durable client against destination [" + getDestination() + "]" );
        }
    }
}
