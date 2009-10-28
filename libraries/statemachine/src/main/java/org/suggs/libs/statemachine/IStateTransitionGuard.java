/*
 * IStateTransitionGuard.java created on 1 Sep 2009 19:02:01 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine;

/**
 * State Transition guard to allow for logical evaluation of a state
 * transition occurrance. Based on the execution of the assocated
 * guard logic, the transition would either pass or fail.
 * 
 * @author suggitpe
 * @version 1.0 1 Sep 2009
 */
public interface IStateTransitionGuard
{

    /**
     * Execute the evaluation of the guard condition.
     * 
     * @param aContext
     *            the context from which to evaluate guard
     * @return true if the guard is passed, else false
     */
    boolean evaluateGuard( IStateMachineContext aContext );

}
