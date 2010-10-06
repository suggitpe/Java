/*
 * JmsMessageSender.java created on 29 Oct 2009 07:09:34 by suggitpe for project Orca Bridge 
 * 
 */
package com.ubs.orca.orcabridge.processors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.orca.orcabridge.IMessageFacade;
import com.ubs.orca.orcabridge.IMessageProcessor;
import com.ubs.orca.orcabridge.OrcaBridgeException;
import com.ubs.orca.orcabridge.jmsclient.IJmsClient;
import com.ubs.orca.orcabridge.jmsclient.JmsClientException;
import com.ubs.orca.orcabridge.jmsclient.impl.JmsSenderAction;

/**
 * Class to send messages to a JMS destination.
 * 
 * @author suggitpe
 * @version 1.0 29 Oct 2009
 */
public class JmsMessageSender implements IMessageProcessor {

    private static final Log LOG = LogFactory.getLog( JmsMessageSender.class );

    private IJmsClient jmsClientCore;

    /**
     * Constructs a new instance.
     */
    public JmsMessageSender( IJmsClient aJmsClient ) {
        jmsClientCore = aJmsClient;
    }

    /**
     * Initialisation method
     * 
     * @throws JmsClientException
     */
    public void init() throws JmsClientException {
        LOG.debug( "Initialising the sender connection" );
        jmsClientCore.connect();
    }

    /**
     * This method is called when spring destroys its bean factories
     * 
     * @throws JmsClientException
     */
    public void tearDown() throws JmsClientException {
        LOG.debug( "Tearing down the sender connection" );
        jmsClientCore.disconnect();
    }

    /**
     * @see com.ubs.orca.orcabridge.IMessageProcessor#processMessage(com.ubs.orca.orcabridge.IMessageFacade)
     */
    @Override
    public void processMessage( IMessageFacade aMessageFacade ) throws OrcaBridgeException {
        JmsSenderAction action = new JmsSenderAction( aMessageFacade );
        try {
            jmsClientCore.processAction( action );
        }
        catch ( JmsClientException jce ) {
            throw new OrcaBridgeException( "Failed to send message to JMS destination", jce );
        }
    }

    /**
     * Sets the jmsClient field to the specified value.
     * 
     * @param aJmsClient
     *            The jmsClient to set.
     */
    public void setJmsClient( IJmsClient aJmsClient ) {
        jmsClientCore = aJmsClient;
    }

}
