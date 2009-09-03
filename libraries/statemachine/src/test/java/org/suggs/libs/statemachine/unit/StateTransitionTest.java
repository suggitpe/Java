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
import org.suggs.libs.statemachine.stub.FalseGuardStub;
import org.suggs.libs.statemachine.stub.TrueGuardStub;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test suite for the state transition implementation.
 * 
 * @author suggitpe
 * @version 1.0 1 Sep 2009
 */
public class StateTransitionTest
{

    private static final Log LOG = LogFactory.getLog( StateTransitionTest.class );

    @BeforeClass
    public static void doBeforeClass()
    {
        LOG.debug( "===================" + StateTransitionTest.class.getSimpleName() );
    }

    @Before
    public void doBefore()
    {
        LOG.debug( "------------------- " );
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

        Assert.assertSame( startState, transition.getStartingState() );
        Assert.assertSame( endState, transition.getEndingState() );
        Assert.assertEquals( transName, transition.getTransitionName() );

        LOG.debug( "Verified that the objects set at construction are correctly persisted into the object" );
    }

    /**
     * Tests the that the equals, hashcode and toString methods work
     * correctly
     */
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
        Assert.assertNotSame( trans1a, trans1b );
        Assert.assertNotSame( trans1a, trans2 );
        Assert.assertEquals( trans1a, trans1b );
        Assert.assertFalse( trans1a.equals( trans2 ) );

        // check hashcode
        Assert.assertEquals( trans1a.hashCode(), trans1b.hashCode() );
        Assert.assertFalse( trans1a.hashCode() == trans2.hashCode() );

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
        IState startState = EasyMock.createMock( IState.class );
        IState endState = EasyMock.createMock( IState.class );

        EasyMock.replay( startState );
        EasyMock.replay( endState );

        IStateTransition target = new StateTransitionImpl( "testTransition", startState, endState );
        LOG.debug( "Calling evaluate on the transition to endure that it throws an exception" );
        target.evaluateTransitionValidity( null );

        Assert.fail( "The test should have thrown an execption so this should not be seen" );

