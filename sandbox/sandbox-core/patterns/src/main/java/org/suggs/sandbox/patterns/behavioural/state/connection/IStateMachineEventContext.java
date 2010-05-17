/*
 * IEventContext.java created on 13 Aug 2009 18:46:46 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.state.connection;

/**
 * This interface encapsulates the context of the client calls to the state machine and allows for the client
 * calls to provide some level of context to the underlying call.
 * 
 * @author suggitpe
 * @version 1.0 13 Aug 2009
 */
public interface IStateMachineEventContext {

    /**
     * Getter for the name of the event context
     * 
     * @return the name of the event context
     */
    String getEventName();

}
