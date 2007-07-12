/*
 * IJmsConnectionDetails.java created on 28 Jun 2007 06:05:41 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.gui.jms.model.connection;


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
    String getConnectionName();

    /**
     * Get the connection type
     * 
     * @return the type of the connection
     */
    EConnectionType getConnectionType();

    /**
     * Get the name for the server for the connection
     * 
     * @return the name of the server
     */
    String getConnectionServer();

    /**
     * Get the port number to connect to
     * 
     * @return the port number for the connection
     */
    String getConnectionPort();

}
