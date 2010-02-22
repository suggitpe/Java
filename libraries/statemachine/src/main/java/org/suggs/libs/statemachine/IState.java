/*
 * IState.java created on 21 Aug 2009 17:47:24 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine;

/**
 * Interface to define the behaviour associated with a State. This
 * State interface is to be used in conjunction with the state machine
 * in the same library. Each state should be wholly responsible for
 * managing its overall transition sequences and evaluation each of
 * the transitions that are associated with it.
 * 
 * @author suggitpe
 * @version 1.0 21 Aug 2009
 */
public interface IState
{

    /**
     * Accessor to the name of the state
     * 
     * @return the name of the state.
     */
    String getStateName();

    /**
     * Attempt to perform a state transition.
     * 
     * @param aContext
     *            a context object so that meaningful information can
     *            be passed to the state to aid in transition
     *            evaluation.
     * @return the new IState that is the end result of that state
     *         transition evaluation. If in the process of the
     *         transition evaluation, it deems that no transition is
     *         valid, then it will return itself to signify no
     *         transition.
     * @throws StateMachineException
     *             if there are any issues in the execution of state
     *             transition.
     */
    IState step( IStateMachineContext aContext ) throws StateMachineException;

    /**
     * When a statemachine enters a new state, it calls this method on
     * the new state.
     * 
     * @param aContext
     *            a context object so that the executing action has
     *            some level of contextual information from which to
     *            perform the action execution relevant to this state.
     * @throws StateMachineException
     */
    void executeEntryAction( IStateMachineContext aContext ) throws StateMachineException;

    /**
     * When a statemachine enters a new state, it calls this method on
     * the old state.
     * 
     * @param aContext
     *            a context object so that the executing action has
     *            some level of contextual information from which to
     *            perform the action execution relevant to this state.
     * @throws StateMachineException
     */
    void executeExitAction( IStateMachineContext aContext ) throws StateMachineException;

}
