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

    private Context mInitialContext_;
    private String mConnectionFactoryName_;
    private String mDestinationName_;

    private Connection mConnection_;
    private Destination mDestination_;

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception
    {
        Assert.notNull( mInitialContext_, "Must set the initial context on the JMS client core" );
        Assert.notNull( mConnectionFactoryName_,
                        "Must set the connection factory name on the JMS client core" );
        Assert.notNull( mDestinationName_, "Must set the destination name on the JMS client core" );
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
        LOG.debug( "Starting JMS Client" );
        ConnectionFactory connectionFactory = null;

        try
        {
            connectionFactory = (ConnectionFactory) mInitialContext_.lookup( mConnectionFactoryName_ );
            mDestination_ = (Destination) mInitialContext_.lookup( mDestinationName_ );
        }
        catch ( NamingException ne )
        {
            throw new JmsClientException( "Failed to perform JNDI lookup for JmsClient", ne );
        }

        if ( mConnection_ != null )
        {
            stopAndCloseConnection();
        }
        mConnection_ = createConnection( connectionFactory, aUsername, aPassword );
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
        LOG.debug( "Stopping JMS Client" );
        stopAndCloseConnection();
        mConnection_ = null;
    }

    private void stopAndCloseConnection() throws JmsClientException
    {
        try
        {
            mConnection_.stop();
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
                mConnection_.close();
            }
            catch ( JMSException closeException )
            {
                LOG.error( "Error when attempting to close the connection", closeException );
            }
        }
    }

    /**
     * @see com.ubs.orca.orcabridge.jmsclient.IJmsClient#processInTransaction(com.ubs.orca.orcabridge.jmsclient.IJmsAction)
     */
    @Override
    public void processInTransaction( IJmsAction aAction ) throws JmsClientException
    {
        Session session = null;
        try
        {
            LOG.info( "Creating transaction for processing ..." );
            if ( mConnection_ == null )
            {
                throw new JmsClientException( "No active connection, you must connect before you send" );
            }

            session = mConnection_.createSession( true, Session.CLIENT_ACKNOWLEDGE );

            // watch out for this one ... ideally this is done later
            // in the flow. This starts the message flow from the
            // connection.
            mConnection_.start();
            aAction.action( session, mDestination_ );

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

    /**
     * Sets the initialContext field to the specified value.
     * 
     * @param aInitialContext
     *            The initialContext to set.
     */
    public void setInitialContext( Context aInitialContext )
    {
        mInitialContext_ = aInitialContext;
    }

    /**
     * Sets the connectionFactoryName field to the specified value.
     * 
     * @param aConnectionFactoryName
     *            The connectionFactoryName to set.
     */
    public void setConnectionFactoryName( String aConnectionFactoryName )
    {
        mConnectionFactoryName_ = aConnectionFactoryName;
    }

    /**
     * Sets the destinationName field to the specified value.
     * 
     * @param aDestinationName
     *            The destinationName to set.
     */
    public void setDestinationName( String aDestinationName )
    {
        mDestinationName_ = aDestinationName;
    }

}
