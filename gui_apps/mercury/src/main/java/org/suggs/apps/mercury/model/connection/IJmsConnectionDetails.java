/*
 * IJmsConnectionDetails.java created on 28 Jun 2007 06:05:41 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury.model.connection;

import java.util.Map;

/**
 * An object to wrap up the connection details for a given connection
 * 
 * @author suggitpe
 * @version 1.0 28 Jun 2007
 */
public interface IJmsConnectionDetails
{

    /**
     * Get the name of the connection
     * 
     * @return the name of the connection
     */
    String getName();

    /**
     * Setter for the connection name
     * 
     * @param aName
     *            the name for the connection details
     */
    void setName( String aName );

    /**
     * Get the connection type
     * 
     * @return the type of the connection
     */
    EConnectionType getType();

    /**
     * Get the name for the server for the connection
     * 
     * @return the name of the server
     */
    String getHostname();

    /**
     * Get the port number to connect to
     * 
     * @return the port number for the connection
     */
    String getPort();

    /**
     * Get any additional connection metadata for the connection
     * 
     * @return the connection metadata
     */
    Map<String, String> getMetaData();

    /**
     * Internal verification method that will self certify that the
     * current IJmsConnectionDetails object is fully populated for a
     * connection (including a name)
     * 
     * @return true of the connection details are valid, else false
     */
    boolean isConnectionDetailsValid();

}
