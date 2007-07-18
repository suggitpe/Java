/*
 * JmsConnectionManager.java created on 22 Jun 2007 08:18:48 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.gui.jms.model.connection.impl;

import java.util.Observable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.suggs.gui.jms.JmsHelperException;
import org.suggs.gui.jms.model.connection.EConnectionState;
import org.suggs.gui.jms.model.connection.IJmsConnectionDetails;
import org.suggs.gui.jms.model.connection.IJmsConnectionManager;

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
     * @see org.suggs.gui.jms.model.connection.IJmsConnectionManager#getConnectionState()
     */
    public EConnectionState getConnectionState()
    {
        return mConnectionState_;
    }

    /**
     * @see org.suggs.gui.jms.model.connection.IJmsConnectionManager#connect(org.suggs.gui.jms.model.connection.IJmsConnectionDetails)
     */
    public void connect( IJmsConnectionDetails aConnDetails ) throws JmsHelperException
    {
        mConnectionState_ = EConnectionState.CONNECTED;
        LOG.warn( "Connection not implemented" );
    }

    /**
     * @see org.suggs.gui.jms.model.connection.IJmsConnectionManager#testConnection(org.suggs.gui.jms.model.connection.IJmsConnectionDetails)
     */
    public boolean testConnection( IJmsConnectionDetails aConnectionDetails )
    {
        mConnectionState_ = EConnectionState.DISCONNECTED;
        return false;
    }

    /**
     * @see org.suggs.gui.jms.model.connection.IJmsConnectionManager#disconnect()
     */
    public void disconnect() throws JmsHelperException
    {
        mConnectionState_ = EConnectionState.DISCONNECTED;
    }

}
