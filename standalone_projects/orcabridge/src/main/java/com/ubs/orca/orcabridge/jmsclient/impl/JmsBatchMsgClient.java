/*
 * JmsBatchMsgClient.java created on 6 Oct 2009 07:19:40 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.jmsclient.impl;

import javax.naming.Context;

/**
 * This implementation of the jms client deals with batches of
 * messages. The batches are configurable and and will allow a number
 * of messages to be read and then routed within a single transaction.
 * 
 * @author suggitpe
 * @version 1.0 6 Oct 2009
 */
public class JmsBatchMsgClient extends AbstractJmsClient
{

    /**
     * Constructs a new instance.
     * 
     * @param aInitialContext
     * @param aConnectionFactoryName
     * @param aDestinationName_
     */
    JmsBatchMsgClient( Context aInitialContext, String aConnectionFactoryName,
                       String aDestinationName_ )
    {
        super( aInitialContext, aConnectionFactoryName, aDestinationName_ );
    }

}
