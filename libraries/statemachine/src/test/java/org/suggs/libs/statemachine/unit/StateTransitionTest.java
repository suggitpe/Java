/*
 * StateTransitionTest.java created on 1 Sep 2009 07:20:51 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine.unit;

import org.suggs.libs.statemachine.IState;
import org.suggs.libs.statemachine.IStateMachineContext;
import org.suggs.libs.statemachine.IStateTransition;
import org.suggs.libs.statemachine.IStateTransitionEvent;
import org.suggs.libs.statemachine.StateMachineException;
import org.suggs.libs.statemachine.impl.StateImpl;
import org.suggs.libs.statemachine.impl.StateTransitionEventImpl;
import org.suggs.libs.statemachine.impl.StateTransitionImpl;
import org.suggs.libs.statemachine.support.FalseGuardStub;
import org.suggs.libs.statemachine.support.TrueGuardStub;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymock.IMocksControl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.easymock.EasyMock.createControl;
import static org.easymock.EasyMock.expect;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Test suite for the state transition implementation.
 * 
 * @author suggitpe
 * @version 1.0 1 Sep 2009
 */
public class StateTransitionTest
{

    private static final Log LOG = LogFactory.getLog( StateTransitionTest.class );

    private IMocksControl ctrl_;

    private IState startState_;
    private IState endState_;
    private IStateMachineContext context_;

    /** */
    @BeforeClass
    public static void doBeforeClass()
    {
        LOG.debug( "===================" + StateTransitionTest.class.getSimpleName() );
    }

    /** */
    @Before
    public void doBefore()
    {
        LOG.debug( "------------------- " );
        ctrl_ = createControl();
        startState_ = ctrl_.createMock( IState.class );
        endState_ = ctrl_.createMock( IState.class );
        context_ = ctrl_.createMock( IStateMachineContext.class );
    }

    /**
     * Simply tests that the attributes of the transition set at the
     * time of the construction are correctly kept throughout.
     */
    @Test
    public void testTransitionNameExtraction()
    {
        final String transName = "TestStateTransition";
        IState startState = new StateImpl( "TestStartState" );
        IState endState = new StateImpl( "TestEndState" );
        IStateTransition transition = new StateTransitionImpl( transName, startState, endState );

        assertThat( startState, equalTo( transition.getStartingState() ) );
        assertThat( endState, equalTo( transition.getEndingState() ) );
        assertThat( transName, equalTo( transition.getTransitionName() ) );

        LOG.debug( "Verified that the objects set at construction are correctly persisted into the object" );
    }

    /**
     * Tests the that the equals, hashcode and toString methods work
     * correctly
     */
    @SuppressWarnings("boxing")
    @Test
    public void testEqualsHashcodeAndToString()
    {
        StateImpl state1a = new StateImpl( "startState1" );
        StateImpl state1b = new StateImpl( "startState1" );
        StateImpl state2 = new StateImpl( "startState2" );

        StateImpl endState1a = new StateImpl( "endState1" );
        StateImpl endState1b = new StateImpl( "endState1" );
        StateImpl endState2 = new StateImpl( "endState2" );

        StateTransitionImpl trans1a = new StateTransitionImpl( "stateTransition1",
                                                               state1a,
                                                               endState1a );
        StateTransitionImpl trans1b = new StateTransitionImpl( "stateTransition1",
                                                               state1b,
                                                               endState1b );
        StateTransitionImpl trans2 = new StateTransitionImpl( "stateTransition2", state2, endState2 );

        // check equals method
        assertThat( trans1a, not( sameInstance( trans1b ) ) );
        assertThat( trans1a, not( sameInstance( trans2 ) ) );
        assertThat( trans1a, equalTo( trans1b ) );
        assertThat( trans1a, not( equalTo( trans2 ) ) );

        // check hashcode
        assertThat( trans1a.hashCode(), equalTo( trans1b.hashCode() ) );
        assertThat( trans1a.hashCode(), not( equalTo( trans2.hashCode() ) ) );

        LOG.debug( "StateTransition1a: " + trans1a );
        LOG.debug( "StateTransition2: " + trans2 );
    }

