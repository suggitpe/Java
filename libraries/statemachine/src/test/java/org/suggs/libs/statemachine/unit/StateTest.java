/*
 * StateTest.java created on 28 Aug 2009 18:15:18 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine.unit;

import org.suggs.libs.statemachine.IState;
import org.suggs.libs.statemachine.IStateMachineContext;
import org.suggs.libs.statemachine.IStateTransition;
import org.suggs.libs.statemachine.StateMachineException;
import org.suggs.libs.statemachine.impl.StateImpl;
import org.suggs.libs.statemachine.impl.StateTransitionManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.easymock.EasyMock.createControl;
import static org.easymock.EasyMock.expect;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

/**
 * Test suite for the state implementation.
 * 
 * @author suggitpe
 * @version 1.0 28 Aug 2009
 */
public class StateTest
{

    private static final Log LOG = LogFactory.getLog( StateTest.class );
    private IMocksControl ctrl_;

    /** */
    @BeforeClass
    public static void doBeforeClass()
    {
        LOG.debug( "===================" + StateTest.class.getSimpleName() );
    }

    /** */
    @Before
    public void doBefore()
    {
        LOG.debug( "------------------- " );
        StateTransitionManager.instance().clearTransitionsFromTransitionManager();
        ctrl_ = createControl();
    }

    /**
     * Simply tests that the state name has been correctly set at
     * state construction
     */
    @Test
    public void testStateNameExtraction()
    {
        final String STATE_NAME = "TestStateForTest";
        IState state = new StateImpl( STATE_NAME );

        assertThat( state.getStateName(), equalTo( STATE_NAME ) );
        LOG.debug( "Successfully created state[" + state + "]" );
    }

    /**
     * Tests the that the equals, hashcode and toString methods work
     * correctly
     */
    @SuppressWarnings("boxing")
    @Test
    public void testEqualsHashcodeAndToString()
    {
        StateImpl state1a = new StateImpl( "state1" );
        StateImpl state1b = new StateImpl( "state1" );
        StateImpl state2 = new StateImpl( "state2" );

        // check equals method
        assertThat( state1a, not( ( sameInstance( state1b ) ) ) );
        assertThat( state1a, not( ( sameInstance( state2 ) ) ) );
        assertThat( state1a, equalTo( state1b ) );
        assertThat( state1a, not( equalTo( state2 ) ) );

        // check hashcode
        assertThat( state1a.hashCode(), equalTo( state1b.hashCode() ) );
        assertThat( state1a.hashCode(), not( equalTo( state2.hashCode() ) ) );

        LOG.debug( "State1a: " + state1a );
        LOG.debug( "State2: " + state2 );
    }

    /**
     * Tests that when there are valid transitions set up, step will
     * return the correct new end state.
     * 
     * @throws StateMachineException
     */
    @SuppressWarnings("boxing")
    @Test
    public void testStepWithValidTransitionsToReturnNewState() throws StateMachineException
    {
        IState state = new StateImpl( "TestState" );
        IState endState = new StateImpl( "TestEndState" );

        // set up mocks
        IStateMachineContext context = ctrl_.createMock( IStateMachineContext.class );
        IStateTransition trans1 = ctrl_.createMock( IStateTransition.class );
        IStateTransition trans2 = ctrl_.createMock( IStateTransition.class );

        expect( trans1.getStartingState() ).andReturn( state ).anyTimes();
        expect( trans1.getTransitionName() ).andReturn( "invalidTransition" ).anyTimes();
        expect( trans2.getStartingState() ).andReturn( state ).anyTimes();
        expect( trans2.getTransitionName() ).andReturn( "badTransition" ).anyTimes();
        expect( trans2.getEndingState() ).andReturn( endState ).anyTimes();

        expect( trans1.evaluateTransitionValidity( context ) ).andReturn( false );
        expect( trans2.evaluateTransitionValidity( context ) ).andReturn( true );

        ctrl_.replay();

        StateTransitionManager.instance().addTransitionToManager( trans1 );
        StateTransitionManager.instance().addTransitionToManager( trans2 );

        IState newState = state.step( context );

        assertThat( state, not( equalTo( newState ) ) );
        assertThat( endState, equalTo( newState ) );
        LOG.debug( "Checked that the step call returns a different state when there are valid transitions setup" );

        ctrl_.verify();
    }

