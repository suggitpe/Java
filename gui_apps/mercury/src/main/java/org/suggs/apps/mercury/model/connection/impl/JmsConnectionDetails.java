/*
 * JmsConnectionDetails.java created on 28 Jun 2007 06:07:29 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury.model.connection.impl;

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
    private String mServer_;
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
        mServer_ = aServer;
        mPort_ = aPort;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IJmsConnectionDetails#getConnectionName()
     */
    public String getConnectionName()
    {
        return mName_;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IJmsConnectionDetails#getConnectionType()
     */
    public EConnectionType getConnectionType()
    {
        return mType_;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IJmsConnectionDetails#getConnectionServer()
     */
    public String getConnectionServer()
    {
        return mServer_;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IJmsConnectionDetails#getConnectionPort()
     */
    public String getConnectionPort()
    {
        return mPort_;
    }

}