        EasyMock.verify( startState );
        EasyMock.verify( endState );
    }

    /**
     * Tests that when an event context with a matching transition
     * event is passed into the transition, it will state that the
     * transition is valid.
     * 
     * @throws StateMachineException
     *             this should not happen for this test
     */
    @Test
    public void testTranitionEventEvaluationForValidEvent() throws StateMachineException
    {
        IState startState = EasyMock.createMock( IState.class );
        IState endState = EasyMock.createMock( IState.class );
        IStateMachineContext context = EasyMock.createMock( IStateMachineContext.class );

        EasyMock.expect( context.getStateTransitionEvent() )
            .andReturn( new StateTransitionEventImpl( "testEvent" ) )
            .anyTimes();

        EasyMock.replay( startState );
        EasyMock.replay( endState );
        EasyMock.replay( context );

        StateTransitionImpl target = new StateTransitionImpl( "testTransition",
                                                              startState,
                                                              endState );
        IStateTransitionEvent evt = new StateTransitionEventImpl( "testEvent" );
        target.addTransitionEvent( evt );

        boolean result = target.evaluateTransitionValidity( context );
        LOG.debug( "For the valid event [" + evt + "] we are expecting true and we got [" + result
                   + "]" );
        Assert.assertTrue( result );

        EasyMock.verify( startState );
        EasyMock.verify( endState );
        EasyMock.verify( context );

    }

    /**
     * Tests that when a state transition is evaluated with no events
     * that it will evaluate to true.
     * 
     * @throws StateMachineException
     *             this should not happen for this test
     */
    @Test
    public void testTransitionEventEvaluationForNoEvents() throws StateMachineException
    {
        IState startState = EasyMock.createMock( IState.class );
        IState endState = EasyMock.createMock( IState.class );
        IStateMachineContext context = EasyMock.createMock( IStateMachineContext.class );

        IStateTransitionEvent evt = new StateTransitionEventImpl( "testEvent" );
        EasyMock.expect( context.getStateTransitionEvent() ).andReturn( evt ).anyTimes();

        EasyMock.replay( startState );
        EasyMock.replay( endState );
        EasyMock.replay( context );

        StateTransitionImpl target = new StateTransitionImpl( "testTransition",
                                                              startState,
                                                              endState );
        // no events set on the transition

        boolean result = target.evaluateTransitionValidity( context );
        LOG.debug( "For no set transition events we are expecting true and we got [" + result + "]" );
        Assert.assertTrue( result );

        EasyMock.verify( startState );
        EasyMock.verify( endState );
        EasyMock.verify( context );

    }

    /**
     * Tests that when a state transition has events that do not match
     * with the context event that it will return false
     * 
     * @throws StateMachineException
     *             this should not happen for this test
     */
    @Test
    public void testTransitionEventEvaluationForNoValidEvents() throws StateMachineException
    {
        IState startState = EasyMock.createMock( IState.class );
        IState endState = EasyMock.createMock( IState.class );
        IStateMachineContext context = EasyMock.createMock( IStateMachineContext.class );

        EasyMock.expect( context.getStateTransitionEvent() )
            .andReturn( new StateTransitionEventImpl( "notMatchingEvent" ) )
            .anyTimes();

        EasyMock.replay( startState );
        EasyMock.replay( endState );
        EasyMock.replay( context );

        StateTransitionImpl target = new StateTransitionImpl( "testTransition",
                                                              startState,
                                                              endState );
        IStateTransitionEvent evt = new StateTransitionEventImpl( "testEvent" );
        target.addTransitionEvent( evt );

        boolean result = target.evaluateTransitionValidity( context );
        LOG.debug( "For the invalid event [" + evt + "] we are expecting false and we got ["
                   + result + "]" );
        Assert.assertFalse( result );

        EasyMock.verify( startState );
        EasyMock.verify( endState );
        EasyMock.verify( context );

    }

    /**
     * Tests that if the event collection on the state transition is
     * null then we return true (similar to no events)
     * 
     * @throws StateMachineException
     *             this should not happen for this test
     */
    @Test
    public void testTransitionEventEvaluationForNullEvents() throws StateMachineException
    {

        IState startState = EasyMock.createMock( IState.class );
        IState endState = EasyMock.createMock( IState.class );
        IStateMachineContext context = EasyMock.createMock( IStateMachineContext.class );

        EasyMock.expect( context.getStateTransitionEvent() )
            .andReturn( new StateTransitionEventImpl( "notMatchingEvent" ) )
            .anyTimes();

        EasyMock.replay( startState );
        EasyMock.replay( endState );
        EasyMock.replay( context );

        StateTransitionImpl target = new StateTransitionImpl( "testTransition",
                                                              startState,
                                                              endState );
        target.setTransitionEvents( null );

        boolean result = target.evaluateTransitionValidity( context );
        LOG.debug( "For transition events we are expecting true and we got [" + result + "]" );
        Assert.assertTrue( result );

        EasyMock.verify( startState );
        EasyMock.verify( endState );
        EasyMock.verify( context );
    }

    /**
     * Tests that when we evaluate a state transition with a valid
     * guard that it evaluates to true.
     * 
     * @throws StateMachineException
     */
    @Test
    public void testTranitionEventEvaluationForValidGuard() throws StateMachineException
    {
        // set up mocks
        IState startState = EasyMock.createMock( IState.class );
        IState endState = EasyMock.createMock( IState.class );
        IStateMachineContext context = EasyMock.createMock( IStateMachineContext.class );

        // replay mocks
        EasyMock.replay( startState );
        EasyMock.replay( endState );
        EasyMock.replay( context );

        // test exec
        StateTransitionImpl target = new StateTransitionImpl( "testTransition",
                                                              startState,
                                                              endState );
        target.addTransitionGuard( new TrueGuardStub() );

        boolean result = target.evaluateTransitionValidity( context );
        LOG.debug( "For transition guards we are expecting true and we got [" + result + "]" );
        Assert.assertTrue( result );

        // verify mocks
        EasyMock.verify( startState );
        EasyMock.verify( endState );
        EasyMock.verify( context );
    }

    /**
     * Tests that when we evaluate a state transition with one valid
     * guard and one invalid guard that it evaluates false.
     * 
     * @throws StateMachineException
     */
    @Test
    public void testTranitionEventEvaluationForValidGuardAndInvalidGuard()
                    throws StateMachineException
    {
        // set up mocks
        IState startState = EasyMock.createMock( IState.class );
        IState endState = EasyMock.createMock( IState.class );
        IStateMachineContext context = EasyMock.createMock( IStateMachineContext.class );

        // replay mocks
        EasyMock.replay( startState );
        EasyMock.replay( endState );
        EasyMock.replay( context );

        // test exec
        StateTransitionImpl target = new StateTransitionImpl( "testTransition",
                                                              startState,
                                                              endState );
        target.addTransitionGuard( new FalseGuardStub() );
        target.addTransitionGuard( new TrueGuardStub() );

        boolean result = target.evaluateTransitionValidity( context );
        LOG.debug( "For transition guards we are expecting false and we got [" + result + "]" );
        Assert.assertFalse( result );

        // verify mocks
        EasyMock.verify( startState );
        EasyMock.verify( endState );
        EasyMock.verify( context );

    }

    /**
     * Tests that if we evaluiate a state transition with no guards
     * that it evaluates to true
     * 
     * @throws StateMachineException
     */
    @Test
    public void testTransitionEventEvaluationForNoGuards() throws StateMachineException
    {
        // set up mocks
        IState startState = EasyMock.createMock( IState.class );
        IState endState = EasyMock.createMock( IState.class );
        IStateMachineContext context = EasyMock.createMock( IStateMachineContext.class );

        // replay mocks
        EasyMock.replay( startState );
        EasyMock.replay( endState );
        EasyMock.replay( context );

        // test exec
        StateTransitionImpl target = new StateTransitionImpl( "testTransition",
                                                              startState,
                                                              endState );

        boolean result = target.evaluateTransitionValidity( context );
        LOG.debug( "For no transition guards we are expecting true and we got [" + result + "]" );
        Assert.assertTrue( result );

        // verify mocks
        EasyMock.verify( startState );
        EasyMock.verify( endState );
        EasyMock.verify( context );

    }

    /**
     * Tests that when we evaluate a state transition with only
     * invalid guards that it evaluates to false
     * 
     * @throws StateMachineException
     */
    @Test
    public void testTransitionEventEvaluationForNoValidGuards() throws StateMachineException
    {
        // set up mocks
        IState startState = EasyMock.createMock( IState.class );
        IState endState = EasyMock.createMock( IState.class );
        IStateMachineContext context = EasyMock.createMock( IStateMachineContext.class );

        // replay mocks
        EasyMock.replay( startState );
        EasyMock.replay( endState );
        EasyMock.replay( context );

        // test exec
        StateTransitionImpl target = new StateTransitionImpl( "testTransition",
                                                              startState,
                                                              endState );
        target.addTransitionGuard( new FalseGuardStub() );

        boolean result = target.evaluateTransitionValidity( context );
        LOG.debug( "For no valid transition guards we are expecting false and we got [" + result
                   + "]" );
        Assert.assertFalse( result );

        // verify mocks
        EasyMock.verify( startState );
        EasyMock.verify( endState );
        EasyMock.verify( context );

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
        // set up mocks
        IState startState = EasyMock.createMock( IState.class );
        IState endState = EasyMock.createMock( IState.class );
        IStateMachineContext context = EasyMock.createMock( IStateMachineContext.class );

        // replay mocks
        EasyMock.replay( startState );
        EasyMock.replay( endState );
        EasyMock.replay( context );

        // test exec
        StateTransitionImpl target = new StateTransitionImpl( "testTransition",
                                                              startState,
                                                              endState );
        target.setTransitionGuards( null );

        boolean result = target.evaluateTransitionValidity( context );
        LOG.debug( "For null transition guards we are expecting true and we got [" + result + "]" );
        Assert.assertTrue( result );

        // verify mocks
        EasyMock.verify( startState );
        EasyMock.verify( endState );
        EasyMock.verify( context );

    }

}
