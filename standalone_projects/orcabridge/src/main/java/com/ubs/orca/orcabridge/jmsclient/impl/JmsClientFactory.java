/*
 * JmsClientFactory.java created on 29 Sep 2009 07:08:37 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.jmsclient.impl;

import javax.naming.Context;

import com.ubs.orca.orcabridge.jmsclient.IJmsClient;

/**
 * Factory class to support the creation of JmsClients. The only way
 * to create a new Jms client is through this factory class, thus the
 * entire JMS client impl is encapsulated.
 * 
 * @author suggitpe
 * @version 1.0 29 Sep 2009
 */
public final class JmsClientFactory
{

    /**
     * This class should only contain static builder methods and thus
     * no instances should ever exist.
     */
    private JmsClientFactory()
    {}

    public static final IJmsClient createSingleMessageClient( Context aInitialContext,
                                                              String aConnectionFactoryName,
                                                              String aDestinationName )
    {
        return new JmsSingleMsgClient( aInitialContext, aConnectionFactoryName, aDestinationName );
    }
}
