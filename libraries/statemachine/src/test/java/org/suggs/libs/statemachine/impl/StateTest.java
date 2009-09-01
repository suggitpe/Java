/*
 * StateTest.java created on 28 Aug 2009 18:15:18 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine.impl;

import org.suggs.libs.statemachine.IState;
import org.suggs.libs.statemachine.IStateMachineContext;
import org.suggs.libs.statemachine.IStateTransition;
import org.suggs.libs.statemachine.StateMachineException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test suite for the state implementation.
 * 
 * @author suggitpe
 * @version 1.0 28 Aug 2009
 */
public class StateTest
{

    private static final Log LOG = LogFactory.getLog( StateTest.class );

    @BeforeClass
    public static void doBeforeClass()
    {
        LOG.debug( "===================" + StateTest.class.getSimpleName() );
    }

    @Before
    public void doBefore()
    {
        LOG.debug( "------------------- " );
        StateTransitionManager.instance().clearTransitionsFromTransitionManager();
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

        Assert.assertEquals( state.getStateName(), STATE_NAME );
        LOG.debug( "Successfully created state[" + state + "]" );
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
        IStateMachineContext context = EasyMock.createMock( IStateMachineContext.class );
        IStateTransition trans1 = EasyMock.createMock( IStateTransition.class );
        IStateTransition trans2 = EasyMock.createMock( IStateTransition.class );

        EasyMock.expect( trans1.getStartingState() ).andReturn( state ).anyTimes();
        EasyMock.expect( trans1.getTransitionName() ).andReturn( "invalidTransition" ).anyTimes();
        EasyMock.expect( trans2.getStartingState() ).andReturn( state ).anyTimes();
        EasyMock.expect( trans2.getTransitionName() ).andReturn( "badTransition" ).anyTimes();
        EasyMock.expect( trans2.getEndingState() ).andReturn( endState ).anyTimes();

        EasyMock.expect( trans1.evaluateTransitionValidity( context ) ).andReturn( false );
        EasyMock.expect( trans2.evaluateTransitionValidity( context ) ).andReturn( true );

        // replay mocks
        EasyMock.replay( context );
        EasyMock.replay( trans1 );
        EasyMock.replay( trans2 );

        // load transmanager
        StateTransitionManager.instance().addTransitionToManager( trans1 );
        StateTransitionManager.instance().addTransitionToManager( trans2 );

        // test exec
        IState newState = state.step( context );

        Assert.assertNotSame( state, newState );
        Assert.assertSame( endState, newState );
        LOG.debug( "Checked that the step call returns a different state when there are valid transitions setup" );

        // verify mocks
        EasyMock.verify( context );
        EasyMock.verify( trans1 );
        EasyMock.verify( trans2 );
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
        IStateMachineContext context = EasyMock.createMock( IStateMachineContext.class );
        IStateTransition trans1 = EasyMock.createMock( IStateTransition.class );
        IStateTransition trans2 = EasyMock.createMock( IStateTransition.class );

        EasyMock.expect( trans1.getStartingState() ).andReturn( state ).anyTimes();
        EasyMock.expect( trans1.getTransitionName() ).andReturn( "invalidTransition" ).anyTimes();
        EasyMock.expect( trans2.getStartingState() ).andReturn( state ).anyTimes();
        EasyMock.expect( trans2.getTransitionName() ).andReturn( "badTransition" ).anyTimes();
        EasyMock.expect( trans2.getEndingState() ).andReturn( endState ).anyTimes();

        EasyMock.expect( trans1.evaluateTransitionValidity( context ) ).andReturn( true );
        EasyMock.expect( trans2.evaluateTransitionValidity( context ) ).andReturn( true );

        // replay mocks
        EasyMock.replay( context );
        EasyMock.replay( trans1 );
        EasyMock.replay( trans2 );

        // load transmanager
        StateTransitionManager.instance().addTransitionToManager( trans1 );
        StateTransitionManager.instance().addTransitionToManager( trans2 );

        // test exec
        IState newState = state.step( context );
        LOG.error( "If the code managed to reach here then the test has failed to perform it's role.  Somehow we have managed to let step create  anew state of ["
                   + newState + "]" );

        // verify mocks
        EasyMock.verify( context );
        EasyMock.verify( trans1 );
        EasyMock.verify( trans2 );
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
        IStateMachineContext context = EasyMock.createMock( IStateMachineContext.class );
        IStateTransition trans1 = EasyMock.createMock( IStateTransition.class );
        IStateTransition trans2 = EasyMock.createMock( IStateTransition.class );

        EasyMock.expect( trans1.getStartingState() ).andReturn( state ).anyTimes();
        EasyMock.expect( trans1.getTransitionName() ).andReturn( "invalidTransition" ).anyTimes();
        EasyMock.expect( trans2.getStartingState() ).andReturn( state ).anyTimes();
        EasyMock.expect( trans2.getTransitionName() ).andReturn( "badTransition" ).anyTimes();

        EasyMock.expect( trans1.evaluateTransitionValidity( context ) ).andReturn( false );
        EasyMock.expect( trans2.evaluateTransitionValidity( context ) ).andReturn( false );

        // replay mocks
        EasyMock.replay( context );
        EasyMock.replay( trans1 );
        EasyMock.replay( trans2 );

        // load transmanager
        StateTransitionManager.instance().addTransitionToManager( trans1 );
        StateTransitionManager.instance().addTransitionToManager( trans2 );

        // test exec
        IState newState = state.step( context );

        Assert.assertSame( state, newState );
        LOG.debug( "Checked that the step call returns the same state when there are no valid transitions setup" );

        // verify mocks
        EasyMock.verify( context );
        EasyMock.verify( trans1 );
        EasyMock.verify( trans2 );
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
        // set up mocks
        IStateMachineContext context = EasyMock.createMock( IStateMachineContext.class );

        // replay mocks
        EasyMock.replay( context );

        // test exec
        IState state = new StateImpl( "TestState" );
        IState newState = state.step( context );

        Assert.assertSame( state, newState );
        LOG.debug( "Checked that the step call returns the same state when there are no transitions setup" );

        // verify mocks
        EasyMock.verify( context );
    }
}
