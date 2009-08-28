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

    String getStateName();

    IState step( IStateMachineContext aContext );

}
