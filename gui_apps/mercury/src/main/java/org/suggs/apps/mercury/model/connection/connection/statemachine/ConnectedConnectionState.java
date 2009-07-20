/*
 * ConnectedConnectionState.java created on 1 Feb 2009 02:23:16 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connection.statemachine;

import org.suggs.apps.mercury.model.connection.connection.ConnectionContext;

/**
 * State to represent when we try and make a connection and the
 * underlying connection attempt has succeeded.
 * 
 * @author suggitpe
 * @version 1.0 1 Feb 2009
 */
public class ConnectedConnectionState implements IConnectionState
{

    private ConnectionContext mContext_;

    /**
     * Constructs a new instance.
     * 
     * @param aContext
     */
    public ConnectedConnectionState( ConnectionContext aContext )
    {
        mContext_ = aContext;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connection.statemachine.IConnectionState#getState()
     */
    public String getState()
    {
        return CONNECTION_STATE_NAME.CONNECTED.toString();
    }

}
