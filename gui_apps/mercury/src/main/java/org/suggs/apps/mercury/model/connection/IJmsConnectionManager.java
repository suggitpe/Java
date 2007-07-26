/*
 * IConnectionManager.java created on 22 Jun 2007 08:08:33 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury.model.connection;

import org.suggs.apps.mercury.MercuryException;

/**
 * This interface manages all of the connections available to the GUI
 * 
 * @author suggitpe
 * @version 1.0 22 Jun 2007
 */
public interface IJmsConnectionManager
{

    /**
     * Test the connection parameters (makes a connection)
     * 
     * @param aConnectionDetails
     *            the connection details with which to connect
     * @return true if the connection succeeds, else false
     */
    boolean testConnection( IJmsConnectionDetails aConnectionDetails );

    /**
     * Returns the current state of the connection
     * 
     * @return the current state of the connection
     */
    EConnectionState getConnectionState();

    /**
     * Connect to a defined JMS resource
     * 
     * @param aConnDetails
     * @throws MercuryException
     */
    void connect( IJmsConnectionDetails aConnDetails ) throws MercuryException;

    /**
     * Disconnect from the current connection
     * 
     * @throws MercuryException
     *             if there are any problems in the disconnect
     */
    void disconnect() throws MercuryException;

}
