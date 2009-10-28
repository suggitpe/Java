/*
 * StateMachineTest.java created on 26 Aug 2009 07:09:34 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine.unit;

import org.suggs.libs.statemachine.IState;
import org.suggs.libs.statemachine.IStateMachine;
import org.suggs.libs.statemachine.IStateMachineContext;
import org.suggs.libs.statemachine.StateMachineException;
import org.suggs.libs.statemachine.impl.StateMachineImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.easymock.EasyMock.createControl;
import static org.easymock.EasyMock.expect;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Test suite for the state machine implementation.
 * 
 * @author suggitpe
 * @version 1.0 26 Aug 2009
 */
public class StateMachineTest
{

    private static final Log LOG = LogFactory.getLog( StateMachineTest.class );

    private IMocksControl mCtrl_;

    /** */
    @BeforeClass
    public static void doBeforeClass()
    {
        LOG.debug( "===================" + StateMachineTest.class.getSimpleName() );
    }

    /** */
    @Before
    public void doBefore()
    {
        LOG.debug( "------------------- " );
        mCtrl_ = createControl();
    }

    /**
     * Tests that when we create a state machine with a state and then
     * call the getter for the state that the same one is returned.
     */
    @Test
    public void testStateMachineInitiation()
    {
        IState initialStateMock = mCtrl_.createMock( IState.class );
        expect( initialStateMock.getStateName() ).andReturn( "InitialState" ).anyTimes();

        mCtrl_.replay();

        IStateMachine stateMachine = new StateMachineImpl( initialStateMock );
        IState curState = stateMachine.getCurrentState();
        LOG.debug( "Current state of the state machine is [" + curState.getStateName() + "]" );

        assertThat( initialStateMock, equalTo( curState ) );
        LOG.debug( "Singleton and initialisation state are the same" );

        mCtrl_.verify();
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
        IStateMachineContext contextMock = mCtrl_.createMock( IStateMachineContext.class );
        IState newStateMock = mCtrl_.createMock( IState.class );
        IState initialStateMock = mCtrl_.createMock( IState.class );

        expect( initialStateMock.getStateName() ).andReturn( "InitialState" ).anyTimes();
        expect( initialStateMock.step( contextMock ) ).andReturn( newStateMock );
        expect( newStateMock.getStateName() ).andReturn( "NewState" ).anyTimes();
        expect( newStateMock.step( contextMock ) ).andReturn( newStateMock );

        // replay
        mCtrl_.replay();

        IStateMachine stateMachine = new StateMachineImpl( initialStateMock );
        LOG.debug( "Starting test in state=[" + stateMachine.getCurrentState().getStateName() + "]" );
        stateMachine.step( contextMock );
        LOG.debug( "Ending up test in state=[" + stateMachine.getCurrentState().getStateName()
                   + "]" );

        assertThat( stateMachine.getCurrentState(), equalTo( newStateMock ) );

        mCtrl_.verify();
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
        IState initialStateMock = mCtrl_.createMock( IState.class );
        IStateMachineContext contextMock = mCtrl_.createMock( IStateMachineContext.class );

        expect( initialStateMock.getStateName() ).andReturn( "InitialState" ).anyTimes();
        expect( initialStateMock.step( contextMock ) ).andReturn( initialStateMock );

        mCtrl_.replay();

        IStateMachine stateMachine = new StateMachineImpl( initialStateMock );
        LOG.debug( "Starting test in state=[" + stateMachine.getCurrentState().getStateName() + "]" );
        stateMachine.step( contextMock );
        LOG.debug( "Ending up test in state=[" + stateMachine.getCurrentState().getStateName()
                   + "]" );

        assertThat( stateMachine.getCurrentState(), equalTo( initialStateMock ) );

        mCtrl_.verify();
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
        IState initialStateMock = mCtrl_.createMock( IState.class );
        IStateMachineContext contextMock = mCtrl_.createMock( IStateMachineContext.class );

        expect( initialStateMock.getStateName() ).andReturn( "InitialState" ).anyTimes();
        expect( initialStateMock.step( contextMock ) ).andReturn( null );

        // replay
        mCtrl_.replay();

        // exec tests
        IStateMachine stateMachine = new StateMachineImpl( initialStateMock );
        LOG.debug( "Starting test in state=[" + stateMachine.getCurrentState().getStateName() + "]" );
        stateMachine.step( contextMock );
        LOG.debug( "Ending up test in state=[" + stateMachine.getCurrentState().getStateName()
                   + "]" );
        assertThat( stateMachine.getCurrentState(), equalTo( initialStateMock ) );

        // verify mocks
        mCtrl_.verify();
    }
}
