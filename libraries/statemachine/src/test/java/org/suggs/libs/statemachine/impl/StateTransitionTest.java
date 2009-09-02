/*
 * StateTransitionTest.java created on 1 Sep 2009 07:20:51 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine.impl;

import org.suggs.libs.statemachine.IState;
import org.suggs.libs.statemachine.IStateTransition;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    @Test
    public void testTransitionEvaluationForNullContext()
    {
    }

    @Test
    public void testTranitionEventEvaluationForValidEvent()
    {
    }

    @Test
    public void testTransitionEventEvaluationForNoEvents()
    {
    }

    @Test
    void testTransitionEventEvaluationForNoValidEvents()
    {
    }

    @Test
    public void testTransitionEventEvaluationForNullEvents()
    {
    }

}
