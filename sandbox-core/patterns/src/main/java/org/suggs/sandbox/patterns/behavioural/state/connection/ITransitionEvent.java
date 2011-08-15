/*
 * ITransitionEvent.java created on 14 Aug 2009 07:08:20 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.state.connection;

/**
 * Interface to define an event for a transition. One of the ways to evaluate whether a transition is valid or
 * not is by way of event where the named event on the transition is matched against that on the event
 * context.
 * 
 * @author suggitpe
 * @version 1.0 14 Aug 2009
 */
public interface ITransitionEvent {

    /**
     * Getter for the name of the transition event
     * 
     * @return the name of the transition event
     */
    String getTransitionEventName();

}
