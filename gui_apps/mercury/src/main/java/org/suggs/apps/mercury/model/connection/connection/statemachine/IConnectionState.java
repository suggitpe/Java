/*
 * IConnectionState.java created on 1 Feb 2009 01:37:17 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connection.statemachine;

/**
 * This interface defines the behaviour of a state in the connection
 * state manager. This class represents the high level behaviour for
 * the state pattern impl used in this
 * 
 * @author suggitpe
 * @version 1.0 1 Feb 2009
 */
public interface IConnectionState
{

    /**
     * Enum to represent the underlying state.
     * 
     * @author suggitpe
     * @version 1.0 2 Feb 2009
     */
    enum CONNECTION_STATE_NAME {
        CONNECTED, DISCONNECTED, FAILED, CONNECTING, DISCONNECTING
    };

    /**
     * Transition to a connected state.
     */
    boolean connect();

    /**
     * Transition to a disconnected state
     */
    boolean disconnect();

    /**
     * Gets the state of the underlying state represented as an
     * enumerated string.
     * 
     * @return the state name
     */
    String getState();

}
