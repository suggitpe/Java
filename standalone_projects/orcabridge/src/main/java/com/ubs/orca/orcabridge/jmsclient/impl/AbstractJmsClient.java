/*
 * JmsClient.java created on 29 Sep 2009 19:48:21 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.jmsclient.impl;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.orca.orcabridge.IMessageFacade;
import com.ubs.orca.orcabridge.jmsclient.IJmsClient;
import com.ubs.orca.orcabridge.jmsclient.IJmsClientSingleMsgCallback;
import com.ubs.orca.orcabridge.jmsclient.JmsClientException;

/**
 * @author suggitpe
 * @version 1.0 29 Sep 2009
 */
abstract class AbstractJmsClient implements IJmsClient
{

    private static final Log LOG = LogFactory.getLog( AbstractJmsClient.class );

    private Context mInitialContext_;
    private String mConnectionFactoryName_;
    private Object mSynchLock_ = new Object();
    private String mDestinationName_;

    private Connection mConnection_;
    private Destination mDestination_;

    AbstractJmsClient( Context aInitialContext, String aConnectionFactoryName,
                       String aDestinationName_ )
    {
        super();
        mInitialContext_ = aInitialContext;
        mConnectionFactoryName_ = aConnectionFactoryName;
        mDestinationName_ = aDestinationName_;
    }

    // ================ CONNECTION ================
    /**
     * @see com.ubs.orca.orcabridge.jmsclient.IJmsClient#connect()
     */
    @Override
    public void connect() throws JmsClientException
    {
        connect( null, null );
    }

    /**
     * @see com.ubs.orca.orcabridge.jmsclient.IJmsClient#connect(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public void connect( String aUsername, String aPassword ) throws JmsClientException
    {
        LOG.debug( "Starting JMS Client" );
        ConnectionFactory connectionFactory = null;

        try
        {
            connectionFactory = (ConnectionFactory) mInitialContext_.lookup( mConnectionFactoryName_ );
        }
        catch ( NamingException ne )
        {
            throw new JmsClientException( "Failed to perform JNDI lookup for JmsClient", ne );
        }

        synchronized ( mSynchLock_ )
        {
            if ( mConnection_ != null )
            {
                stopAndCloseConnection();
            }
            mConnection_ = createConnection( connectionFactory, aUsername, aPassword );
        }
    }

    private static Connection createConnection( ConnectionFactory aFactory, String aUsername,
                                                String aPassword ) throws JmsClientException
    {
        try
        {
            if ( aUsername != null && aUsername != "" )
            {
                return aFactory.createConnection( aUsername, aPassword );
            }
            return aFactory.createConnection();
        }
        catch ( JMSException je )
        {
            throw new JmsClientException( "Failed to create connection with JmsClient", je );
        }
    }

    // ================ DISCONNECTION ================
    /**
     * @see com.ubs.orca.orcabridge.jmsclient.IJmsClient#disconnect()
     */
    @Override
    public void disconnect() throws JmsClientException
    {
        LOG.debug( "Stopping JMS Client" );
        synchronized ( mSynchLock_ )
        {
            stopAndCloseConnection();
            mConnection_ = null;
        }
    }

    private void stopAndCloseConnection()
    {
        /*
         * don't try and throw exceptions from these catches, else you
         * can be safe to use the finally clause (potentially ends up
         * with two exceptions kicking around)
         */
        try
        {
            mConnection_.stop();
        }
        catch ( JMSException stopException )
        {
            LOG.error( "Error when attempting to stop the connection", stopException );
        }
        finally
        {
            try
            {
                mConnection_.close();
            }
            catch ( JMSException closeException )
            {
                LOG.error( "Error when attempting to close the connection", closeException );
            }
        }
    }

    // ================ SEND PROCESS ================
    /**
     * @see com.ubs.orca.orcabridge.jmsclient.IJmsClient#sendMessageToDestination(com.ubs.orca.orcabridge.MessageFacade)
     */
    public void sendMessageToDestination( IMessageFacade aMessageFacade ) throws JmsClientException
    {
        Session session = null;
        try
        {
            synchronized ( mSynchLock_ )
            {
                session = mConnection_.createSession( true, Session.CLIENT_ACKNOWLEDGE );
                createProducerAndSend( aMessageFacade, session );
                session.commit();
            }
        }
        catch ( JMSException je )
        {
            throw new JmsClientException( "Failed to create JMS session", je );
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
                    throw new JmsClientException( "Failed to close session", je );
                }
            }
        }
    }

    private void createProducerAndSend( IMessageFacade aMessageFacade, Session aSession )
                    throws JmsClientException
    {
        if ( mDestination_ == null )
        {
            mDestination_ = createDestination( mInitialContext_ );
        }

        MessageProducer producer = null;
        try
        {
            producer = aSession.createProducer( mDestination_ );
            sendMessage( aMessageFacade, aSession, producer );
        }
        catch ( JMSException je )
        {
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
                    throw new JmsClientException( "Failed to close out the message producer", je );
                }
            }
        }
    }

    private Destination createDestination( Context aInitialContext ) throws JmsClientException
    {
        try
        {
            return (Destination) aInitialContext.lookup( mDestinationName_ );
        }
        catch ( NamingException ne )
        {
            throw new JmsClientException();
        }
    }

    private void sendMessage( IMessageFacade aMessageFacade, Session aSession,
                              MessageProducer aMessageProducer ) throws JmsClientException
    {
        try
        {
            Message msg = aMessageFacade.buildJmsMessage( aSession );
            aMessageProducer.send( msg );
        }
        catch ( JMSException je )
        {
            throw new JmsClientException( "Failed to create and send JMS message", je );
        }
    }

    // ================ LISTENER PROCESS ================
    public void startDurableSubscription( IJmsClientSingleMsgCallback aCallback )
                    throws JmsClientException
    {}

    public void stopDurbleSubscription() throws JmsClientException

    {}
}
