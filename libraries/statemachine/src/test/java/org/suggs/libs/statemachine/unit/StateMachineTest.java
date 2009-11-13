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
import static org.easymock.EasyMock.createMock;
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

    private IMocksControl ctrl_;
    private IState mockInitialState_;
    private IState mockNewState_;
    private IStateMachineContext mockContext_;

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
        ctrl_ = createControl();
        mockInitialState_ = ctrl_.createMock( IState.class );
        mockNewState_ = createMock( IState.class );
        mockContext_ = ctrl_.createMock( IStateMachineContext.class );
    }

    /**
     * Tests that when we create a state machine with a state and then
     * call the getter for the state that the same one is returned.
     */
    @Test
    public void testStateMachineInitiation()
    {
        expect( mockInitialState_.getStateName() ).andReturn( "InitialState" ).anyTimes();

        ctrl_.replay();

        IStateMachine stateMachine = new StateMachineImpl( mockInitialState_ );
        IState curState = stateMachine.getCurrentState();

        LOG.debug( "Expecting that the state machine is in the correct initial state" );
        assertThat( mockInitialState_, equalTo( curState ) );

        ctrl_.verify();
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
        expect( mockInitialState_.step( mockContext_ ) ).andReturn( mockNewState_ );
        expect( mockNewState_.step( mockContext_ ) ).andReturn( mockNewState_ );

        ctrl_.replay();

        IStateMachine stateMachine = new StateMachineImpl( mockInitialState_ );
        stateMachine.step( mockContext_ );

        LOG.debug( "Expecting that the state machine has transitioned to the new state due to new state returned." );
        assertThat( stateMachine.getCurrentState(), equalTo( mockNewState_ ) );

        ctrl_.verify();
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
        expect( mockInitialState_.step( mockContext_ ) ).andReturn( mockInitialState_ );

        ctrl_.replay();

        IStateMachine stateMachine = new StateMachineImpl( mockInitialState_ );
        stateMachine.step( mockContext_ );

        LOG.debug( "Expecting that the state machine has remained in the initial state due to same state returned" );
        assertThat( stateMachine.getCurrentState(), equalTo( mockInitialState_ ) );

        ctrl_.verify();
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
        expect( mockInitialState_.step( mockContext_ ) ).andReturn( null );

        ctrl_.replay();

        IStateMachine stateMachine = new StateMachineImpl( mockInitialState_ );
        stateMachine.step( mockContext_ );

        LOG.debug( "Expecting that the state machine remains in the initial state due to NULL step result" );
        assertThat( stateMachine.getCurrentState(), equalTo( mockInitialState_ ) );

        ctrl_.verify();
    }
}
