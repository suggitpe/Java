/*
 * ITransition.java created on 21 Aug 2009 17:47:54 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine;

/**
 * Interface to define the behaviour associated with a transition between two states. This transition is to be
 * used in conjunction with a state in the same library. Each state transition will be responsible for
 * managing the logic of determining whether it is itself valid for transition.
 * 
 * @author suggitpe
 * @version 1.0 21 Aug 2009
 */
public interface IStateTransition {

    /**
     * Accessor to the name of the transition
     * 
     * @return the name of the transition
     */
    String getTransitionName();

    /**
     * Accessor to the state at the beginning of the transition
     * 
     * @return the starting state
     */
    IState getStartingState();

    /**
     * Accessor to the state at the end of the transition
     * 
     * @return the ending state
     */
    IState getEndingState();

    /**
     * This is the core delegation method on the transitions. It is here that a transition evaluates itself
     * for validity against the passed in context.
     * 
     * @param aContext
     *            the context from which to evaluate whether the state transition is valid
     * @return true if the state transition is valid, else false
     * @throws StateMachineException
     *             when any issues are encountered in the evaluation of the state transition
     */
    boolean evaluateTransitionValidity( IStateMachineContext aContext ) throws StateMachineException;

}
