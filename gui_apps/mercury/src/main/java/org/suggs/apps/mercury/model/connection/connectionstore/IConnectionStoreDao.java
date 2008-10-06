/*
 * IConnectionStore.java created on 22 Sep 2008 18:43:36 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connectionstore;

import org.suggs.apps.mercury.model.connection.ConnectionDetails;

import java.util.Set;

/**
 * Defines a persistence mechanism for storing connection details
 * 
 * @author suggitpe
 * @version 1.0 22 Sep 2008
 */
public interface IConnectionStoreDao
{

    /**
     * This will go to the persistent connection store and retrieve a
     * list of the available and known connections
     * 
     * @return an array of the names of the connections
     */
    Set<String> getListOfKnownConnectionNames();

    /**
     * Checks to see if a connection exists already (by name only)
     * 
     * @param aConnectionName
     *            the name of the connection to look for
     * @return true if the connection exists else false
     */
    boolean doesConnectionExist( String aConnectionName );

    /**
     * Load a connection bean for a given name
     * 
     * @param aName
     *            the name of the connection to load
     * @return the details of the connection
     * @throws ConnectionStoreException
     *             when there is an issue with the connection store
     */
    ConnectionDetails loadConnectionParameters( String aName ) throws ConnectionStoreException;

    /**
     * Save a set of connection parameters to the local connection
     * store
     * 
     * @param aDetails
     *            the connection parameters to store
     * @param aName
     *            the name of the connection
     * @throws ConnectionStoreException
     *             when there is an issue in the saving of connection
     *             details
     */
    void saveConnectionParameters( String aName, ConnectionDetails aDetails )
                    throws ConnectionStoreException;

    /**
     * Allows the deletion of a known connection
     * 
     * @param aName
     *            the name of the connection
     * @throws ConnectionStoreException
     *             if there are any problems
     */
    void deleteNamedConnection( String aName ) throws ConnectionStoreException;

}
