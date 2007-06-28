/*
 * JmsConnectionManager.java created on 22 Jun 2007 08:18:48 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.gui.connection.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.suggs.gui.connection.IJmsConnectionDetails;
import org.suggs.gui.connection.IJmsConnectionManager;

/**
 * Implementation of the jms connection manager interface
 * 
 * @author suggitpe
 * @version 1.0 22 Jun 2007
 */
public class JmsConnectionManager implements IJmsConnectionManager
{

    private static final Log LOG = LogFactory.getLog( JmsConnectionManager.class );

    private boolean mParametersLoaded_ = false;
    private String mConnectionState_ = "Disconnected";

    /**
     * Constructs a new instance.
     */
    public JmsConnectionManager()
    {
        super();
    }

    /**
     * @see org.suggs.gui.connection.IJmsConnectionManager#loadConnectionParameters()
     */
    public IJmsConnectionDetails loadConnectionParameters( String aName )
    {
        // firstly validate that all of the conn parameters are there,
        // and then we can load them into the class
        mParametersLoaded_ = true;
        LOG.debug( "Loaded connecvtion parameters" );

        return new JmsConnectionDetails( aName );

    }

    /**
     * @see org.suggs.gui.connection.IJmsConnectionManager#testConnection()
     */
    public boolean testConnection()
    {
        // first check that the conn params have been loaded
        if ( mParametersLoaded_ == false )
        {
            LOG.debug( "no params loaded" );
            return false;

        }

        LOG.debug( "test ok" );
        return true;
    }

    /**
     * @see org.suggs.gui.connection.IJmsConnectionManager#getConnectionState()
     */
    public String getConnectionState()
    {
        return mConnectionState_;
    }

    /**
     * @see org.suggs.gui.connection.IJmsConnectionManager#getListOfKnownConnectionNames()
     */
    public String[] getListOfKnownConnectionNames()
    {
        return new String[] { "Conn1", "Conn2" };
    }
}
