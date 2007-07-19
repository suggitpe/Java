/*
 * JmsConnectionManager.java created on 22 Jun 2007 08:18:48 by suggitpe for project GUI - JmsHelper
 * 
 */
package com.suggs.gui.jms.model.connection.impl;

import com.suggs.gui.jms.JmsHelperException;
import com.suggs.gui.jms.model.connection.EConnectionState;
import com.suggs.gui.jms.model.connection.IJmsConnectionDetails;
import com.suggs.gui.jms.model.connection.IJmsConnectionManager;

import java.util.Observable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Implementation of the jms connection manager interface
 * 
 * @author suggitpe
 * @version 1.0 22 Jun 2007
 */
public class JmsConnectionManager extends Observable implements IJmsConnectionManager
{

    private static final Log LOG = LogFactory.getLog( JmsConnectionManager.class );

    private EConnectionState mConnectionState_ = EConnectionState.INITIAL;

    /**
     * Constructs a new instance.
     */
    public JmsConnectionManager()
    {
        super();
    }

    /**
     * @see com.suggs.gui.jms.model.connection.IJmsConnectionManager#getConnectionState()
     */
    public EConnectionState getConnectionState()
    {
        return mConnectionState_;
    }

    /**
     * @see com.suggs.gui.jms.model.connection.IJmsConnectionManager#connect(com.suggs.gui.jms.model.connection.IJmsConnectionDetails)
     */
    public void connect( IJmsConnectionDetails aConnDetails ) throws JmsHelperException
    {
        mConnectionState_ = EConnectionState.CONNECTED;
        LOG.warn( "Connection not implemented" );
    }

    /**
     * @see com.suggs.gui.jms.model.connection.IJmsConnectionManager#testConnection(com.suggs.gui.jms.model.connection.IJmsConnectionDetails)
     */
    public boolean testConnection( IJmsConnectionDetails aConnectionDetails )
    {
        mConnectionState_ = EConnectionState.DISCONNECTED;
        return false;
    }

    /**
     * @see com.suggs.gui.jms.model.connection.IJmsConnectionManager#disconnect()
     */
    public void disconnect() throws JmsHelperException
    {
        mConnectionState_ = EConnectionState.DISCONNECTED;
    }

}
