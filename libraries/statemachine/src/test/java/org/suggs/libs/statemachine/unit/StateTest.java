/*
 * StateTest.java created on 28 Aug 2009 18:15:18 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine.unit;

import org.suggs.libs.statemachine.*;
import org.suggs.libs.statemachine.impl.StateImpl;
import org.suggs.libs.statemachine.impl.StateTransitionManager;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test suite for the state implementation.
 *
 * @author suggitpe
 * @version 1.0 28 Aug 2009
 */
public class StateTest {

    private static final Logger LOG = LoggerFactory.getLogger( StateTest.class );

    private StateMachineContext mockContext;
    private StateTransition mockTransitionOne;
    private StateTransition mockTransitionTwo;
    private Action mockAction;

    /** */
    @BeforeClass
    public static void doBeforeClass() {
        LOG.debug( "===================" + StateTest.class.getSimpleName() );
    }

    /** */
    @Before
    public void doBefore() {
        LOG.debug( "------------------- " );
        StateTransitionManager.instance().clearTransitionsFromTransitionManager();
        mockContext = mock( StateMachineContext.class );
        mockTransitionOne = mock( StateTransition.class );
        mockTransitionTwo = mock( StateTransition.class );
        mockAction = mock( Action.class );
    }

    /**
     * Simply tests that the state name has been correctly set at state construction
     */
    @Test
    public void stateNameExtraction() {
        final String STATE_NAME = "TestStateForTest";
        State state = new StateImpl( STATE_NAME );

        assertThat( state.getStateName(), equalTo( STATE_NAME ) );
        LOG.debug( "Successfully created state[" + state + "]" );
    }

    /**
     * Tests that when there are valid transitions set up, step will return the correct new end state.
     *
     * @throws StateMachineException if there is an issue with the transition validity
     */
    @SuppressWarnings("boxing")
    @Test
    public void stepWithValidTransitionsToReturnNewState() throws StateMachineException {
        State state = new StateImpl( "TestState" );
        State endState = new StateImpl( "TestEndState" );

        when( mockTransitionOne.getStartingState() ).thenReturn( state );
        when( mockTransitionOne.getTransitionName() ).thenReturn( "invalidTransition" );
        when( mockTransitionTwo.getStartingState() ).thenReturn( state );
        when( mockTransitionTwo.getTransitionName() ).thenReturn( "badTransition" );
        when( mockTransitionTwo.getEndingState() ).thenReturn( endState );

        when( mockTransitionOne.evaluateTransitionValidity( mockContext ) ).thenReturn( false );
        when( mockTransitionTwo.evaluateTransitionValidity( mockContext ) ).thenReturn( true );

        StateTransitionManager.instance().addTransitionToManager( mockTransitionOne );
        StateTransitionManager.instance().addTransitionToManager( mockTransitionTwo );

        State newState = state.step( mockContext );

        assertThat( state, not( equalTo( newState ) ) );
        assertThat( endState, equalTo( newState ) );
        LOG.debug( "Checked that the step call returns a different state when there are valid transitions setup" );
    }

    /**
     * Tests that when there are more than one valid transitions set up, step will give rise to an exception being
     * thrown.
     *
     * @throws StateMachineException if there is an issue with the transition validity
     */
    @SuppressWarnings("boxing")
    @Test(expected = StateMachineException.class)
    public void stepWithTwoValidTransitionsCausesException() throws StateMachineException {
        State state = new StateImpl( "TestState" );
        State endState = new StateImpl( "TestEndState" );

        when( mockTransitionOne.getStartingState() ).thenReturn( state );
        when( mockTransitionOne.getTransitionName() ).thenReturn( "invalidTransition" );
        when( mockTransitionTwo.getStartingState() ).thenReturn( state );
        when( mockTransitionTwo.getTransitionName() ).thenReturn( "badTransition" );
        when( mockTransitionTwo.getEndingState() ).thenReturn( endState );

        when( mockTransitionOne.evaluateTransitionValidity( mockContext ) ).thenReturn( true );
        when( mockTransitionTwo.evaluateTransitionValidity( mockContext ) ).thenReturn( true );

        StateTransitionManager.instance().addTransitionToManager( mockTransitionOne );
        StateTransitionManager.instance().addTransitionToManager( mockTransitionTwo );

        State newState = state.step( mockContext );
        LOG.error( "If the code managed to reach here then the test has failed to perform it's role.  Somehow we have managed to let step create  anew state of ["
                + newState + "]" );
    }