    /**
     * Tests that when a null context is passed into the evaluation
     * method, that an exception is thrown.
     * 
     * @throws StateMachineException
     *             from the evaluation call. We expect that an
     *             exception is thrown during the execution of this
     *             test.
     */
    @Test(expected = StateMachineException.class)
    public void testTransitionEvaluationForNullContext() throws StateMachineException
    {

        ctrl_.replay();

        IStateTransition target = new StateTransitionImpl( "testTransition", startState_, endState_ );
        LOG.debug( "Calling evaluate on the transition to endure that it throws an exception" );
        target.evaluateTransitionValidity( null );

        fail( "The test should have thrown an execption so this should not be seen" );

        ctrl_.verify();
    }

    /**
     * Tests that when an event context with a matching transition
     * event is passed into the transition, it will state that the
     * transition is valid.
     * 
     * @throws StateMachineException
     *             this should not happen for this test
     */
    @SuppressWarnings("boxing")
    @Test
    public void testTranitionEventEvaluationForValidEvent() throws StateMachineException
    {
        expect( context_.getStateTransitionEvent() ).andReturn( new StateTransitionEventImpl( "testEvent" ) )
            .anyTimes();

        ctrl_.replay();

        StateTransitionImpl target = new StateTransitionImpl( "testTransition",
                                                              startState_,
                                                              endState_ );
        IStateTransitionEvent evt = new StateTransitionEventImpl( "testEvent" );
        target.addTransitionEvent( evt );

        boolean result = target.evaluateTransitionValidity( context_ );
        LOG.debug( "For the valid event [" + evt + "] we are expecting true and we got [" + result
                   + "]" );
        assertThat( result, is( true ) );

        ctrl_.verify();
    }

    /**
     * Tests that when a state transition is evaluated with no events
     * that it will evaluate to true.
     * 
     * @throws StateMachineException
     *             this should not happen for this test
     */
    @SuppressWarnings("boxing")
    @Test
    public void testTransitionEventEvaluationForNoEvents() throws StateMachineException
    {
        IStateTransitionEvent evt = new StateTransitionEventImpl( "testEvent" );
        expect( context_.getStateTransitionEvent() ).andReturn( evt ).anyTimes();

        ctrl_.replay();

        StateTransitionImpl target = new StateTransitionImpl( "testTransition",
                                                              startState_,
                                                              endState_ );
        // no events set on the transition
        boolean result = target.evaluateTransitionValidity( context_ );
        LOG.debug( "For no set transition events we are expecting true and we got [" + result + "]" );
        assertThat( result, is( true ) );

        ctrl_.verify();
    }

    /**
     * Tests that when a state transition has events that do not match
     * with the context event that it will return false
     * 
     * @throws StateMachineException
     *             this should not happen for this test
     */
    @SuppressWarnings("boxing")
    @Test
    public void testTransitionEventEvaluationForNoValidEvents() throws StateMachineException
    {
        expect( context_.getStateTransitionEvent() ).andReturn( new StateTransitionEventImpl( "notMatchingEvent" ) )
            .anyTimes();

        ctrl_.replay();

        StateTransitionImpl target = new StateTransitionImpl( "testTransition",
                                                              startState_,
                                                              endState_ );
        IStateTransitionEvent evt = new StateTransitionEventImpl( "testEvent" );
        target.addTransitionEvent( evt );

        boolean result = target.evaluateTransitionValidity( context_ );
        LOG.debug( "For the invalid event [" + evt + "] we are expecting false and we got ["
                   + result + "]" );

        assertThat( result, is( false ) );

        ctrl_.verify();
    }

