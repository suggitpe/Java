/*
 * IConnectionManager.java created on 22 Jun 2007 08:08:33 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury.model.connection;

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
     * @param aDetails
     *            the context with which to connect
     * @param aConnectionFactoryName
     *            the connection factory name
     * @return
     */
    boolean testConnection( IJmsConnectionDetails aDetails, String aConnectionFactoryName );

    /**
     * Returns the current state of the connection
     * 
     * @return the current state of the connection
     */
    EConnectionState getConnectionState();

    /**
     * Connect to a defined JMS resource
     * 
     * @param aDetails
     *            the connection details
     * @param aConnectionFactoryName
     *            the connection factory name
     * @throws MercuryConnectionException
     */
    void connect( IJmsConnectionDetails aDetails, String aConnectionFactoryName ) throws MercuryConnectionException;

    /**
     * Disconnect from the current connection
     * 
     * @throws MercuryConnectionException
     *             if there are any problems in the disconnect
     */
    void disconnect() throws MercuryConnectionException;

}
