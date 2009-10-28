/*
 * JmsSenderClient.java created on 7 Oct 2009 19:38:11 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.jmsclient.impl;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.orca.orcabridge.IMessageFacade;
import com.ubs.orca.orcabridge.jmsclient.IJmsAction;
import com.ubs.orca.orcabridge.jmsclient.JmsClientException;

/**
 * JMS client that only allows for the sending of messages.
 * 
 * @author suggitpe
 * @version 1.0 7 Oct 2009
 */
public class JmsSenderAction implements IJmsAction
{

    private static final Log LOG = LogFactory.getLog( JmsSenderAction.class );

    private IMessageFacade messageFacade_;

    /**
     * Constructs a new instance.
     * 
     * @param aMessageFacade
     *            a message facade
     */
    public JmsSenderAction( IMessageFacade aMessageFacade )
    {
        super();
        messageFacade_ = aMessageFacade;
    }

    /**
     * @see com.ubs.orca.orcabridge.jmsclient.IJmsAction#action(javax.jms.Session,
     *      javax.jms.Destination)
     */
    @Override
    public void action( Session aSession, Destination aDestination ) throws JmsClientException
    {
        createProducerAndSend( messageFacade_, aSession, aDestination );
        try
        {
            aSession.commit();
        }
        catch ( JMSException jmse )
        {
            throw new JmsClientException( "Failed to commit JMS transaction", jmse );
        }
    }

    private void createProducerAndSend( IMessageFacade aMessageFacade, Session aSession,
                                        Destination aDestination ) throws JmsClientException
    {

        MessageProducer producer = null;
        try
        {
            producer = aSession.createProducer( aDestination );
            if ( LOG.isDebugEnabled() )
            {
                LOG.debug( "Created producer for destination [" + aDestination + "]" );
            }
            sendMessage( aMessageFacade, aSession, producer );
        }
        catch ( JMSException je )
        {
            String err = "Failed to create message producer";
            LOG.error( err );
            throw new JmsClientException( "Failed to create message producer", je );
        }
        finally
        {
            if ( producer != null )
            {
                try
                {
                    producer.close();
                }
                catch ( JMSException je )
                {
                    LOG.error( "Failed to close the message producer" );
                }
            }
        }
    }

    private void sendMessage( IMessageFacade aMessageFacade, Session aSession,
                              MessageProducer aMessageProducer ) throws JmsClientException
    {
        try
        {
            Message msg = aMessageFacade.buildJmsMessage( aSession );
            if ( LOG.isInfoEnabled() )
            {
                LOG.info( "Sending message [" + msg + "]" );
            }
            aMessageProducer.send( msg );
        }
        catch ( JMSException je )
        {
            throw new JmsClientException( "Failed to create and send JMS message", je );
        }
    }

}
