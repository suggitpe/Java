/*
 * ITransition.java created on 21 Aug 2009 17:47:54 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine;

/**
 * Interface to define the behaviour associated with a transition
 * between two states. This transition is to be used in conjunction
 * with a state in the same library. Each state transition will be
 * responsible for managing the logic of determining whether it is
 * itself valid for transition.
 * 
 * @author suggitpe
 * @version 1.0 21 Aug 2009
 */
public interface IStateTransition
{

    String getTransitionName();

    IState getStartingState();

    IState getEndingState();

    boolean evaluateTransitionValidity( IStateMachineContext aContext )
                    throws StateMachineException;

}
