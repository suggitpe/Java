/*
 * JmsClient.java created on 29 Sep 2009 19:48:21 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.jmsclient.impl;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.orca.orcabridge.jmsclient.IJmsAction;
import com.ubs.orca.orcabridge.jmsclient.IJmsClient;
import com.ubs.orca.orcabridge.jmsclient.JmsClientException;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * This class represents the core JMS processing logic for the JMS
 * clients.
 * 
 * @author suggitpe
 * @version 1.0 29 Sep 2009
 */
public class JmsClientCore implements IJmsClient, InitializingBean
{

    private static final Log LOG = LogFactory.getLog( JmsClientCore.class );

    private Context initialContext_;
    private String connectionFactoryName_;
    private String destinationName_;

    private Connection connection_;
    private Destination destination_;

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception
    {
        Assert.notNull( initialContext_, "Must set the initial context on the JMS client core" );
        Assert.notNull( connectionFactoryName_,
                        "Must set the connection factory name on the JMS client core" );
        Assert.notNull( destinationName_, "Must set the destination name on the JMS client core" );
    }

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
        if ( LOG.isDebugEnabled() )
        {
            LOG.debug( "Connecting to JMS client with username=["
                       + ( aUsername == null ? "no credentials" : aUsername ) + "]" );
        }
        if ( connection_ != null )
        {
            stopAndCloseConnection();
        }

        LOG.debug( "Starting JMS Client" );
        ConnectionFactory connectionFactory = null;

        try
        {
            connectionFactory = (ConnectionFactory) initialContext_.lookup( connectionFactoryName_ );
            destination_ = (Destination) initialContext_.lookup( destinationName_ );
        }
        catch ( NamingException ne )
        {
            throw new JmsClientException( "Failed to perform JNDI lookup for JmsClient", ne );
        }

        if ( LOG.isDebugEnabled() )
        {
            LOG.debug( "Creating connection to JMS Client using [" + connectionFactory + "]" );
        }
        connection_ = createConnection( connectionFactory, aUsername, aPassword );
        LOG.info( "Connection established" );
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

    /**
     * @see com.ubs.orca.orcabridge.jmsclient.IJmsClient#disconnect()
     */
    @Override
    public void disconnect() throws JmsClientException
    {
        try
        {
            stopAndCloseConnection();
        }
        finally
        {
            // we always want to remove the connection when we call
            // disconnect, even if we get an exception coming up the
            // stack
            connection_ = null;
        }
    }

    private void stopAndCloseConnection() throws JmsClientException
    {
        LOG.debug( "Stopping JMS Client" );
        try
        {
            connection_.stop();
        }
        catch ( JMSException stopException )
        {
            throw new JmsClientException( "Error when attempting to stop the connection",
                                          stopException );
        }
        finally
        {
            try
            {
                connection_.close();
            }
            catch ( JMSException closeException )
            {
                LOG.error( "Error when attempting to close the connection", closeException );
            }
        }
    }

    /**
     * @see com.ubs.orca.orcabridge.jmsclient.IJmsClient#processAction(com.ubs.orca.orcabridge.jmsclient.IJmsAction)
     */
    @Override
    public void processAction( IJmsAction aAction ) throws JmsClientException
    {
        if ( connection_ == null )
        {
            throw new JmsClientException( "No active connection, you must connect before you send" );
        }

        // here is where we could optimise by caching the session in
        // the object and then reusing the existing one rather than
        // creating a new one. This will allow for any actions that
        // are single process actions
        Session session = null;
        try
        {
            LOG.info( "Creating transaction for processing ..." );

            session = connection_.createSession( true, Session.CLIENT_ACKNOWLEDGE );

            // watch out for this one ... ideally this is done later
            // in the flow. This starts the message flow from the
            // connection.
            connection_.start();
            aAction.actionInTransaction( session, destination_ );

            LOG.info( "JMS Client completed execution of processing" );
        }
        catch ( JMSException je )
        {
            String err = "Failed to create JMS session";
            LOG.error( err );
            throw new JmsClientException( err, je );
        }
        finally
        {
            closeSession( session );
        }

    }

    private void closeSession( Session aSession )
    {
        if ( aSession != null )
        {
            try
            {
                aSession.close();
            }
            catch ( JMSException je )
            {
                LOG.error( "Failed to close session", je );
            }
        }
    }

    /**
     * Sets the initialContext field to the specified value.
     * 
     * @param aInitialContext
     *            The initialContext to set.
     */
    public void setInitialContext( Context aInitialContext )
    {
        initialContext_ = aInitialContext;
    }

    /**
     * Sets the connectionFactoryName field to the specified value.
     * 
     * @param aConnectionFactoryName
     *            The connectionFactoryName to set.
     */
    public void setConnectionFactoryName( String aConnectionFactoryName )
    {
        connectionFactoryName_ = aConnectionFactoryName;
    }

    /**
     * Sets the destinationName field to the specified value.
     * 
     * @param aDestinationName
     *            The destinationName to set.
     */
    public void setDestinationName( String aDestinationName )
    {
        destinationName_ = aDestinationName;
    }

    /**
     * This should only be used to enable tests
     * 
     * @param aConnection
     */
    protected void setConnection( Connection aConnection )
    {
        connection_ = aConnection;
    }

    /**
     * This should only be used to enable tests.
     * 
     * @param aDestination
     */
    protected void setDestination( Destination aDestination )
    {
        destination_ = aDestination;
    }

}
