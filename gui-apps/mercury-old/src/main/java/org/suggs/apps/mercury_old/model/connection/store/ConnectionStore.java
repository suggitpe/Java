/*
 * JmsConnectionStore.java created on 28 Jun 2007 18:36:21 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury_old.model.connection.store;

import org.suggs.apps.mercury_old.MercuryException;
import org.suggs.apps.mercury_old.model.connection.IConnectionDetails;
import org.suggs.apps.mercury_old.model.connection.IConnectionStore;
import org.suggs.apps.mercury_old.model.connection.MercuryConnectionStoreException;

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
 * Implementation of the IJmsConnectionStore. This implementation will use an XML file stored in the users
 * home directory (in a .jmshelper dir), for the persistence of the connection details.
 * 
 * @author suggitpe
 * @version 1.0 2 Jul 2007
 */
public class ConnectionStore extends Observable implements IConnectionStore, InitializingBean {

    private static final Log LOG = LogFactory.getLog( ConnectionStore.class );

    private String storeState = new String( "Unsaved" );
    private Map<String, IConnectionDetails> connStore = new HashMap<String, IConnectionDetails>();

    private IPersistenceLayer mPersistenceLayer_;

    /**
     * Constructs a new instance.
     */
    public ConnectionStore() {
        super();
    }

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception {
        Assert.notNull( mPersistenceLayer_, "Must inject a persisztence layer into the connection store" );

        mPersistenceLayer_.verifyPersistenceLayer();
        try {
            Map<String, IConnectionDetails> cs = mPersistenceLayer_.readPersistenceLayer();
            if ( cs != null ) {
                connStore = cs;
            }
        }
        catch ( MercuryException jhe ) {
            LOG.warn( "Unable to read in connection store details [" + jhe.getMessage() + "]" );
        }
    }

    /**
     * @see org.suggs.apps.mercury_old.model.connection.IConnectionStore#loadConnectionParameters(java.lang.String)
     */
    public IConnectionDetails loadConnectionParameters( String aName ) throws MercuryConnectionStoreException {
        IConnectionDetails ret = connStore.get( aName );
        if ( ret == null ) {
            throw new MercuryConnectionStoreException( "Connection [" + aName
                                                       + "] does not exist in the connection store" );
        }
        return ret;
    }

    /**
     * @see org.suggs.apps.mercury_old.model.connection.IConnectionStore#getListOfKnownConnectionNames()
     */
    public String[] getListOfKnownConnectionNames() {
        SortedSet<String> keys = new TreeSet<String>( connStore.keySet() );
        return keys.toArray( new String[keys.size()] );
    }

    /**
     * @see org.suggs.apps.mercury_old.model.connection.IConnectionStore#doesConnectionExist(java.lang.String)
     */
    public boolean doesConnectionExist( String aConnectionName ) {
        return connStore.containsKey( aConnectionName.toUpperCase() );
    }

    /**
     * @see org.suggs.apps.mercury_old.model.connection.IConnectionStore#deleteNamedConnection(java.lang.String)
     */
    public void deleteNamedConnection( String aName ) throws MercuryConnectionStoreException {
        LOG.debug( "Deleting connection with name [" + aName + "]" );
        connStore.remove( aName );

        storeState = "Connection removed";
        mPersistenceLayer_.savePersistenceLayer( connStore );
        setChanged();
        notifyObservers();
    }

    /**
     * @see org.suggs.apps.mercury_old.model.connection.IConnectionStore#saveConnectionParameters(java.lang.String,
     *      org.suggs.apps.mercury_old.model.connection.IConnectionDetails)
     */
    public void saveConnectionParameters( String aName, IConnectionDetails aDetails )
                    throws MercuryConnectionStoreException {
        if ( aDetails == null ) {
            throw new MercuryConnectionStoreException( "Connection details null" );
        }

        aDetails.setName( aName.toUpperCase() );

        if ( !( aDetails.isConnectionDetailsValid() ) ) {
            throw new MercuryConnectionStoreException( "Connection details are invalid, pls check data entry" );
        }

        if ( connectionExists( aDetails ) ) {
            storeState = "Overwritten existing";
        }
        else {
            storeState = "Saved";
        }
        connStore.put( aDetails.getName(), aDetails );
        mPersistenceLayer_.savePersistenceLayer( connStore );
        setChanged();
        notifyObservers();
    }

    /**
     * tests to see if the connection details actually exists in the conn store. TODO: need to finish off this
     * impl
     * 
     * @param aConnDtls
     *            the jms connection details to test for
     * @return true if the jms connection store details exists, else false
     */
    private boolean connectionExists( IConnectionDetails aConnDtls ) {
        if ( connStore.containsKey( aConnDtls.getName() ) ) {
            return true;
        }

        for ( String i : connStore.keySet() ) {
            LOG.debug( "name  " + i );
        }

        return false;
    }

    /**
     * @see org.suggs.apps.mercury_old.model.connection.IConnectionStore#getState()
     */
    public String getState() {
        return storeState;
    }

    /**
     * Setter for the store state
     * 
     * @param aState
     *            the state to set
     */
    public void setState( String aState ) {
        storeState = aState;
    }

    /**
     * getter for the persistence layer
     * 
     * @return the persistence layer
     */
    public IPersistenceLayer getPersistenceLayer() {
        return mPersistenceLayer_;
    }

    /**
     * setter for the persistence layer
     * 
     * @param aLayer
     *            the persistence layer to inject
     */
    public void setPersistenceLayer( IPersistenceLayer aLayer ) {
        mPersistenceLayer_ = aLayer;

    }

}
