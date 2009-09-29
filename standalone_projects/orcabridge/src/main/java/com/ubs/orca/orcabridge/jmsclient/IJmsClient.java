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

    void connectAndStart() throws JmsClientException;

    void stopAndDisconnect() throws JmsClientException;

}
