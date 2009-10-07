/*
 * JmsSingleMsgClient.java created on 6 Oct 2009 07:18:49 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.jmsclient.impl;

import javax.naming.Context;

/**
 * This implementation of the Jms Client deals with single messages.
 * This is the default message client that should be used.
 * 
 * @author suggitpe
 * @version 1.0 6 Oct 2009
 */
public class JmsSingleMsgClient extends AbstractJmsClient
{

    /**
     * Constructs a new instance.
     * 
     * @param aInitialContext
     * @param aConnectionFactoryName
     * @param aDestinationName_
     */
    JmsSingleMsgClient( Context aInitialContext, String aConnectionFactoryName,
                        String aDestinationName_ )
    {
        super( aInitialContext, aConnectionFactoryName, aDestinationName_ );
    }

}
