/*
 * ConnectionParameters.java created on 9 Aug 2007 19:40:28 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.manager;

import org.suggs.apps.mercury.model.connection.EConnectionType;
import org.suggs.apps.mercury.model.connection.IConnectionParameters;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of a connection parameters class.
 * 
 * @author suggitpe
 * @version 1.0 9 Aug 2007
 */
public class ConnectionParameters implements IConnectionParameters
{

    private EConnectionType mType_;
    private String mHostName_;
    private String mPort_;
    private Map<String, String> mMetaData_ = new HashMap<String, String>();
    private String mConnectionFactory_;

    /**
     * Constructs a new instance.
     */
    private ConnectionParameters()
    {
        super();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aType
     * @param aHostname
     * @param aPort
     * @param aMetadata
     * @param aConnectionFactory
     */
    public ConnectionParameters( EConnectionType aType, String aHostname, String aPort, Map<String, String> aMetadata,
                                 String aConnectionFactory )
    {
        super();
        mType_ = aType;
        mHostName_ = aHostname;
        mPort_ = aPort;
        mMetaData_ = aMetadata;
        mConnectionFactory_ = aConnectionFactory;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IConnectionParameters#getType()
     */
    public EConnectionType getType()
    {
        return mType_;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IConnectionParameters#getHostname()
     */
    public String getHostname()
    {
        return mHostName_;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IConnectionParameters#getPort()
     */
    public String getPort()
    {
        return mPort_;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IConnectionParameters#getMetaData()
     */
    public Map<String, String> getMetaData()
    {
        return mMetaData_;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IConnectionParameters#getConnectionFactory()
     */
    public String getConnectionFactory()
    {
        return mConnectionFactory_;
    }

}
