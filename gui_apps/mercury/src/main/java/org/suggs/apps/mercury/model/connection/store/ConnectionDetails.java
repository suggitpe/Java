/*
 * JmsConnectionDetails.java created on 28 Jun 2007 06:07:29 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury.model.connection.store;

import org.suggs.apps.mercury.model.connection.EConnectionType;
import org.suggs.apps.mercury.model.connection.IConnectionDetails;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Implementation bean of the connection details interface
 * 
 * @author suggitpe
 * @version 1.0 28 Jun 2007
 */
public class ConnectionDetails implements IConnectionDetails
{

    private String mName_;
    private EConnectionType mType_;
    private String mHostName_;
    private String mPort_;
    private Map<String, String> mMetaData_ = new HashMap<String, String>();
    private Map<String, Set<String>> mConnectionFactories_ = new HashMap<String, Set<String>>();
    private Map<String, Set<String>> mDestinations_ = new HashMap<String, Set<String>>();

    /**
     * Constructs a new instance.
     * 
     * @param aName
     */
    public ConnectionDetails( String aName )
    {
        mName_ = aName;
    }

    /**
     * Constructs a new instance.
     * 
     * @param aName
     *            the name of the connection
     * @param aType
     *            the type of the connection
     */
    public ConnectionDetails( String aName, EConnectionType aType )
    {
        mName_ = aName;
        mType_ = aType;
    }

    /**
     * Constructs a new instance.
     * 
     * @param aName
     *            the name of the connection
     * @param aType
     *            the type of the connection
     * @param aHostname
     *            the address of the server
     * @param aPort
     *            the port number for the server
     */
    public ConnectionDetails( String aName, EConnectionType aType, String aHostname, String aPort )
    {
        mName_ = aName;
        mType_ = aType;
        mHostName_ = aHostname;
        mPort_ = aPort;
    }

    /**
     * Constructs a new instance.
     * 
     * @param aName
     *            the name of the connection
     * @param aType
     *            the connection type
     * @param aHostname
     *            the name of the host for the broker
     * @param aPort
     *            the port number to access the broker
     * @param aMetaData
     *            additional connection metadata
     */
    public ConnectionDetails( String aName, EConnectionType aType, String aHostname, String aPort, Map<String, String> aMetaData )
    {
        mName_ = aName;
        mType_ = aType;
        mHostName_ = aHostname;
        mPort_ = aPort;
        mMetaData_ = aMetaData;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuffer buff = new StringBuffer( "JmsConnectionDetails: name=[" ).append( mName_ )
            .append( "], type=[" )
            .append( mType_.name() )
            .append( "], hostname=[" )
            .append( mHostName_ )
            .append( "], port=[" )
            .append( mPort_ );
        for ( String s : mMetaData_.keySet() )
        {
            buff.append( "], " ).append( s ).append( "=[" ).append( mMetaData_.get( s ) );
        }

        buff.append( "]" );
        return buff.toString();
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IConnectionDetails#isConnectionDetailsValid()
     */
    public boolean isConnectionDetailsValid()
    {
        boolean ret = true;
        if ( mName_ == null || mName_.length() < 1 )
        {
            ret = false;
        }
        else if ( mType_ == null )
        {
            ret = false;
        }
        else if ( mHostName_ == null || mHostName_.length() < 1 )
        {
            ret = false;
        }
        else if ( mPort_ == null || mPort_.length() < 1 )
        {
            ret = false;
        }
        return ret;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IConnectionDetails#getName()
     */
    public String getName()
    {
        return mName_;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IConnectionDetails#setName(java.lang.String)
     */
    public void setName( String aName )
    {
        mName_ = aName;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IConnectionDetails#getType()
     */
    public EConnectionType getType()
    {
        return mType_;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IConnectionDetails#getHostname()
     */
    public String getHostname()
    {
        return mHostName_;
    }

    /**
     * Setter for the host name
     * 
     * @param aHost
     *            the host to set
     */
    public void setHostname( String aHost )
    {
        mHostName_ = aHost;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IConnectionDetails#getPort()
     */
    public String getPort()
    {
        return mPort_;
    }

    /**
     * Setter for the port number
     * 
     * @param aPort
     *            the port number to set
     */
    public void setPort( String aPort )
    {
        mPort_ = aPort;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IConnectionDetails#getMetaData()
     */
    public Map<String, String> getMetaData()
    {
        return mMetaData_;
    }

    /**
     * Setter for the connection metadata
     * 
     * @param aMap
     *            the map of metadata to set
     */
    public void setMetaData( Map<String, String> aMap )
    {
        mMetaData_ = aMap;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IConnectionDetails#getConnectionFactories()
     */
    public Map<String, Set<String>> getConnectionFactories()
    {
        return mConnectionFactories_;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IConnectionDetails#setConnectionFactories(java.util.Map)
     */
    public void setConnectionFactories( Map<String, Set<String>> aMap )
    {
        mConnectionFactories_ = aMap;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IConnectionDetails#getDestinations()
     */
    public Map<String, Set<String>> getDestinations()
    {
        return mDestinations_;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IConnectionDetails#setDestinations(java.util.Map)
     */
    public void setDestinations( Map<String, Set<String>> aMap )
    {
        mDestinations_ = aMap;
    }

}
