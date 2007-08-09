/*
 * JmsConnectionManager.java created on 22 Jun 2007 08:18:48 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury.model.connection.manager;

import org.suggs.apps.mercury.model.connection.EConnectionState;
import org.suggs.apps.mercury.model.connection.IConnectionDetails;
import org.suggs.apps.mercury.model.connection.IConnectionManager;
import org.suggs.apps.mercury.model.connection.MercuryConnectionException;

import java.util.Map;
import java.util.Observable;
import java.util.Set;

import javax.jms.Connection;
import javax.jms.ConnectionMetaData;
import javax.jms.JMSException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * Implementation of the jms connection manager interface
 * 
 * @author suggitpe
 * @version 1.0 22 Jun 2007
 */
public class ConnectionManager extends Observable implements IConnectionManager, InitializingBean
{

    private static final Log LOG = LogFactory.getLog( ConnectionManager.class );

    private EConnectionState mConnectionState_ = EConnectionState.INITIAL;

    private Connection mConnection_;
    private Map<String, IConnectionAdapter> mAdapters_;

    /**
     * Constructs a new instance.
     */
    public ConnectionManager()
    {
        super();
    }

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception
    {
        Assert.notNull( mAdapters_, "Must inject a map of adapters into the connection manager" );
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IConnectionManager#getConnectionState()
     */
    public EConnectionState getConnectionState()
    {
        return mConnectionState_;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IConnectionManager#connect(org.suggs.apps.mercury.model.connection.IConnectionDetails,
     *      java.lang.String)
     */
    public void connect( IConnectionDetails aDetails, String aConnectionFactoryName ) throws MercuryConnectionException

    {
        mConnectionState_ = EConnectionState.CONNECTED;

        setChanged();
        notifyObservers();
    }

    /**
     * Get the connection metadata ... ie what is behind the
     * connection
     * 
     * @return the connection meta data
     * @throws MercuryConnectionException
     *             if there is no connection
     */
    public Map<String, Set<String>> getConnectionData() throws MercuryConnectionException
    {
        if ( mConnection_ == null )
        {
            throw new MercuryConnectionException( "No connection has been created.  You must connect to the broker first." );
        }

        Map<String, Set<String>> ret = null;
        try
        {
            ConnectionMetaData data = mConnection_.getMetaData();
            LOG.debug( "Meta data for connection is:\n" + data.toString() );
        }
        catch ( JMSException je )
        {
            throw new MercuryConnectionException( "Failed to collect connection metadata", je );
        }
        return ret;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IConnectionManager#disconnect()
     */
    public void disconnect() throws MercuryConnectionException
    {
        mConnectionState_ = EConnectionState.DISCONNECTED;
        setChanged();
        notifyObservers();
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IConnectionManager#testConnection(org.suggs.apps.mercury.model.connection.IConnectionDetails,
     *      java.lang.String)
     */
    public boolean testConnection( IConnectionDetails aDetails, String aConnectionFactoryName )
    {
        mConnectionState_ = EConnectionState.DISCONNECTED;
        return false;
    }

    /**
     * Getter for the adapters
     * 
     * @return the map of adapters
     */
    public Map<String, IConnectionAdapter> getAdapters()
    {
        return mAdapters_;
    }

    /**
     * setter for the adapters
     * 
     * @param aMap
     *            the map of adapters to set
     */
    public void setAdapters( Map<String, IConnectionAdapter> aMap )
    {
        mAdapters_ = aMap;
    }

}
