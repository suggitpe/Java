/*
 * Connection.java created on 20 Jan 2009 19:41:12 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connection.impl;

import org.suggs.apps.mercury.model.connection.ConnectionDetails;
import org.suggs.apps.mercury.model.connection.connection.IConnection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * TODO Write javadoc for Connection
 * 
 * @author suggitpe
 * @version 1.0 20 Jan 2009
 */
public class Connection implements IConnection
{

    private static final Log LOG = LogFactory.getLog( Connection.class );
    private ConnectionDetails mDetails_;

    /**
     * Constructs a new instance.
     */
    public Connection( ConnectionDetails aDetails )
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

}
