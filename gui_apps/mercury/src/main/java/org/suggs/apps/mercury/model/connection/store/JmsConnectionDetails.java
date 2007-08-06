/*
 * JmsConnectionDetails.java created on 28 Jun 2007 06:07:29 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury.model.connection.store;

import org.suggs.apps.mercury.model.connection.EConnectionType;
import org.suggs.apps.mercury.model.connection.IJmsConnectionDetails;

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
     * @param aServer
     *            the address of the server
     * @param aPort
     *            the port number for the server
     */
    public JmsConnectionDetails( String aName, EConnectionType aType, String aServer, String aPort )
    {
        mName_ = aName;
        mType_ = aType;
        mHostName_ = aServer;
        mPort_ = aPort;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return new StringBuffer( "JmsConnectionDetails: name=[" ).append( mName_ )
            .append( "], type=[" )
            .append( mType_.name() )
            .append( "], hostname=[" )
            .append( mHostName_ )
            .append( "], port=[" )
            .append( mPort_ )
            .append( "]" )
            .toString();
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
     * @see org.suggs.apps.mercury.model.connection.IJmsConnectionDetails#getConnectionName()
     */
    public String getConnectionName()
    {
        return mName_;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IJmsConnectionDetails#setConnectionName(java.lang.String)
     */
    public void setConnectionName( String aName )
    {
        mName_ = aName;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IJmsConnectionDetails#getConnectionType()
     */
    public EConnectionType getConnectionType()
    {
        return mType_;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IJmsConnectionDetails#getConnectionHostname()
     */
    public String getConnectionHostname()
    {
        return mHostName_;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IJmsConnectionDetails#getConnectionPort()
     */
    public String getConnectionPort()
    {
        return mPort_;
    }

}
