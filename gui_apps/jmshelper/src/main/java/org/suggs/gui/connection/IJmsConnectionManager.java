/*
 * IConnectionManager.java created on 22 Jun 2007 08:08:33 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.gui.connection;

/**
 * This interface manages all of the connections available to the GUI
 * 
 * @author suggitpe
 * @version 1.0 22 Jun 2007
 */
public interface IJmsConnectionManager
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
     * Test the connection parameters (makes a connection)
     * 
     * @return true if the connection succeeds, else false
     */
    boolean testConnection();

    /**
     * Returns the current state of the connection
     * 
     * @return the current state of the connection
     */
    String getConnectionState();

}
