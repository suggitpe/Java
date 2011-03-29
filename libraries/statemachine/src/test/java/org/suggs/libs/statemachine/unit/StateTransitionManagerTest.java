/*
 * TransitionManagerTests.java created on 24 Aug 2009 07:11:03 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine.unit;

import org.suggs.libs.statemachine.State;
import org.suggs.libs.statemachine.StateTransition;
import org.suggs.libs.statemachine.impl.StateTransitionManager;

import java.util.Collection;

import org.junit.AfterClass;
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
 * Test suite for the transition manager.
 *
 * @author suggitpe
 * @version 1.0 24 Aug 2009
 */
public class StateTransitionManagerTest {

    private static final Logger LOG = LoggerFactory.getLogger( StateTransitionManagerTest.class );

    private State mockStateOne;
    private State mockStateTwo;
    private StateTransition mockTransitionOne;
    private StateTransition mockTransitionTwo;
    private StateTransition mockTransitionThree;
    private StateTransition mockTransitionFour;

    /** */
    @BeforeClass
    public static void doBeforeClass() {
        LOG.debug( "=================== Start: " + StateTransitionManagerTest.class.getSimpleName() );
    }

    @AfterClass
    public static void doAfterClass() {
        LOG.debug( "=================== End: " + StateTransitionManagerTest.class.getSimpleName() );
    }

    /** */
    @Before
    public void doBefore() {
        LOG.debug( "------------------- " );
        LOG.debug( "Clearing all transitions from manager" );
        StateTransitionManager.instance().clearTransitionsFromTransitionManager();
        mockStateOne = mock( State.class );
        mockStateTwo = mock( State.class );
        mockTransitionOne = mock( StateTransition.class );
        mockTransitionTwo = mock( StateTransition.class );
        mockTransitionThree = mock( StateTransition.class );
        mockTransitionFour = mock( StateTransition.class );
    }

    /**
     * Verifies that the returns from two singleton requests are the same.
     */
    @Test
    public void checkStateTransitionManagerReturnsSameObjectFromSingleton() {
        LOG.debug( "Testing that the StateTransitionManager Singleton functions correctly" );
        StateTransitionManager transMgr1 = StateTransitionManager.instance();
        StateTransitionManager transMgr2 = StateTransitionManager.instance();

        LOG.debug( "Transition Manager 1: " + transMgr1 );
        LOG.debug( "Transition Manager 2: " + transMgr2 );

        assertThat( transMgr1, equalTo( transMgr2 ) );
    }

    /**
     * Tests that we can add a transition to a the transition manager and then retrieve the same transition through a
     * separate call to the transition manager.
     */
    @SuppressWarnings("boxing")
    @Test
    public void addAndRetrieveSameTransitionForState() {
        when( mockStateOne.getStateName() ).thenReturn( "testState" );

        when( mockTransitionOne.getStartingState() ).thenReturn( mockStateOne );
        when( mockTransitionOne.getTransitionName() ).thenReturn( "testTransition" );

        Collection<StateTransition> collection = StateTransitionManager.instance()
                .getListOfTransitionsForState( mockStateOne );
        assertThat( 0, equalTo( collection.size() ) );
        LOG.debug( "Retrieved [" + collection.size() + "] transitions for mocked state transition" );

        LOG.debug( "Loading Mock transition" );
        StateTransitionManager.instance().addTransitionToManager( mockTransitionOne );

        collection = StateTransitionManager.instance().getListOfTransitionsForState( mockStateOne );
        assertThat( 1, equalTo( collection.size() ) );
        LOG.debug( "Retrieved [" + collection.size() + "] transitions for mocked state transition" );
        LOG.debug( StateTransitionManager.instance().toString() );

        assertThat( mockTransitionOne, equalTo( collection.iterator().next() ) );
    }

    /**
     * Tests that if no transitions are loaded, no state transitions are returned.
     */
    @SuppressWarnings("boxing")
    @Test
    public void retrieveNoTransition() {
        when( mockStateOne.getStateName() ).thenReturn( "testState" );

        LOG.debug( "Calling StateTransitionManager with state=[" + mockStateOne.getStateName()
                + "] argument so expecting no transitions to be returned" );
        Collection<StateTransition> collection = StateTransitionManager.instance()
                .getListOfTransitionsForState( mockStateOne );

        assertThat( 0, equalTo( collection.size() ) );
        LOG.debug( StateTransitionManager.instance().toString() );
    }

