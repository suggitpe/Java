/*
 * AllUnitTests.java created on 24 Aug 2009 06:56:18 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine;

import org.suggs.libs.statemachine.impl.StateMachineTest;
import org.suggs.libs.statemachine.impl.StateTest;
import org.suggs.libs.statemachine.impl.StateTransitionManagerTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * All Unit Tests suite for the state machine library
 * 
 * @author suggitpe
 * @version 1.0 24 Aug 2009
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { StateTransitionManagerTest.class, StateMachineTest.class, StateTest.class })
public class AllUnitTests
{}
