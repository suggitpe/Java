/*
 * JmsSenderClient.java created on 7 Oct 2009 19:38:11 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.jmsclient.impl;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.Context;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.orca.orcabridge.IMessageFacade;
import com.ubs.orca.orcabridge.jmsclient.IJmsSenderClient;
import com.ubs.orca.orcabridge.jmsclient.JmsClientException;

/**
 * JMS client that only allows for the sending of messages.
 * 
 * @author suggitpe
 * @version 1.0 7 Oct 2009
 */
public class JmsSenderClient extends JmsClientBase implements IJmsSenderClient
{

    private static final Log LOG = LogFactory.getLog( JmsSenderClient.class );

    public JmsSenderClient( Context aInitialContext, String aConnectionFactoryName,
                            String aDestinationName )
    {
        super( aInitialContext, aConnectionFactoryName, aDestinationName );
    }

    /**
     * @see com.ubs.orca.orcabridge.jmsclient.IJmsSenderClient#sendMessageToDestination(com.ubs.orca.orcabridge.IMessageFacade)
     */
    public void sendMessageToDestination( IMessageFacade aMessageFacade ) throws JmsClientException
    {
        Session session = null;
        try
        {
            synchronized ( getSynchLock() )
            {
                LOG.info( "Creating sending transaction ..." );
                Connection conn = getConnection();
                if ( conn == null )
                {
                    throw new JmsClientException( "No active connection, you must connect before you send" );
                }
                session = getConnection().createSession( true, Session.CLIENT_ACKNOWLEDGE );
                createProducerAndSend( aMessageFacade, session );
                session.commit();
                LOG.info( "Sending transaction commited" );
            }
        }
        catch ( JMSException je )
        {
            String err = "Failed to create JMS session";
            LOG.error( err );
            throw new JmsClientException( err, je );
        }
        finally
        {
            if ( session != null )
            {
                try
                {
                    session.close();
                }
                catch ( JMSException je )
                {
                    LOG.error( "Failed to close session" );
                }
            }
        }
    }

    private void createProducerAndSend( IMessageFacade aMessageFacade, Session aSession )
                    throws JmsClientException
    {

        MessageProducer producer = null;
        try
        {
            Destination dest = getDestination();
            producer = aSession.createProducer( dest );
            if ( LOG.isDebugEnabled() )
            {
                LOG.debug( "Created producer for destination [" + dest + "]" );
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
