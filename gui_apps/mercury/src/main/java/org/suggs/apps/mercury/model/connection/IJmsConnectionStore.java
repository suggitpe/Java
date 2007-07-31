/*
 * IJmsConnectionStore.java created on 28 Jun 2007 18:35:20 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury.model.connection;

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
     * @throws MercuryConnectionException
     *             when there is an issue with the connection store
     */
    IJmsConnectionDetails loadConnectionParameters( String aName ) throws MercuryConnectionException;

    /**
     * Save a set of connection parameters to the local connection
     * store
     * 
     * @param aDetails
     *            the connection parameters to store
     * @param aName
     *            the name of the connection
     * @throws MercuryConnectionException
     *             when there is an issue in the saving of connection
     *             details
     */
    void saveConnectionParameters( String aName, IJmsConnectionDetails aDetails ) throws MercuryConnectionException;

    /**
     * Getter for the internal status of the Connection Store
     * 
     * @return
     */
    String getState();

}
