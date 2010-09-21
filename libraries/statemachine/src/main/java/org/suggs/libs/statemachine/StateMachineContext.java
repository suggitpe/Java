/*
 * StateMachineContext.java created on 25 Aug 2009 19:52:48 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine;

/**
 * This is the main context for the state machine and allows an encapsulated data structure to be passed
 * around the state machine library. This context should contain all of the information that the state machine
 * needs to evaluate whether or not to transition from one state to another.
 * 
 * @author suggitpe
 * @version 1.0 25 Aug 2009
 */
public interface StateMachineContext {

    /**
     * Getter for the state transition event
     * 
     * @return the state transition event
     */
    StateTransitionEvent getStateTransitionEvent();

}
