/*
 * StateMachineTest.java created on 26 Aug 2009 07:09:34 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine.impl;

import org.suggs.libs.statemachine.IState;
import org.suggs.libs.statemachine.IStateMachine;
import org.suggs.libs.statemachine.IStateMachineContext;
import org.suggs.libs.statemachine.StateMachineException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test suite for the state machine implementation.
 * 
 * @author suggitpe
 * @version 1.0 26 Aug 2009
 */
public class StateMachineTest
{

    private static final Log LOG = LogFactory.getLog( StateMachineTest.class );

    @BeforeClass
    public static void doBeforeClass()
    {
        LOG.debug( "===================" + StateMachineTest.class.getSimpleName() );
    }

    @Before
    public void doBefore()
    {
        LOG.debug( "------------------- " );
    }

    /**
     * Tests that when we create a state machine with a state and then
     * call the getter for the state that the same one is returned.
     */
    @Test
    public void testStateMachineInitiation()
    {
        // create mocks
        IState initialStateMock = EasyMock.createMock( IState.class );
        EasyMock.expect( initialStateMock.getStateName() ).andReturn( "InitialState" ).anyTimes();

        // replay
        EasyMock.replay( initialStateMock );

        // exec tests
        IStateMachine stateMachine = new StateMachineImpl( initialStateMock );
        IState curState = stateMachine.getCurrentState();
        LOG.debug( "Current state of the state machine is [" + curState.getStateName() + "]" );

        Assert.assertEquals( initialStateMock, curState );
        LOG.debug( "Singleton and initialisation state are the same" );

        // verify mocks
        EasyMock.verify( initialStateMock );
    }

    /**
     * Tests that when SM calls step on a state and the state returns
     * with a new state, that we actually transition to that new
     * state.
     * 
     * @throws StateMachineException
     */
    @Test
    public void testStepResultsInNewCurrentState() throws StateMachineException
    {
        // create mocks
        IStateMachineContext contextMock = EasyMock.createMock( IStateMachineContext.class );
        IState newStateMock = EasyMock.createMock( IState.class );
        IState initialStateMock = EasyMock.createMock( IState.class );

        EasyMock.expect( initialStateMock.getStateName() ).andReturn( "InitialState" ).anyTimes();
        EasyMock.expect( initialStateMock.step( contextMock ) ).andReturn( newStateMock );
        EasyMock.expect( newStateMock.getStateName() ).andReturn( "NewState" ).anyTimes();
        EasyMock.expect( newStateMock.step( contextMock ) ).andReturn( newStateMock );

        // replay
        EasyMock.replay( initialStateMock );
        EasyMock.replay( newStateMock );
        EasyMock.replay( contextMock );

        // exec tests
        IStateMachine stateMachine = new StateMachineImpl( initialStateMock );
        LOG.debug( "Starting test in state=[" + stateMachine.getCurrentState().getStateName() + "]" );
        stateMachine.step( contextMock );
        LOG.debug( "Ending up test in state=[" + stateMachine.getCurrentState().getStateName()
                   + "]" );

        Assert.assertEquals( stateMachine.getCurrentState(), newStateMock );

        // verify mocks
        EasyMock.verify( initialStateMock );
        EasyMock.verify( newStateMock );
        EasyMock.verify( contextMock );
    }

    /**
     * Tests that when a step call returns the same object that we do
     * not then transition to a new state.
     * 
     * @throws StateMachineException
     */
    @Test
    public void testNoStepResultsInSameCurrentState() throws StateMachineException
    {
        // create mocks
        IState initialStateMock = EasyMock.createMock( IState.class );
        IStateMachineContext contextMock = EasyMock.createMock( IStateMachineContext.class );

        EasyMock.expect( initialStateMock.getStateName() ).andReturn( "InitialState" ).anyTimes();
        EasyMock.expect( initialStateMock.step( contextMock ) ).andReturn( initialStateMock );

        // replay
        EasyMock.replay( initialStateMock );
        EasyMock.replay( contextMock );

        // exec tests
        IStateMachine stateMachine = new StateMachineImpl( initialStateMock );
        LOG.debug( "Starting test in state=[" + stateMachine.getCurrentState().getStateName() + "]" );
        stateMachine.step( contextMock );
        LOG.debug( "Ending up test in state=[" + stateMachine.getCurrentState().getStateName()
                   + "]" );

        Assert.assertEquals( stateMachine.getCurrentState(), initialStateMock );

        // verify mocks
        EasyMock.verify( initialStateMock );
        EasyMock.verify( contextMock );
    }

    /**
     * Tests that when a step call returns a null object that we do
     * not then transition to a new state.
     * 
     * @throws StateMachineException
     *             from the call to step
     */
    @Test
    public void testNullStepResultsInSameCurrentState() throws StateMachineException
    {
        // create mocks
        IState initialStateMock = EasyMock.createMock( IState.class );
        IStateMachineContext contextMock = EasyMock.createMock( IStateMachineContext.class );

        EasyMock.expect( initialStateMock.getStateName() ).andReturn( "InitialState" ).anyTimes();
        EasyMock.expect( initialStateMock.step( contextMock ) ).andReturn( null );

        // replay
        EasyMock.replay( initialStateMock );
        EasyMock.replay( contextMock );

        // exec tests
        IStateMachine stateMachine = new StateMachineImpl( initialStateMock );
        LOG.debug( "Starting test in state=[" + stateMachine.getCurrentState().getStateName() + "]" );
        stateMachine.step( contextMock );
        LOG.debug( "Ending up test in state=[" + stateMachine.getCurrentState().getStateName()
                   + "]" );

        // verify mocks
        EasyMock.verify( initialStateMock );
        EasyMock.verify( contextMock );
    }
}
