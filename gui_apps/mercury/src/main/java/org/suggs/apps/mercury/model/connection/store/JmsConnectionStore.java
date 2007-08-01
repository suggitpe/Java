/*
 * JmsConnectionStore.java created on 28 Jun 2007 18:36:21 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury.model.connection.store;

import org.suggs.apps.mercury.MercuryException;
import org.suggs.apps.mercury.model.connection.IJmsConnectionDetails;
import org.suggs.apps.mercury.model.connection.IJmsConnectionStore;
import org.suggs.apps.mercury.model.connection.MercuryConnectionStoreException;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * Implementation of the IJmsConnectionStore. This implementation will
 * use an XML file stored in the users home directory (in a .jmshelper
 * dir), for the persistence of the connection details.
 * 
 * @author suggitpe
 * @version 1.0 2 Jul 2007
 */
public class JmsConnectionStore extends Observable implements IJmsConnectionStore, InitializingBean
{

    private static final Log LOG = LogFactory.getLog( JmsConnectionStore.class );

    private String mStoreState_ = new String( "Unsaved" );
    private Map<String, IJmsConnectionDetails> mConnStore_ = new HashMap<String, IJmsConnectionDetails>();

    private IPersistenceLayer mPersistenceLayer_;

    /**
     * Constructs a new instance.
     */
    public JmsConnectionStore()
    {
        super();
    }

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception
    {
        Assert.notNull( mPersistenceLayer_, "Must inject a persisztence layer into the connection store" );

        mPersistenceLayer_.verifyPersistenceLayer();
        try
        {
            mConnStore_ = mPersistenceLayer_.readPersistenceLayer();
        }
        catch ( MercuryException jhe )
        {
            LOG.warn( "UNable to read in connection store details [" + jhe.getMessage() + "]" );
        }
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IJmsConnectionStore#loadConnectionParameters(java.lang.String)
     */
    public IJmsConnectionDetails loadConnectionParameters( String aName ) throws MercuryConnectionStoreException
    {
        IJmsConnectionDetails ret = mConnStore_.get( aName );
        if ( ret == null )
        {
            throw new MercuryConnectionStoreException( "Connection [" + aName + "] does not exist in the connection store" );
        }
        return ret;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IJmsConnectionStore#getListOfKnownConnectionNames()
     */
    public String[] getListOfKnownConnectionNames()
    {
        SortedSet<String> keys = new TreeSet<String>( mConnStore_.keySet() );
        return keys.toArray( new String[keys.size()] );
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IJmsConnectionStore#deleteNamedConnection(java.lang.String)
     */
    public void deleteNamedConnection( String aName ) throws MercuryConnectionStoreException
    {
        LOG.debug( "Deleting connection with name [" + aName + "]" );
        mConnStore_.remove( aName );

        mStoreState_ = "Connection removed";
        mPersistenceLayer_.savePersistenceLayer( mConnStore_ );
        setChanged();
        notifyObservers();
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IJmsConnectionStore#saveConnectionParameters(java.lang.String,
     *      org.suggs.apps.mercury.model.connection.IJmsConnectionDetails)
     */
    public void saveConnectionParameters( String aName, IJmsConnectionDetails aDetails ) throws MercuryConnectionStoreException
    {
        if ( aDetails == null )
        {
            throw new MercuryConnectionStoreException( "Connection details null" );
        }

        aDetails.setConnectionName( aName.toUpperCase() );

        if ( !( aDetails.isConnectionDetailsValid() ) )
        {
            throw new MercuryConnectionStoreException( "Connection details are invalid, pls check data entry" );
        }

        if ( connectionExists( aDetails ) )
        {
            mStoreState_ = "Duplicate (ignored)";
        }
        else
        {
            mStoreState_ = "Saved";
            mConnStore_.put( aDetails.getConnectionName(), aDetails );
            mPersistenceLayer_.savePersistenceLayer( mConnStore_ );
        }
        setChanged();
        notifyObservers();
    }

    /**
     * tests to see if the connection details actually exists in the
     * conn store
     * 
     * @param aConnDtls
     *            the jms connection details to test for
     * @return true if the jms connection store details exists, else
     *         false
     */
    private boolean connectionExists( IJmsConnectionDetails aConnDtls )
    {
        if ( mConnStore_.containsKey( aConnDtls.getConnectionName() ) )
        {
            return true;
        }

        for ( String i : mConnStore_.keySet() )
        {
            LOG.debug( "name  " + i );
        }

        return false;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IJmsConnectionStore#getState()
     */
    public String getState()
    {
        return mStoreState_;
    }

    /**
     * Setter for the store state
     * 
     * @param aState
     *            the state to set
     */
    public void setState( String aState )
    {
        mStoreState_ = aState;
    }

    /**
     * getter for the persistence layer
     * 
     * @return the persistence layer
     */
    public IPersistenceLayer getPersistenceLayer()
    {
        return mPersistenceLayer_;
    }

    /**
     * setter for the persistence layer
     * 
     * @param aLayer
     *            the persistence layer to inject
     */
    public void setPersistenceLayer( IPersistenceLayer aLayer )
    {
        mPersistenceLayer_ = aLayer;

    }

}
