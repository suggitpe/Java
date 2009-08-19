/*
 * ITransitionGuard.java created on 14 Aug 2009 07:08:30 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.state.connection;

/**
 * Interface to define a guard for a transition. One of the ways to
 * evaluate whether a transition is valid or not is by way of
 * evaluating logic encapsualated within a guard. This roughly follows
 * the strategy pattern in that all guards are evaluated under the
 * same function call.
 * 
 * @author suggitpe
 * @version 1.0 14 Aug 2009
 */
public interface ITransitionGuard
{

    /**
     * Evaluates whether or not a guard object is valid for a
     * transition for a given event context.
     * 
     * @param aContext
     *            the event context
     * @return true if the guard logic has been satisfied
     */
    boolean evaluateGuard( IStateMachineEventContext aContext );

}
