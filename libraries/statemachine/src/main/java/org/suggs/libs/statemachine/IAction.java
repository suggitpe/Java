/*
 * IAction.java created on 18 Feb 2010 20:14:10 by suggitpe for project state-machine-lib
 * 
 */
package org.suggs.libs.statemachine;

/**
 * Interface that will allow arbitrary functionality to be called
 * throughout the statemachine implementation.
 * 
 * @author suggitpe
 * @version 1.0 18 Feb 2010
 */
public interface IAction
{

    /**
     * This interface imposes a vague notion of the command/strategy
     * pattern that can be called to perform some action.
     * 
     * @param aContext
     *            a context object that olds all of the information
     *            and references required for successful execution of
     *            the state machine.
     * @throws StateMachineException
     *             when there is any kind of issue caused in the
     *             execution of the action.
     */
    void execute( IStateMachineContext aContext ) throws StateMachineException;

}
