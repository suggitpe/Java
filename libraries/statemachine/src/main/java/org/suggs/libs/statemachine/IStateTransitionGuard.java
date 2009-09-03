/*
 * IStateTransitionGuard.java created on 1 Sep 2009 19:02:01 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine;

/**
 * TODO Write javadoc for IStateTransitionGuard
 * 
 * @author suggitpe
 * @version 1.0 1 Sep 2009
 */
public interface IStateTransitionGuard
{

    boolean evaluateGuard( IStateMachineContext aContext );

}
