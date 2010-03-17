/*
 * LogOnlyMessageProcessor.java created on 10 Nov 2009 20:15:43 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.orca.orcabridge.IMessageFacade;
import com.ubs.orca.orcabridge.IMessageProcessor;
import com.ubs.orca.orcabridge.OrcaBridgeException;

/**
 * This is a stub class that will only ever perform logging on a
 * received message
 * 
 * @author suggitpe
 * @version 1.0 10 Nov 2009
 */
public class LogOnlyMessageProcessor implements IMessageProcessor
{

    private static final Log LOG = LogFactory.getLog( LogOnlyMessageProcessor.class );

    /**
     * @see com.ubs.orca.orcabridge.IMessageProcessor#processMessage(com.ubs.orca.orcabridge.IMessageFacade)
     */
    @Override
    public void processMessage( IMessageFacade aMessageFacade ) throws OrcaBridgeException
    {
        LOG.debug( "***** Processing message [" + aMessageFacade + "] *****" );
    }
}
