/*
 * JmsConnectionDetails.java created on 28 Jun 2007 06:07:29 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury.model.connection;

import java.util.HashMap;
import java.util.Map;

/**
 * A bean to represent the connection details.
 * 
 * @author suggitpe
 * @version 1.0 28 Jun 2007
 */
public class ConnectionDetails
{

    public static final String META_CHANNEL = "ChannelName";

    private String mName_;
    private String mType_;
    private String mHostName_;
    private int mPort_ = 0;
    private boolean mIsSecurityEnabled_;
    private String mUsername_;
    private String mPassword_;
    private Map<String, String> mConnectionData_ = new HashMap<String, String>();

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
     * @param aConnData
     */
    public ConnectionDetails( String aName, String aType, String aHostname, int aPort,
                              Map<String, String> aConnData )
    {
        mName_ = aName;
        mType_ = aType;
        mHostName_ = aHostname;
        mPort_ = aPort;
        mConnectionData_ = aConnData;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( mConnectionData_ == null ) ? 0 : mConnectionData_.hashCode() );
        result = prime * result + ( ( mHostName_ == null ) ? 0 : mHostName_.hashCode() );
        result = prime * result + ( mIsSecurityEnabled_ ? 1231 : 1237 );
        result = prime * result + ( ( mName_ == null ) ? 0 : mName_.hashCode() );
        result = prime * result + ( ( mPassword_ == null ) ? 0 : mPassword_.hashCode() );
        result = prime * result + mPort_;
        result = prime * result + ( ( mType_ == null ) ? 0 : mType_.hashCode() );
        result = prime * result + ( ( mUsername_ == null ) ? 0 : mUsername_.hashCode() );
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        ConnectionDetails other = (ConnectionDetails) obj;
        if ( mConnectionData_ == null )
        {
            if ( other.mConnectionData_ != null )
                return false;
        }
        else if ( !mConnectionData_.equals( other.mConnectionData_ ) )
            return false;
        if ( mHostName_ == null )
        {
            if ( other.mHostName_ != null )
                return false;
        }
        else if ( !mHostName_.equals( other.mHostName_ ) )
            return false;
        if ( mIsSecurityEnabled_ != other.mIsSecurityEnabled_ )
            return false;
        if ( mName_ == null )
        {
            if ( other.mName_ != null )
                return false;
        }
        else if ( !mName_.equals( other.mName_ ) )
            return false;
        if ( mPassword_ == null )
        {
            if ( other.mPassword_ != null )
                return false;
        }
        else if ( !mPassword_.equals( other.mPassword_ ) )
            return false;
        if ( mPort_ != other.mPort_ )
            return false;
        if ( mType_ == null )
        {
            if ( other.mType_ != null )
                return false;
        }
        else if ( !mType_.equals( other.mType_ ) )
            return false;
        if ( mUsername_ == null )
        {
            if ( other.mUsername_ != null )
                return false;
        }
        else if ( !mUsername_.equals( other.mUsername_ ) )
            return false;
        return true;
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
        for ( String s : mConnectionData_.keySet() )
        {
            buff.append( "], " ).append( s ).append( "=[" ).append( mConnectionData_.get( s ) );
        }
        buff.append( "]" );

        buff.append( ", securityEnabled=[" ).append( mIsSecurityEnabled_ ).append( "]" );

        if ( mIsSecurityEnabled_ )
        {
            buff.append( ", username=[" ).append( mUsername_ ).append( "], password=[*****]" );
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
     * This is a convenience method that allows you to set up the
     * security details in one fell swoop
     * 
     * @param username
     *            the username to use
     * @param password
     *            the password to use
     */
    public void setSecurityDetails( String username, String password )
    {
        mIsSecurityEnabled_ = true;
        mUsername_ = username;
        mPassword_ = password;
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
     * Getter for the security enabled flag
     * 
     * @return true if the security is enabled, else false
     */
    public boolean isSecurityEnabled()
    {
        return mIsSecurityEnabled_;
    }

    /**
     * Sets the security enabled flag
     */
    public void setSecurityEnabled()
    {
        mIsSecurityEnabled_ = true;
    }

    /**
     * Unsets the security enabled flag
     */
    public void unsetSecurityEnabled()
    {
        mIsSecurityEnabled_ = false;
    }

    /**
     * Getter for the username
     * 
     * @return the username value
     */
    public String getUsername()
    {
        return mUsername_;
    }

    /**
     * Setter for the username
     * 
     * @param aUsername
     *            the username to set
     */
    public void setUsername( String aUsername )
    {
        mUsername_ = aUsername;
    }

    /**
     * Getter for the password
     * 
     * @return the password value
     */
    public String getPassword()
    {
        return mPassword_;
    }

    /**
     * Setter for the password field
     * 
     * @param aPassword
     *            the password to set
     */
    public void setPassword( String aPassword )
    {
        mPassword_ = aPassword;
    }

    /**
     * Getter for the metadata associated with the connection
     * 
     * @return the metadata map
     */
    public Map<String, String> getConnectionData()
    {
        return mConnectionData_;
    }

    /**
     * Setter for the connection metadata
     * 
     * @param aMap
     *            the map of metadata to set
     */
    public void setConnectionData( Map<String, String> aMap )
    {
        mConnectionData_ = aMap;
    }

    /**
     * Adds a new key value pair to the connection store data
     * 
     * @param key
     *            the key
     * @param value
     *            the value
     * @throws ConnectionDataException
     *             if the key already exists
     */
    public void addConnectionDataItem( String key, String value ) throws ConnectionDataException
    {
        if ( mConnectionData_.containsKey( key ) )
        {
            throw new ConnectionDataException( "Key [" + key + "] already exists in meta data" );
        }

        mConnectionData_.put( key, value );
    }

}
