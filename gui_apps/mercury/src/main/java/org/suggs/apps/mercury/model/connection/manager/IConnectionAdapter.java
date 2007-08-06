/*
 * IConnectionAdapter.java created on 3 Aug 2007 05:58:21 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.manager;

import org.suggs.apps.mercury.model.connection.IJmsConnectionDetails;

import javax.naming.Context;

/**
 * High level adapter interface to allow for the differences in
 * function for the various middleware implementations
 * 
 * @author suggitpe
 * @version 1.0 3 Aug 2007
 */
public interface IConnectionAdapter
{

    /**
     * Creates the connection context from which we can locate
     * factories and destinations
     * 
     * @param aConnDetails
     *            the connection details required for the connection
     * @return the connection context
     */
    Context createJmsContext( IJmsConnectionDetails aConnDetails );

}