    /**
     * Tests that when we try and retrieve a transition for a null state, we get a decent exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void retrieveTransitionForNullState() {
        LOG.debug( "Calling StateTransitionManager with null argument so expecting exception" );
        StateTransitionManager.instance().getListOfTransitionsForState( null );
    }

    /**
     * Tests that when two transitions with the same name are loaded for the same state that an exception is thrown.
     */
    @Test(expected = IllegalStateException.class)
    public void exceptionThrownWhenTwoTransitionsForSameStateLoaded() {
        when( mockTransitionOne.getStartingState() ).thenReturn( mockStateOne );
        when( mockStateOne.getStateName() ).thenReturn( "testState" );
        when( mockTransitionOne.getTransitionName() ).thenReturn( "testTransition" );
        when( mockTransitionOne.getStartingState() ).thenReturn( mockStateOne );

        when( mockTransitionTwo.getTransitionName() ).thenReturn( "testTransition" );
        when( mockTransitionTwo.getStartingState() ).thenReturn( mockStateOne );

        LOG.debug( "Loading Mock transitions" );
        StateTransitionManager.instance().addTransitionToManager( mockTransitionOne );
        StateTransitionManager.instance().addTransitionToManager( mockTransitionTwo );
    }

    /**
     * Tests that if we try and add a null transition into the manaer we get some level of hissy fit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void exceptionThrownWhenNullTransitionAddedToManager() {
        LOG.debug( "Loading NULL transitions" );
        StateTransitionManager.instance().addTransitionToManager( null );
    }

    /**
     * Tests that if we try and add a null transition into the manaer we get some level of hissy fit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void exceptionThrownWhenNullTransitionsSetOnManager() {
        LOG.debug( "Loading NULL transitions" );
        StateTransitionManager.instance().setTransitions( null );
    }

    /**
     * Tests that for a number of state transitions loaded for a given state that it will return multiple state
     * transitions associated with that state.
     */
    @SuppressWarnings("boxing")
    @Test
    public void addAndRetrieveMultipleTransitionForState() {
        when( mockTransitionOne.getStartingState() ).thenReturn( mockStateOne );
        when( mockStateOne.getStateName() ).thenReturn( "testState1" );
        when( mockTransitionOne.getTransitionName() ).thenReturn( "testTransition1" );

        when( mockTransitionTwo.getStartingState() ).thenReturn( mockStateOne );
        when( mockTransitionTwo.getTransitionName() ).thenReturn( "testTransition2" );

        when( mockTransitionThree.getStartingState() ).thenReturn( mockStateOne );
        when( mockTransitionThree.getTransitionName() ).thenReturn( "testTransition3" );

        when( mockTransitionFour.getStartingState() ).thenReturn( mockStateTwo );
        when( mockStateTwo.getStateName() ).thenReturn( "testState2" );
        when( mockTransitionFour.getTransitionName() ).thenReturn( "testTransition4" );

        LOG.debug( "Loading Mock transitions" );
        StateTransitionManager.instance().addTransitionToManager( mockTransitionOne );
        StateTransitionManager.instance().addTransitionToManager( mockTransitionTwo );
        StateTransitionManager.instance().addTransitionToManager( mockTransitionThree );
        StateTransitionManager.instance().addTransitionToManager( mockTransitionFour );
        LOG.debug( StateTransitionManager.instance().toString() );

        Collection<StateTransition> collection = StateTransitionManager.instance()
                .getListOfTransitionsForState( mockStateOne );
        assertThat( collection.size(), equalTo(3) );
        LOG.debug( "Retrieved [" + collection.size() + "] transitions for mocked state 1" );

        collection = StateTransitionManager.instance().getListOfTransitionsForState( mockStateTwo );
        assertThat( collection.size(), equalTo(1) );
        LOG.debug( "Retrieved [" + collection.size() + "] transitions for mocked state 2" );

        collection = StateTransitionManager.instance().getAllTransitions();
        assertThat( 4, equalTo( collection.size() ) );
        LOG.debug( "Retrieved [" + collection.size() + "] transitions for all states" );
    }

}
