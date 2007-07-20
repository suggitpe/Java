/*
 * JmsConnectionStore.java created on 28 Jun 2007 18:36:21 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury.model.connection.store;

import org.suggs.apps.mercury.model.connection.IJmsConnectionDetails;
import org.suggs.apps.mercury.model.connection.IJmsConnectionStore;
import org.suggs.apps.mercury.model.connection.JmsConnectionException;

import java.util.Observable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Implementation of the IJmsConnectionStore. This implementation will
 * use an XML file stored in the users home directory (in a .jmshelper
 * dir), for the persistence of the connection details.
 * 
 * @author suggitpe
 * @version 1.0 2 Jul 2007
 */
public class JmsConnectionStore extends Observable implements IJmsConnectionStore
{

    private static final Log LOG = LogFactory.getLog( JmsConnectionStore.class );
    private String mStoreState_ = new String( "Unsaved" );
    private static final String HOME_DIR = System.getProperty( "user.home" );

    /**
     * Constructs a new instance.
     */
    public JmsConnectionStore()
    {
        super();
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IJmsConnectionStore#loadConnectionParameters(java.lang.String)
     */
    public IJmsConnectionDetails loadConnectionParameters( String aName ) throws JmsConnectionException
    {
        LOG.debug( "Loaded connecvtion parameters" );

        return new JmsConnectionDetails( aName );
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IJmsConnectionStore#getListOfKnownConnectionNames()
     */
    public String[] getListOfKnownConnectionNames()
    {
        return new String[] { "Conn1", "Conn2" };
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IJmsConnectionStore#saveConnectionParameters(org.suggs.apps.mercury.model.connection.IJmsConnectionDetails)
     */
    public void saveConnectionParameters( IJmsConnectionDetails aDetails ) throws JmsConnectionException
    {
        LOG.warn( "Save impl has not been done yet" );
        mStoreState_ = "Saved";
        setChanged();
        notifyObservers();
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

}
