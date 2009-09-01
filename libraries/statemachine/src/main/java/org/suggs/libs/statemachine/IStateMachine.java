/*
 * IStateMachine.java created on 21 Aug 2009 17:47:40 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine;

/**
 * This interface provides the high level functionality to clients of
 * the state machine, so that they can transition through the state
 * machine for a configured set of states. State transitions are
 * initiated through calls to the step function that will evaluate the
 * event context against the internals of the state machine. A state
 * transition will occur based on the current internal state and the
 * event context that is passed through into the state machine.
 * 
 * @author suggitpe
 * @version 1.0 21 Aug 2009
 */
public interface IStateMachine
{

    /**
     * Accessor to the current internal state.
     * 
     * @return the current state of the state machine.
     */
    IState getCurrentState();

    /**
     * This method is the core way in which to transition through the
     * state machine. By calling step with the appropriate event
     * context, one can navigate through the state machine
     * effectively.
     * 
     * @param aContext
     *            the event context from which the transition logic
     *            can be triggered.
     * @throws StateMachineException
     */
    void step( IStateMachineContext aContext ) throws StateMachineException;

}
