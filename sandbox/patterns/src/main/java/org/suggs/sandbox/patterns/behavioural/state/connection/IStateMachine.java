/*
 * IStateMachine.java created on 10 Aug 2009 19:29:55 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.state.connection;

/**
 * This interface provides the high level functionality to clients of the state machine, so that they can
 * transition through the state machine for a configured set of states. From a 1,000 ft high level, the state
 * machine is initialised with an initial state, and subsequent state transitions are triggered through calls
 * to the step function that will evaluate the event context against the internals of the state machine. The
 * current internal state of the state machine is evaluated through a call to get the underlying current
 * state.
 * 
 * @author suggitpe
 * @version 1.0 10 Aug 2009
 */
public interface IStateMachine {

    /**
     * Getter for th current state of the state machine.
     * 
     * @return the internal state of the state machine
     */
    IState getState();

    /**
     * Allows a client to call the state machine to move states. It is assumed that the evaluation of whether
     * or not the transition actually happens is down to the implementation with the states and transitions of
     * the state diagram.
     * 
     * @param aContext
     *            the context from which to evaluate whether or how to transition between states
     */
    void step( IStateMachineEventContext aContext );

}
