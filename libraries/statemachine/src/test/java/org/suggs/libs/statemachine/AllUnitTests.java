/*
 * AllUnitTests.java created on 24 Aug 2009 06:56:18 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine;

import org.suggs.libs.statemachine.unit.StateMachineTest;
import org.suggs.libs.statemachine.unit.StateTest;
import org.suggs.libs.statemachine.unit.StateTransitionEventTest;
import org.suggs.libs.statemachine.unit.StateTransitionManagerTest;
import org.suggs.libs.statemachine.unit.StateTransitionTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * All Unit Tests suite for the state machine library
 * 
 * @author suggitpe
 * @version 1.0 24 Aug 2009
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { StateTransitionManagerTest.class, StateMachineTest.class, StateTest.class,
                      StateTransitionTest.class, StateTransitionEventTest.class })
public class AllUnitTests
{}
