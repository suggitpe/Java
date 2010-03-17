/*
 * ITransition.java created on 10 Aug 2009 19:29:20 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.state.connection;

/**
 * Interface for the transitions within the state diagram. The
 * transition will be responsible for evaluating whether it can
 * transition given a set of logic implied upon itself.
 * 
 * @author suggitpe
 * @version 1.0 10 Aug 2009
 */
public interface ITransition
{

    /**
     * Getter for the name of the transition
     * 
     * @return the name of the transition
     */
    String getName();

    /**
     * Getter for the starting state of the transition
     * 
     * @return the starting state for the transition
     */
    IState getStartState();

    /**
     * Getter for the end state of the transition
     * 
     * @return the end state of the transition
     */
    IState getEndState();

    /**
     * Evaluate a particular transition to determine whether it is
     * eligible to transition
     * 
     * @param aContext
     *            the event context for evaluating the transition
     *            validity
     * @param aState
     *            the state to evaluate the transition against
     * @return true if the transition is a valid one, else false
     */
    boolean evaluateStateAgainstEventsAndGuards( IStateMachineEventContext aContext, IState aState );

}
