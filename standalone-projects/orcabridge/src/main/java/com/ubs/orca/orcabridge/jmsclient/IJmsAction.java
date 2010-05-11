/*
 * IJmsProcessCallback.java created on 21 Oct 2009 07:05:36 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.jmsclient;

import javax.jms.Destination;
import javax.jms.Session;

/**
 * This interface allows for a client of the jms core to perform whatever processing is needed inside a
 * transaction.
 * 
 * @author suggitpe
 * @version 1.0 21 Oct 2009
 */
public interface IJmsAction {

    /**
     * Perform whatever processing is required within a JMS connection.
     * 
     * @param aSession
     *            the session from which to create the transaction
     * @param aDestination
     *            the destination that the processing should be performed against
     * @throws JmsClientException
     */
    void actionInTransaction( Session aSession, Destination aDestination ) throws JmsClientException;

}
