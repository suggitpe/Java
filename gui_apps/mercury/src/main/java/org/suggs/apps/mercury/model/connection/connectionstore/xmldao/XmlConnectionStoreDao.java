/*
 * ConnectionStore.java created on 22 Sep 2008 18:44:55 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connectionstore.xmldao;

import org.suggs.apps.mercury.model.connection.ConnectionDetails;
import org.suggs.apps.mercury.model.connection.connectionstore.ConnectionStoreException;
import org.suggs.apps.mercury.model.connection.connectionstore.IConnectionStore;
import org.suggs.apps.mercury.model.connection.connectionstore.IConnectionStoreChangeListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * This is a DAO implementation from which we can manage the
 * underlying XML storage. This delegates to an XML persistence layer.
 * 
 * @author suggitpe
 * @version 1.0 22 Sep 2008
 */
public class XmlConnectionStoreDao implements IConnectionStore, InitializingBean
{

    private static final Log LOG = LogFactory.getLog( XmlConnectionStoreDao.class );

    private IXmlConnectionStoreManager mXmlStore_;
    private List<IConnectionStoreChangeListener> mListeners_ = new Vector<IConnectionStoreChangeListener>();

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception
    {
        Assert.notNull( mXmlStore_, "Must set the xml store on the xml connection store dao" );
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connectionstore.IConnectionStore#deleteNamedConnection(java.lang.String)
     */
    public void deleteNamedConnection( String name ) throws ConnectionStoreException
    {
        LOG.info( "Deleting named connection [" + name + "]" );
        if ( name == null || name.equals( "" ) )
        {
            throw new ConnectionStoreException( "Trying to delete a connection with a null or empty name.  Try passing in a valid name." );
        }

        Map<String, ConnectionDetails> conns = mXmlStore_.readConnectionData();
        if ( conns.containsKey( name ) )
        {
            conns.remove( name );
        }
        else
        {
            LOG.warn( "No connection named [" + name + "] could be found" );
            throw new ConnectionStoreException( "No connection could be found for name [" + name
                                                + "], please use a different name" );
        }

        mXmlStore_.saveConnectionData( conns );

        notifyAllListeners( name, IConnectionStoreChangeListener.ConnectionStoreEvent.REMOVE );
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connectionstore.IConnectionStore#doesConnectionExist(java.lang.String)
     */
    public boolean doesConnectionExist( String connectionName )
    {
        try
        {
            Map<String, ConnectionDetails> conns = mXmlStore_.readConnectionData();
            // we want to check ignoring the case
            for ( String s : conns.keySet() )
            {
                if ( connectionName.equalsIgnoreCase( s ) )
                {
                    return true;
                }
            }
            return false;
        }
        catch ( ConnectionStoreException ce )
        {
            LOG.error( "Exception caught when trying to read the connection data for doesConnectionExist",
                       ce );
            return false;
        }

    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connectionstore.IConnectionStore#getKnownConnections()
     */
    public Map<String, ConnectionDetails> getKnownConnections()
    {
        try
        {
            Map<String, ConnectionDetails> conns = mXmlStore_.readConnectionData();
            return conns;
        }
        catch ( ConnectionStoreException ce )
        {
            LOG.error( "Exception caught when trying to read the connection data for getListOfKnownConnectionNames",
                       ce );
            return new HashMap<String, ConnectionDetails>();
        }
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connectionstore.IConnectionStore#loadConnectionParameters(java.lang.String)
     */
    public ConnectionDetails loadConnectionParameters( String name )
                    throws ConnectionStoreException
    {
        LOG.debug( "Loading connection details with name [" + name + "]" );
        Map<String, ConnectionDetails> conns = mXmlStore_.readConnectionData();
        if ( conns.containsKey( name ) )
        {
            return conns.get( name );
        }
        throw new ConnectionStoreException( "No connection with the name [" + name + "] exists." );
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connectionstore.IConnectionStore#saveConnectionParameters(java.lang.String,
     *      org.suggs.apps.mercury.model.connection.ConnectionDetails)
     */
    public void saveConnectionParameters( String name, ConnectionDetails details )
                    throws ConnectionStoreException
    {
        Map<String, ConnectionDetails> conns = mXmlStore_.readConnectionData();
        if ( conns.containsKey( name ) )
        {
            throw new ConnectionStoreException( "Trying to save connection name=[" + name
                                                + "] when connection name already exists" );
        }
        LOG.debug( "Saving connection [" + name + "] to the underlying connection store mechanism" );
        conns.put( name, details );
        mXmlStore_.saveConnectionData( conns );
        notifyAllListeners( name, IConnectionStoreChangeListener.ConnectionStoreEvent.CREATE );
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connectionstore.IConnectionStore#addConnectionStoreChangeListener(org.suggs.apps.mercury.model.connection.connectionstore.IConnectionStoreChangeListener)
     */
    public void addConnectionStoreChangeListener( IConnectionStoreChangeListener aListener )
    {
        mListeners_.add( aListener );
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connectionstore.IConnectionStore#removeConnectionStoreChangeListener(org.suggs.apps.mercury.model.connection.connectionstore.IConnectionStoreChangeListener)
     */
    public void removeConnectionStoreChangeListener( IConnectionStoreChangeListener aListener )
    {
        mListeners_.remove( aListener );
    }

    /**
     * This method is used to put out the change notification to all
     * of the listeners that have registered their interest.
     */
    private void notifyAllListeners( String aConnectionName,
                                     IConnectionStoreChangeListener.ConnectionStoreEvent aEvent )
    {
        for ( IConnectionStoreChangeListener l : mListeners_ )
        {
            l.handleConnectionStoreChange( aConnectionName, aEvent );
        }
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connectionstore.IConnectionStore#getConnectionStoreDumpAsXml()
     */
    public String getConnectionStoreDumpAsXml() throws ConnectionStoreException
    {
        return mXmlStore_.getRawXml();
    }

    /**
     * Getter for the connection store manager
     * 
     * @return the connection store manager
     */
    public IXmlConnectionStoreManager getConnectionStoreManager()
    {
        return mXmlStore_;
    }

    /**
     * Setter for thr connection store manager
     * 
     * @param connectionStoreManager
     *            the manager to set
     */
    public void setConnectionStoreManager( IXmlConnectionStoreManager connectionStoreManager )
    {
        mXmlStore_ = connectionStoreManager;
    }

}
