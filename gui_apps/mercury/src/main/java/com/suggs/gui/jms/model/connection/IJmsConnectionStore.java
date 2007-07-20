/*
 * IJmsConnectionStore.java created on 28 Jun 2007 18:35:20 by suggitpe for project GUI - JmsHelper
 * 
 */
package com.suggs.gui.jms.model.connection;


/**
 * Defines a persistence mechanism for storing connection details
 * 
 * @author suggitpe
 * @version 1.0 28 Jun 2007
 */
public interface IJmsConnectionStore
{

    /**
     * This will go to the persistent connection store and retrieve a
     * list of the avialable and known connections
     * 
     * @return an array of the names of the connections
     */
    String[] getListOfKnownConnectionNames();

    /**
     * LOad a connection parameter for a given name
     * 
     * @param aName
     *            the name of the connection to load
     * @return the details fo the connection
     */
    IJmsConnectionDetails loadConnectionParameters( String aName );

    /**
     * Save a set of connection parameters to the local connection
     * store
     * 
     * @param aDetails
     *            the connection parameters to store
     */
    void saveConnectionParameters( IJmsConnectionDetails aDetails );
    
    /**
     * Getter for the internal status of the Connection Store
     * @return
     */
    String getState();

}
