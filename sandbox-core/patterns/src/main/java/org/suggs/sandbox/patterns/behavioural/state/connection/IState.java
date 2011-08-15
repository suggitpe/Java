/*
 * IState.java created on 6 Aug 2009 07:14:51 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.state.connection;

/**
 * Interface to represent a State. Each state should be responsible for receiving calls to its own step method
 * so that it can evaluate (or delegate) the evaluation of the transitioning process between the states.
 * 
 * @author suggitpe
 * @version 1.0 6 Aug 2009
 */
public interface IState {

    /**
     * Getter for the name of the state
     * 
     * @return the name of the state.
     */
    String getName();

    /**
     * Attempt to perform a state transition.
     * 
     * @param aContext
     *            a context object so that meaningful information can be passed to the state
     * @return the new IState that is the end result of that state transition, or if there is no state
     *         transition performed, then it will return itself.
     */
    IState step( IStateMachineEventContext aContext );

}
