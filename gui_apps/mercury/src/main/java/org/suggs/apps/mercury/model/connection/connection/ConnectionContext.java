/*
 * ConnectionContext.java created on 20 Jan 2009 19:41:12 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connection;

import org.suggs.apps.mercury.model.adapters.ConnectionAdapterFactory;
import org.suggs.apps.mercury.model.adapters.IConnectionAdapter;
import org.suggs.apps.mercury.model.connection.ConnectionDetails;
import org.suggs.apps.mercury.model.connection.connection.statemachine.ConnectedConnectionState;
import org.suggs.apps.mercury.model.connection.connection.statemachine.DisconnectedConnectionState;
import org.suggs.apps.mercury.model.connection.connection.statemachine.FailedConnectionState;
import org.suggs.apps.mercury.model.connection.connection.statemachine.IConnectionState;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This implementation looks after the state transitions for the
 * connections. This class will fully delegate all of the connection
 * and disconnection to the state machine and does not expose its
 * underlying connection approach to th wider API.
 * 
 * @author suggitpe
 * @version 1.0 20 Jan 2009
 */
public class ConnectionContext implements IConnection
{

    private static final Log LOG = LogFactory.getLog( ConnectionContext.class );
    private ConnectionDetails mDetails_;
    private IConnectionAdapter mAdapter_;

    private final IConnectionState mConnectedState_ = new ConnectedConnectionState( this );
    private final IConnectionState mDisconnectedState_ = new DisconnectedConnectionState( this );
    private final IConnectionState mFailedState = new FailedConnectionState( this );
    private IConnectionState mCurrentState_ = mDisconnectedState_;

    {
        // initialise the adapter
        mAdapter_ = ConnectionAdapterFactory.createAdapter( IConnectionAdapter.CONNECTION_TYPE.valueOf( mDetails_.getType() ) );
    }

    /**
     * Constructs a new instance.
     */
    public ConnectionContext( ConnectionDetails aDetails )
    {
        mDetails_ = aDetails;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connection.IConnection#getConnectionDetails()
     */
    public ConnectionDetails getConnectionDetails()
    {
        return mDetails_;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connection.IConnection#connect()
     */
    public void connect()
    {
        if ( mCurrentState_.connect() )
        {
        }
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connection.IConnection#disconnect()
     */
    public void disconnect()
    {
        if ( mCurrentState_.disconnect() )
        {
        }
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connection.IConnection#getConnectionStatus()
     */
    public String getConnectionStatus()
    {
        return mCurrentState_.getState();
    }

    /**
     * Helper method that allows the states themselves to manage
     * transitions to the connected state.
     */
    public void setToConnectedState()
    {
        mCurrentState_ = mConnectedState_;
    }

    /**
     * Helper method that allows the states themselves to manage
     * transitions to the disconnected state.
     */
    public void setToDisconnectedState()
    {
        mCurrentState_ = mDisconnectedState_;
    }

    /**
     * Helper method that allows the states themselves to manage
     * transitions to the disconnected state.
     */
    public void setToFailedState()
    {
        mCurrentState_ = mFailedState;
    }
}
