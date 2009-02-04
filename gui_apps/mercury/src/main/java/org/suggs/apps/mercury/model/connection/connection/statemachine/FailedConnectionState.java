/*
 * ConnectedConnectionState.java created on 1 Feb 2009 02:23:16 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connection.statemachine;

import org.suggs.apps.mercury.model.connection.connection.ConnectionContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * State to represent when we try and make a connection but the
 * underlying connection attempt fails.
 * 
 * @author suggitpe
 * @version 1.0 1 Feb 2009
 */
public class FailedConnectionState implements IConnectionState
{

    private static final Log LOG = LogFactory.getLog( FailedConnectionState.class );
    private ConnectionContext mContext_;

    /**
     * Constructs a new instance.
     */
    public FailedConnectionState( ConnectionContext aContext )
    {
        mContext_ = aContext;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connection.statemachine.IConnectionState#connectTransition()
     */
    public boolean connect()
    {
        mContext_.setToConnectedState();
        return true;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connection.statemachine.IConnectionState#disconnectTransition()
     */
    public boolean disconnect()
    {
        LOG.info( "Connection previously failed to connect" );
        return false;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connection.statemachine.IConnectionState#getState()
     */
    public String getState()
    {
        return CONNECTION_STATE_NAME.FAILED.toString();
    }

}
