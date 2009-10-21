/*
 * IJmsClientCore.java created on 21 Oct 2009 19:55:22 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.jmsclient;

/**
 * Interface to represent the core JMS behaviour to expose to clients.
 * 
 * @author suggitpe
 * @version 1.0 21 Oct 2009
 */
public interface IJmsClient
{

    /**
     * Connects to a JMS broker with no security credentials.
     * 
     * @throws JmsClientException
     */
    void connect() throws JmsClientException;

    /**
     * Connects to a JMS broker with security credentials
     * 
     * @param aUsername
     *            the username as on the JMS broker
     * @param aPassword
     *            the password as on the JMS broker
     * @throws JmsClientException
     */
    void connect( String aUsername, String aPassword ) throws JmsClientException;

    /**
     * Disconnects from the JMS broker.
     * 
     * @throws JmsClientException
     */
    void disconnect() throws JmsClientException;

    /**
     * Perform the JMS specific processing as required within a
     * specified connection.
     * 
     * @param aCallback
     *            a callback that will implement the required
     *            processing.
     * @throws JmsClientException
     */
    void processInTransaction( IJmsProcessCallback aCallback ) throws JmsClientException;

}
