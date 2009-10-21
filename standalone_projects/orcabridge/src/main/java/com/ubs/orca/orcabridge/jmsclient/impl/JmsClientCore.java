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

import com.ubs.orca.orcabridge.jmsclient.IJmsClient;
import com.ubs.orca.orcabridge.jmsclient.IJmsProcessCallback;
import com.ubs.orca.orcabridge.jmsclient.JmsClientException;

/**
 * This class represents the core JMS processing logic for the JMS
 * clients.
 * 
 * @author suggitpe
 * @version 1.0 29 Sep 2009
 */
class JmsClientCore implements IJmsClient
{

    private static final Log LOG = LogFactory.getLog( JmsClientCore.class );

    private Context mInitialContext_;
    private String mConnectionFactoryName_;
    private String mDestinationName_;

    private Object mSynchLock_ = new Object();

    private Connection mConnection_;
    private Destination mDestination_;

    JmsClientCore( Context aInitialContext, String aConnectionFactoryName, String aDestinationName )
    {
        super();
        mInitialContext_ = aInitialContext;
        mConnectionFactoryName_ = aConnectionFactoryName;
        mDestinationName_ = aDestinationName;
    }

    // ================ CONNECTION ================
    /**
     * @see com.ubs.orca.orcabridge.jmsclient.old.IJmsClient#connect()
     */
    @Override
    public void connect() throws JmsClientException
    {
        connect( null, null );
    }

    /**
     * @see com.ubs.orca.orcabridge.jmsclient.old.IJmsClient#connect(java.lang.String,
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

    // ================ DISCONNECTION ================
    /**
     * @see com.ubs.orca.orcabridge.jmsclient.old.IJmsClient#disconnect()
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
     * Perform the JMS specific processing as required
     * 
     * @param aCallback
     *            a callback that will implement the required
     *            processing.
     * @throws JmsClientException
     */
    public void processInTransaction( IJmsProcessCallback aCallback ) throws JmsClientException
    {
        Session session = null;
        try
        {
            LOG.info( "Creating transaction for processing ..." );
            Connection conn = getConnection();
            if ( conn == null )
            {
                throw new JmsClientException( "No active connection, you must connect before you send" );
            }

            session = getConnection().createSession( true, Session.CLIENT_ACKNOWLEDGE );

            aCallback.processInTransaction( session, mDestination_ );

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
     * Getter for the connection
     * 
     * @return the connection
     */
    Connection getConnection()
    {
        return mConnection_;
    }

    /**
     * This is only used for the unit tests, do not change the scope
     * 
     * @param aConnection
     */
    void setConnection( Connection aConnection )
    {
        mConnection_ = aConnection;
    }

    /**
     * Getter for the initial context
     * 
     * @return the initial context
     * @throws JmsClientException
     *             if the initial context has not been initialised
     */
    protected Context getInitialContext() throws JmsClientException
    {
        if ( mInitialContext_ == null )
        {
            throw new JmsClientException( "Initial context has not been initialised" );
        }
        return mInitialContext_;
    }

    /**
     * Getter for the destination
     * 
     * @return the destination
     * @throws JmsClientException
     *             if the destination has not been initialised
     */
    protected Destination getDestination() throws JmsClientException
    {
        if ( mDestination_ == null )
        {
            throw new JmsClientException( "Destination has not been initialised" );
        }
        return mDestination_;
    }

    /**
     * This is here to support the unit tests only
     * 
     * @param aDestination
     */
    void setDestination( Destination aDestination )
    {
        mDestination_ = aDestination;
    }

    /**
     * Used by child classes to synchronise mutexes within the code.
     * 
     * @return the synch lock
     */
    protected Object getSynchLock()
    {
        return mSynchLock_;
    }
}