    /**
     * Tests that if the event collection on the state transition is
     * null then we return true (similar to no events)
     * 
     * @throws StateMachineException
     *             this should not happen for this test
     */
    @SuppressWarnings("boxing")
    @Test
    public void testTransitionEventEvaluationForNullEvents() throws StateMachineException
    {

        expect( context_.getStateTransitionEvent() ).andReturn( new StateTransitionEventImpl( "notMatchingEvent" ) )
            .anyTimes();

        ctrl_.replay();

        StateTransitionImpl target = new StateTransitionImpl( "testTransition",
                                                              startState_,
                                                              endState_ );
        target.setTransitionEvents( null );

        boolean result = target.evaluateTransitionValidity( context_ );
        LOG.debug( "For transition events we are expecting true and we got [" + result + "]" );
        assertThat( result, is( true ) );

        ctrl_.verify();
    }

    /**
     * Tests that when we evaluate a state transition with a valid
     * guard that it evaluates to true.
     * 
     * @throws StateMachineException
     */
    @SuppressWarnings("boxing")
    @Test
    public void testTranitionEventEvaluationForValidGuard() throws StateMachineException
    {
        ctrl_.replay();

        StateTransitionImpl target = new StateTransitionImpl( "testTransition",
                                                              startState_,
                                                              endState_ );
        target.addTransitionGuard( new TrueGuardStub() );

        boolean result = target.evaluateTransitionValidity( context_ );
        LOG.debug( "For transition guards we are expecting true and we got [" + result + "]" );
        assertThat( result, is( true ) );

        ctrl_.verify();
    }

    /**
     * Tests that when we evaluate a state transition with one valid
     * guard and one invalid guard that it evaluates false.
     * 
     * @throws StateMachineException
     */
    @SuppressWarnings("boxing")
    @Test
    public void testTranitionEventEvaluationForValidGuardAndInvalidGuard()
                    throws StateMachineException
    {
        ctrl_.replay();

        StateTransitionImpl target = new StateTransitionImpl( "testTransition",
                                                              startState_,
                                                              endState_ );
        target.addTransitionGuard( new FalseGuardStub() );
        target.addTransitionGuard( new TrueGuardStub() );

        boolean result = target.evaluateTransitionValidity( context_ );
        LOG.debug( "For transition guards we are expecting false and we got [" + result + "]" );
        assertThat( result, is( false ) );

        ctrl_.verify();
    }

    /**
     * Tests that if we evaluiate a state transition with no guards
     * that it evaluates to true
     * 
     * @throws StateMachineException
     */
    @SuppressWarnings("boxing")
    @Test
    public void testTransitionEventEvaluationForNoGuards() throws StateMachineException
    {
        ctrl_.replay();

        StateTransitionImpl target = new StateTransitionImpl( "testTransition",
                                                              startState_,
                                                              endState_ );

        boolean result = target.evaluateTransitionValidity( context_ );
        LOG.debug( "For no transition guards we are expecting true and we got [" + result + "]" );
        assertThat( result, is( true ) );

        ctrl_.verify();
    }

    /**
     * Tests that when we evaluate a state transition with only
     * invalid guards that it evaluates to false
     * 
     * @throws StateMachineException
     */
    @SuppressWarnings("boxing")
    @Test
    public void testTransitionEventEvaluationForNoValidGuards() throws StateMachineException
    {
        ctrl_.replay();

        StateTransitionImpl target = new StateTransitionImpl( "testTransition",
                                                              startState_,
                                                              endState_ );
        target.addTransitionGuard( new FalseGuardStub() );

        boolean result = target.evaluateTransitionValidity( context_ );
        LOG.debug( "For no valid transition guards we are expecting false and we got [" + result
                   + "]" );
        assertThat( result, is( false ) );

        ctrl_.verify();
    }

    /**
     * Tests that when the guard collection is null that we return
     * true
     * 
     * @throws StateMachineException
     */
    @Test
    public void testTransitionEventEvaluationForNullGuards() throws StateMachineException
    {
        ctrl_.replay();

        StateTransitionImpl target = new StateTransitionImpl( "testTransition",
                                                              startState_,
                                                              endState_ );
        target.setTransitionGuards( null );

        boolean result = target.evaluateTransitionValidity( context_ );
        LOG.debug( "For null transition guards we are expecting true and we got [" + result + "]" );
        Assert.assertTrue( result );

        ctrl_.verify();
    }

}