    /**
     * Tests that when there are transitions set up but that none of them are valid transitions, the same state is
     * returned from the step call.
     *
     * @throws StateMachineException if there is an issue with the transition validity
     */
    @SuppressWarnings("boxing")
    @Test
    public void stepwithNonValidTransitionsToReturnSelf() throws StateMachineException {
        State state = new StateImpl( "TestState" );

        when( mockTransitionOne.getStartingState() ).thenReturn( state );
        when( mockTransitionOne.getTransitionName() ).thenReturn( "invalidTransition" );
        when( mockTransitionTwo.getStartingState() ).thenReturn( state );
        when( mockTransitionTwo.getTransitionName() ).thenReturn( "badTransition" );

        when( mockTransitionOne.evaluateTransitionValidity( mockContext ) ).thenReturn( false );
        when( mockTransitionTwo.evaluateTransitionValidity( mockContext ) ).thenReturn( false );

        StateTransitionManager.instance().addTransitionToManager( mockTransitionOne );
        StateTransitionManager.instance().addTransitionToManager( mockTransitionTwo );

        State newState = state.step( mockContext );

        assertThat( state, equalTo( newState ) );
        LOG.debug( "Checked that the step call returns the same state when there are no valid transitions setup" );
    }

    /**
     * Tests that if there are no valid transitions set up for the state that the call to step will not give rise to
     * exceptions and will simply return the same state.
     *
     * @throws StateMachineException if there is an issue with the transition validity
     */
    @Test
    public void stepWithNoTransitionsSetUpReturnsSelf() throws StateMachineException {

        State state = new StateImpl( "TestState" );
        State newState = state.step( mockContext );

        assertThat( state, sameInstance( newState ) );
        LOG.debug( "Checked that the step call returns the same state when there are no transitions setup" );
    }

    /**
     * Tests that when we have set an entry action on a state and we call execute entry action on the state, that the
     * call is propogated down to the execute on teh underlying action.
     *
     * @throws StateMachineException if there is an issue with the transition validity
     */
    @Test
    public void executionOfEntryAction() throws StateMachineException {

        StateImpl state = new StateImpl( "TestState" );
        state.setEntryAction( mockAction );
        state.executeEntryAction( mockContext );
    }

    /**
     * Tests that when we have set an entry action on a state and we call execute entry action on the state, that the
     * call is propogated down to the execute on teh underlying action.
     *
     * @throws StateMachineException if there is an issue with the transition validity
     */
    @Test
    public void executionOfExitAction() throws StateMachineException {

        StateImpl state = new StateImpl( "TestState" );
        state.setExitAction( mockAction );
        state.executeExitAction( mockContext );
    }

    /**
     * Tests the that the equals, hashcode and toString methods work correctly
     */
    @Test
    public void equalsIsTrueWithSameObject() {

        StateImpl state = new StateImpl( "hello" );
        assertThat( state, equalTo( state ) );
    }

    @Test
    public void equalsIsTrueWithSameDataObjects() {
        StateImpl stateA = new StateImpl( "state1" );
        StateImpl stateB = new StateImpl( stateA );
        assertThat( stateA, equalTo( stateB ) );
    }

    @Test
    public void equalsIsFalseAgainstNullObject() {
        StateImpl state = new StateImpl( "helllo" );
        assertThat( state, notNullValue() );
    }

    @Test
    public void equalsFailsWithDataMismatch() {
        StateImpl stateA = new StateImpl( "State A" );
        StateImpl stateNull = new StateImpl( ( String ) null );
        StateImpl stateB = new StateImpl( "State B" );

        assertFalse( stateA.equals( new StateImpl( "boom!" ) ) );
        assertThat( stateA, not( equalTo( stateB ) ) );
        assertThat( stateA, not( equalTo( stateNull ) ) );

    }

    @SuppressWarnings("boxing")
    @Test
    public void hashcodeProducesMatchWithSameValues() {
        StateImpl state1a = new StateImpl( "state1" );
        StateImpl state1b = new StateImpl( state1a );
        StateImpl state2 = new StateImpl( "state2" );

        assertThat( state1a.hashCode(), equalTo( state1b.hashCode() ) );
        assertThat( state1a.hashCode(), not( equalTo( state2.hashCode() ) ) );
    }

    @SuppressWarnings("boxing")
    @Test
    public void hascodeProducesDifferentResultWithDiffereingValues() {
        StateImpl state1a = new StateImpl( "state1" );
        StateImpl state1b = new StateImpl( ( String ) null );

        assertThat( state1a.hashCode(), not( equalTo( state1b.hashCode() ) ) );

    }
}
