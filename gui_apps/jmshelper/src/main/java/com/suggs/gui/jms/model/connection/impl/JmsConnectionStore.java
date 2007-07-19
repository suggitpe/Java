/*
 * JmsConnectionStore.java created on 28 Jun 2007 18:36:21 by suggitpe for project GUI - JmsHelper
 * 
 */
package com.suggs.gui.jms.model.connection.impl;

import com.suggs.gui.jms.model.connection.IJmsConnectionDetails;
import com.suggs.gui.jms.model.connection.IJmsConnectionStore;

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

    /**
     * @see com.suggs.gui.jms.model.connection.IJmsConnectionStore#loadConnectionParameters(java.lang.String)
     */
    public IJmsConnectionDetails loadConnectionParameters( String aName )
    {
        // firstly validate that all of the conn parameters are there,
        // and then we can load them into the class
        LOG.debug( "Loaded connecvtion parameters" );

        return new JmsConnectionDetails( aName );
    }

    /**
     * @see com.suggs.gui.jms.model.connection.IJmsConnectionStore#getListOfKnownConnectionNames()
     */
    public String[] getListOfKnownConnectionNames()
    {
        return new String[] { "Conn1", "Conn2" };
    }

    /**
     * @see com.suggs.gui.jms.model.connection.IJmsConnectionStore#saveConnectionParameters(com.suggs.gui.jms.model.connection.IJmsConnectionDetails)
     */
    public void saveConnectionParameters( IJmsConnectionDetails aDetails )
    {
        LOG.warn( "Save impl has not been done yet" );
        mStoreState_ = "Saved";
        setChanged();
        notifyObservers();
    }

    /**
     * @see com.suggs.gui.jms.model.connection.IJmsConnectionStore#getState()
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
