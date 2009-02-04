/*
 * DisconnectedConnectionState.java created on 1 Feb 2009 02:19:08 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connection.statemachine;

import org.suggs.apps.mercury.model.connection.connection.ConnectionContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * State to represent when we try and close an existing connection and
 * the underlying connection close attempt has succeeded.
 * 
 * @author suggitpe
 * @version 1.0 1 Feb 2009
 */
public class DisconnectedConnectionState implements IConnectionState
{

    private static final Log LOG = LogFactory.getLog( DisconnectedConnectionState.class );

    private ConnectionContext mContext_;

    /**
     * Constructs a new instance.
     */
    public DisconnectedConnectionState( ConnectionContext aContext )
    {
        mContext_ = aContext;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connection.statemachine.IConnectionState#connect()
     */
    public boolean connect()
    {
        mContext_.setToConnectedState();
        return true;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connection.statemachine.IConnectionState#disconnect()
     */
    public boolean disconnect()
    {
        LOG.info( "Connection already disconnected" );
        return false;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connection.statemachine.IConnectionState#getState()
     */
    public String getState()
    {
        return CONNECTION_STATE_NAME.DISCONNECTED.toString();
    }

}
