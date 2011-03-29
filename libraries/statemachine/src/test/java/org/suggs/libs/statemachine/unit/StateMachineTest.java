/*
 * StateMachineTest.java created on 26 Aug 2009 07:09:34 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine.unit;

import org.suggs.libs.statemachine.State;
import org.suggs.libs.statemachine.StateMachine;
import org.suggs.libs.statemachine.StateMachineContext;
import org.suggs.libs.statemachine.StateMachineException;
import org.suggs.libs.statemachine.impl.StateMachineImpl;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test suite for the state machine implementation.
 *
 * @author suggitpe
 * @version 1.0 26 Aug 2009
 */
public class StateMachineTest {

    private static final Logger LOG = LoggerFactory.getLogger( StateMachineTest.class );

    private State mockInitialState;
    private State mockNewState;
    private StateMachineContext mockContext;

    /** */
    @BeforeClass
    public static void doBeforeClass() {
        LOG.debug( "===================" + StateMachineTest.class.getSimpleName() );
    }

    /** */
    @Before
    public void doBefore() {
        LOG.debug( "------------------- " );
        mockInitialState = mock( State.class );
        mockNewState = mock( State.class );
        mockContext = mock( StateMachineContext.class );
    }

    @Test
    public void toStringOutput() {
        StateMachineImpl stateMachine = new StateMachineImpl( mockInitialState );
        stateMachine.setCurrentState( mockNewState );
        LOG.debug( stateMachine.toString() );
    }

    /**
     * Tests that when we create a state machine with a state and then call the getter for the state that the same one
     * is returned.
     */
    @Test
    public void stateMachineInitiation() {

        when( mockInitialState.getStateName() ).thenReturn( "InitialState" );

        StateMachine stateMachine = new StateMachineImpl( mockInitialState );
        State curState = stateMachine.getCurrentState();

        LOG.debug( "Expecting that the state machine is in the correct initial state" );
        assertThat( mockInitialState, equalTo( curState ) );

    }

    /**
     * Tests that when SM calls step on a state and the state returns with a new state, that we actually transition to
     * that new state.
     *
     * @throws StateMachineException from the state machine step call
     */
    @Test
    public void stepResultsInNewCurrentState() throws StateMachineException {
        when( mockInitialState.step( mockContext ) ).thenReturn( mockNewState );
        when( mockNewState.step( mockContext ) ).thenReturn( mockNewState );

        StateMachine stateMachine = new StateMachineImpl( mockInitialState );
        stateMachine.step( mockContext );

        LOG.debug( "Expecting that the state machine has transitioned to the new state due to new state returned." );
        assertThat( stateMachine.getCurrentState(), equalTo( mockNewState ) );
    }

    /**
     * Tests that when a step call returns the same object that we do not then transition to a new state.
     *
     * @throws StateMachineException from the state machine step call
     */
    @Test
    public void noStepResultsInSameCurrentState() throws StateMachineException {
        when( mockInitialState.step( mockContext ) ).thenReturn( mockInitialState );

        StateMachine stateMachine = new StateMachineImpl( mockInitialState );
        stateMachine.step( mockContext );

        LOG.debug( "Expecting that the state machine has remained in the initial state due to same state returned" );
        assertThat( stateMachine.getCurrentState(), equalTo( mockInitialState ) );
    }

    /**
     * Tests that when a step call returns a null object that we do not then transition to a new state.
     *
     * @throws StateMachineException from the call to step
     */
    @Test
    public void nullStepResultsInSameCurrentState() throws StateMachineException {
        when( mockInitialState.step( mockContext ) ).thenReturn( null );

        StateMachine stateMachine = new StateMachineImpl( mockInitialState );
        stateMachine.step( mockContext );

        LOG.debug( "Expecting that the state machine remains in the initial state due to NULL step result" );
        assertThat( stateMachine.getCurrentState(), equalTo( mockInitialState ) );
    }
}
