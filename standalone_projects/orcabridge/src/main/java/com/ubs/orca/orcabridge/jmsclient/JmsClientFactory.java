/*
 * JmsClientFactory.java created on 29 Sep 2009 07:08:37 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.jmsclient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * TODO Write javadoc for JmsClientFactory
 * 
 * @author suggitpe
 * @version 1.0 29 Sep 2009
 */
public class JmsClientFactory
{

    private static final Log LOG = LogFactory.getLog( JmsClientFactory.class );

    public static final IJmsClient createJmsClient() throws JmsClientException
    {
        LOG.debug( "not yet impl" );
        throw new JmsClientException( "not yet impl" );
    }
}
