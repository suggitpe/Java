/*
 * JmsConnectionDetails.java created on 28 Jun 2007 06:07:29 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury.model.connection.store;

import org.suggs.apps.mercury.model.connection.EConnectionType;
import org.suggs.apps.mercury.model.connection.IJmsConnectionDetails;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation bean of the connection details interface
 * 
 * @author suggitpe
 * @version 1.0 28 Jun 2007
 */
public class JmsConnectionDetails implements IJmsConnectionDetails
{

    private String mName_;
    private EConnectionType mType_;
    private String mHostName_;
    private String mPort_;
    private Map<String, String> mMetaData_ = new HashMap();

    /**
     * Constructs a new instance.
     * 
     * @param aName
     */
    public JmsConnectionDetails( String aName )
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
     * @param aHostname
     *            the address of the server
     * @param aPort
     *            the port number for the server
     */
    public JmsConnectionDetails( String aName, EConnectionType aType, String aHostname, String aPort )
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
    public JmsConnectionDetails( String aName, EConnectionType aType, String aHostname, String aPort, Map<String, String> aMetaData )
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
     * @see org.suggs.apps.mercury.model.connection.IJmsConnectionDetails#isConnectionDetailsValid()
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
     * @see org.suggs.apps.mercury.model.connection.IJmsConnectionDetails#getName()
     */
    public String getName()
    {
        return mName_;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IJmsConnectionDetails#setName(java.lang.String)
     */
    public void setName( String aName )
    {
        mName_ = aName;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IJmsConnectionDetails#getType()
     */
    public EConnectionType getType()
    {
        return mType_;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IJmsConnectionDetails#getHostname()
     */
    public String getHostname()
    {
        return mHostName_;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IJmsConnectionDetails#getPort()
     */
    public String getPort()
    {
        return mPort_;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IJmsConnectionDetails#getMetaData()
     */
    public Map<String, String> getMetaData()
    {
        return mMetaData_;
    }

}
