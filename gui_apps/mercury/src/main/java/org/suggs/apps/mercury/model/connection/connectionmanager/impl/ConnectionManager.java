/*
 * ConnectionManager.java created on 20 Jan 2009 07:45:22 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connectionmanager.impl;

import org.suggs.apps.mercury.ContextProvider;
import org.suggs.apps.mercury.model.connection.ConnectionDetails;
import org.suggs.apps.mercury.model.connection.connection.IConnection;
import org.suggs.apps.mercury.model.connection.connection.impl.Connection;
import org.suggs.apps.mercury.model.connection.connectionmanager.IConnectionManager;
import org.suggs.apps.mercury.model.connection.connectionmanager.IConnectionManagerListener;
import org.suggs.apps.mercury.model.connection.connectionstore.ConnectionStoreException;
import org.suggs.apps.mercury.model.connection.connectionstore.IConnectionStore;
import org.suggs.apps.mercury.model.connection.connectionstore.IConnectionStoreChangeListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This is the main impl of the IConnectionManager code. The main aim
 * of this class is to provide the glue in teh application between the
 * connection store and the underlying connections. Any part of the
 * system that wants to have access to a connection should come
 * through this class.
 * 
 * @author suggitpe
 * @version 1.0 20 Jan 2009
 */
public class ConnectionManager implements IConnectionManager, IConnectionStoreChangeListener
{

    private static final Log LOG = LogFactory.getLog( ConnectionManager.class );

    private static IConnectionManager mInstance_;
    private IConnectionStore mConnStore_;
    private Map<String, IConnection> mConnectionMap_ = new HashMap<String, IConnection>();
    private List<IConnectionManagerListener> mListsners = new Vector<IConnectionManagerListener>();

    static
    {
        mInstance_ = new ConnectionManager();
    }

    // non static initialiser
    {
        mConnStore_ = (IConnectionStore) ContextProvider.instance()
            .getBean( "connectionStoreManager" );
        Map<String, ConnectionDetails> map = mConnStore_.getKnownConnections();
        for ( String s : map.keySet() )
        {
            mConnectionMap_.put( s, new Connection( map.get( s ) ) );
        }
        mConnStore_.addConnectionStoreChangeListener( this );
    }

    /**
     * Constructs a new instance.
     */
    private ConnectionManager()
    {
        // this is hidden
    }

    /**
     * TODO: delete me
     * 
     * @see org.suggs.apps.mercury.model.connection.connectionmanager.IConnectionManager#getConnectionDump()
     */
    public String getConnectionDump() throws ConnectionStoreException
    {
        return mConnStore_.getConnectionStoreDumpAsXml();
    }

    /**
     * This is the main singleton interface.
     * 
     * @return the singleton instance of the class
     */
    public static IConnectionManager instance()
    {
        return mInstance_;
    }

    /**
     * This is the part where we have registered interest in any
     * changes in the connection store so that we can correctly
     * reflect these changes in the manager (and those that register
     * interest in the connections).
     * 
     * @see org.suggs.apps.mercury.model.connection.connectionstore.IConnectionStoreChangeListener#handleConnectionStoreChange()
     */
    public void handleConnectionStoreChange(
                                             String aConnectionName,
                                             IConnectionStoreChangeListener.ConnectionStoreEvent aEvent )
    {
        switch ( aEvent )
        {
            case CREATE:
                LOG.debug( "Adding " + aConnectionName + " to conn mgr" );
                try
                {
                    mConnectionMap_.put( aConnectionName,
                                         new Connection( mConnStore_.loadConnectionParameters( aConnectionName ) ) );
                }
                catch ( ConnectionStoreException cse )
                {
                    LOG.warn( "Failed to load connection parameters from the connection store for connection ["
                              + aConnectionName + "]" );
                }
                notifyAllListeners( aConnectionName,
                                    IConnectionManagerListener.ConnectionManagerEvent.CREATE );
                break;
            case EDIT:
                notifyAllListeners( aConnectionName,
                                    IConnectionManagerListener.ConnectionManagerEvent.EDIT );
                throw new NotImplementedException( "Need to implement logic for the edit case" );
                // break;
            case REMOVE:
                LOG.debug( "Removing " + aConnectionName + " from the conn mgr" );
                if ( mConnectionMap_.containsKey( aConnectionName ) )
                {
                    mConnectionMap_.remove( aConnectionName );
                }
                notifyAllListeners( aConnectionName,
                                    IConnectionManagerListener.ConnectionManagerEvent.REMOVE );
                break;
            default:
                throw new IllegalStateException( "Unknown ConnectionStoreEvent ["
                                                 + aEvent.toString() + "]" );
        }

    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connectionmanager.IConnectionManager#containsConnection()
     */
    public boolean containsConnection( String aConnectionName )
    {
        return mConnectionMap_.containsKey( aConnectionName );
    }

    private void notifyAllListeners( String aConnectionName,
                                     IConnectionManagerListener.ConnectionManagerEvent aEvent )
    {
        for ( IConnectionManagerListener l : mListsners )
        {
            l.handleConnectionManagerChange( aConnectionName, aEvent );
        }
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connectionmanager.IConnectionManager#addConnectionManagerListener(org.suggs.apps.mercury.model.connection.connectionmanager.IConnectionManagerListener)
     */
    public void addConnectionManagerListener( IConnectionManagerListener aListener )
    {
        mListsners.add( aListener );
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connectionmanager.IConnectionManager#removeConnectionManagerListsner(org.suggs.apps.mercury.model.connection.connectionmanager.IConnectionManagerListener)
     */
    public void removeConnectionManagerListsner( IConnectionManagerListener aListener )
    {
        mListsners.remove( aListener );
    }

}
