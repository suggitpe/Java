/*
 * IJmsClient.java created on 29 Sep 2009 07:05:29 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.jmsclient;

/**
 * Top level interface for the JMS client interaction. The whole
 * purpose of this interface and the classes behind it are to decouple
 * the bridge from any JMS code and encapsulate it in one place.
 * 
 * @author suggitpe
 * @version 1.0 29 Sep 2009
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

}
