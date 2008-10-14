/*
 * JmsConnectionDetails.java created on 28 Jun 2007 06:07:29 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury.model.connection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A bean to represent the connection details.
 * 
 * @author suggitpe
 * @version 1.0 28 Jun 2007
 */
public class ConnectionDetails
{

    private String mName_;
    private String mType_;
    private String mHostName_;
    private int mPort_ = 0;
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
    public ConnectionDetails( String aName, String aType )
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
    public ConnectionDetails( String aName, String aType, String aHostname, int aPort )
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
    public ConnectionDetails( String aName, String aType, String aHostname, int aPort,
                              Map<String, String> aMetaData )
    {
        mName_ = aName;
        mType_ = aType;
        mHostName_ = aHostname;
        mPort_ = aPort;
        mMetaData_ = aMetaData;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object other )
    {
        if ( this == other )
        {
            return true;
        }

        if ( other != null && getClass() == other.getClass() )
        {
            boolean ret = true;
            ConnectionDetails rhs = (ConnectionDetails) other;
            if ( mName_.equals( rhs.mName_ ) && mType_.equals( rhs.mType_ )
                 && mHostName_.equals( rhs.mHostName_ ) && mPort_ == rhs.mPort_
                 && mConnectionFactories_.size() == rhs.mConnectionFactories_.size()
                 && mDestinations_.size() == rhs.mDestinations_.size() )
            {
                for ( String s : mConnectionFactories_.keySet() )
                {
                    if ( !mConnectionFactories_.get( s )
                        .equals( rhs.mConnectionFactories_.get( s ) ) )
                    {
                        ret = false;
                    }
                }

                for ( String s : mDestinations_.keySet() )
                {
                    if ( !mDestinations_.get( s ).equals( rhs.mDestinations_.get( s ) ) )
                    {
                        ret = false;
                    }
                }
                return ret;
            }
        }
        return false;

    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuffer buff = new StringBuffer( "JmsConnectionDetails: name=[" ).append( mName_ )
            .append( "], type=[" )
            .append( mType_ )
            .append( "], hostname=[" )
            .append( mHostName_ )
            .append( "], port=[" )
            .append( mPort_ );
        for ( String s : mMetaData_.keySet() )
        {
            buff.append( "], " ).append( s ).append( "=[" ).append( mMetaData_.get( s ) );
        }
        buff.append( "]" );

        buff.append( ", ConnectionFactories: " );
        for ( String s : mConnectionFactories_.keySet() )
        {
            buff.append( s ).append( "={" ).append( mConnectionFactories_.get( s ) ).append( "} " );
        }

        buff.append( ", Destinations:" );
        for ( String s : mDestinations_.keySet() )
        {
            buff.append( s ).append( "={" ).append( mDestinations_.get( s ) ).append( "} " );
        }
        return buff.toString();
    }

    /**
     * Self validation method. This method will look at the internal
     * details and will self validate itself.
     * 
     * @return true if the contents are valid, else false;
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
        else if ( mPort_ == 0 )
        {
            ret = false;
        }
        return ret;
    }

    /**
     * Getter for the connection name
     * 
     * @return the name of the connection
     */
    public String getName()
    {
        return mName_;
    }

    /**
     * Setter for the name of the connection
     * 
     * @param aName
     *            the name to set
     */
    public void setName( String aName )
    {
        mName_ = aName;
    }

    /**
     * Getter for the type of connection
     * 
     * @return the type of the connection
     */
    public String getType()
    {
        return mType_;
    }

    /**
     * Setter for the type
     * 
     * @param aType
     *            the type to set
     */
    public void setType( String aType )
    {
        mType_ = aType;
    }

    /**
     * Getter for the hostname
     * 
     * @return the hostname
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
     * Getter for the port number
     * 
     * @return the port number
     */
    public int getPort()
    {
        return mPort_;
    }

    /**
     * Setter for the port number
     * 
     * @param aPort
     *            the port number to set
     */
    public void setPort( int aPort )
    {
        mPort_ = aPort;
    }

    /**
     * Getter for the metadata associated with the connection
     * 
     * @return the metadata map
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
     * Getter for the connection factories
     * 
     * @return the connection factories
     */
    public Map<String, Set<String>> getConnectionFactories()
    {
        return mConnectionFactories_;
    }

    /**
     * @param aMap
     */
    public void setConnectionFactories( Map<String, Set<String>> aMap )
    {
        mConnectionFactories_ = aMap;
    }

    public Map<String, Set<String>> getDestinations()
    {
        return mDestinations_;
    }

    public void setDestinations( Map<String, Set<String>> aMap )
    {
        mDestinations_ = aMap;
    }

}