    /**
     * Tests that when there are more than one valid transitions set
     * up, step will give rise to an exception being thrown.
     * 
     * @throws StateMachineException
     */
    @SuppressWarnings("boxing")
    @Test(expected = StateMachineException.class)
    public void testStepWithTwoValidTransitionsCausesException() throws StateMachineException
    {
        IState state = new StateImpl( "TestState" );
        IState endState = new StateImpl( "TestEndState" );

        // set up mocks
        IStateMachineContext context = ctrl_.createMock( IStateMachineContext.class );
        IStateTransition trans1 = ctrl_.createMock( IStateTransition.class );
        IStateTransition trans2 = ctrl_.createMock( IStateTransition.class );

        expect( trans1.getStartingState() ).andReturn( state ).anyTimes();
        expect( trans1.getTransitionName() ).andReturn( "invalidTransition" ).anyTimes();
        expect( trans2.getStartingState() ).andReturn( state ).anyTimes();
        expect( trans2.getTransitionName() ).andReturn( "badTransition" ).anyTimes();
        expect( trans2.getEndingState() ).andReturn( endState ).anyTimes();

        expect( trans1.evaluateTransitionValidity( context ) ).andReturn( true );
        expect( trans2.evaluateTransitionValidity( context ) ).andReturn( true );

        ctrl_.replay();

        StateTransitionManager.instance().addTransitionToManager( trans1 );
        StateTransitionManager.instance().addTransitionToManager( trans2 );

        IState newState = state.step( context );
        LOG.error( "If the code managed to reach here then the test has failed to perform it's role.  Somehow we have managed to let step create  anew state of ["
                   + newState + "]" );

        ctrl_.verify();
    }

    /**
     * Tests that when there are transitions set up but that none of
     * them are valid transitions, the same state is returned from the
     * step call.
     * 
     * @throws StateMachineException
     */
    @SuppressWarnings("boxing")
    @Test
    public void testStepwithNonValidTransitionsToReturnSelf() throws StateMachineException
    {
        IState state = new StateImpl( "TestState" );

        // set up mocks
        IStateMachineContext context = ctrl_.createMock( IStateMachineContext.class );
        IStateTransition trans1 = ctrl_.createMock( IStateTransition.class );
        IStateTransition trans2 = ctrl_.createMock( IStateTransition.class );

        expect( trans1.getStartingState() ).andReturn( state ).anyTimes();
        expect( trans1.getTransitionName() ).andReturn( "invalidTransition" ).anyTimes();
        expect( trans2.getStartingState() ).andReturn( state ).anyTimes();
        expect( trans2.getTransitionName() ).andReturn( "badTransition" ).anyTimes();

        expect( trans1.evaluateTransitionValidity( context ) ).andReturn( false );
        expect( trans2.evaluateTransitionValidity( context ) ).andReturn( false );

        ctrl_.replay();

        StateTransitionManager.instance().addTransitionToManager( trans1 );
        StateTransitionManager.instance().addTransitionToManager( trans2 );

        IState newState = state.step( context );

        assertThat( state, equalTo( newState ) );
        LOG.debug( "Checked that the step call returns the same state when there are no valid transitions setup" );

        ctrl_.verify();
    }

    /**
     * Tests that if there are no valid transitions set up for the
     * state that the call to step will not give rise to exceptions
     * and will simply return the same state.
     * 
     * @throws StateMachineException
     */
    @Test
    public void testStepWithNoTransitionsSetUpReturnsSelf() throws StateMachineException
    {
        IStateMachineContext context = ctrl_.createMock( IStateMachineContext.class );

        ctrl_.replay();

        IState state = new StateImpl( "TestState" );
        IState newState = state.step( context );

        assertThat( state, equalTo( newState ) );
        LOG.debug( "Checked that the step call returns the same state when there are no transitions setup" );

        ctrl_.verify();
    }
}
