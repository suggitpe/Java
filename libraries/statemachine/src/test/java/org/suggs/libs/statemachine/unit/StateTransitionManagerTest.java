/*
 * TransitionManagerTests.java created on 24 Aug 2009 07:11:03 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine.unit;

import org.suggs.libs.statemachine.IState;
import org.suggs.libs.statemachine.IStateTransition;
import org.suggs.libs.statemachine.impl.StateTransitionManager;

import java.util.Collection;

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
import static org.junit.Assert.assertThat;

/**
 * Test suite for the transition manager.
 * 
 * @author suggitpe
 * @version 1.0 24 Aug 2009
 */
public class StateTransitionManagerTest
{

    private static final Log LOG = LogFactory.getLog( StateTransitionManagerTest.class );

    private IMocksControl ctrl;
    private IState mockStateOne;
    private IState mockStateTwo;
    private IStateTransition mockTransitionOne;
    private IStateTransition mockTransitionTwo;
    private IStateTransition mockTransitionThree;
    private IStateTransition mockTransitionFour;

    /** */
    @BeforeClass
    public static void doBeforeClass()
    {
        LOG.debug( "===================" + StateTransitionManagerTest.class.getSimpleName() );
    }

    /** */
    @Before
    public void doBefore()
    {
        LOG.debug( "------------------- " );
        LOG.debug( "Clearing all transitions from manager" );
        StateTransitionManager.instance().clearTransitionsFromTransitionManager();
        ctrl = createControl();
        mockStateOne = ctrl.createMock( IState.class );
        mockStateTwo = ctrl.createMock( IState.class );
        mockTransitionOne = ctrl.createMock( IStateTransition.class );
        mockTransitionTwo = ctrl.createMock( IStateTransition.class );
        mockTransitionThree = ctrl.createMock( IStateTransition.class );
        mockTransitionFour = ctrl.createMock( IStateTransition.class );
    }

    /**
     * Verifies that the returns from two singleton requests are the
     * same.
     */
    @Test
    public void checkStateTransitionManagerReturnsSameObjectFromSingleton()
    {
        LOG.debug( "Testing that the StateTransitionManager Singleton functions correctly" );
        StateTransitionManager transMgr1 = StateTransitionManager.instance();
        StateTransitionManager transMgr2 = StateTransitionManager.instance();

        LOG.debug( "Transition Manager 1: " + transMgr1 );
        LOG.debug( "Transition Manager 2: " + transMgr2 );

        assertThat( transMgr1, equalTo( transMgr2 ) );
    }

    /**
     * Tests that we can add a transition to a the transition manager
     * and then retrieve the same transition through a separate call
     * to the transition manager.
     */
    @SuppressWarnings("boxing")
    @Test
    public void addAndRetrieveSameTransitionForState()
    {
        expect( mockStateOne.getStateName() ).andReturn( "testState" ).anyTimes();

        expect( mockTransitionOne.getStartingState() ).andReturn( mockStateOne ).anyTimes();
        expect( mockTransitionOne.getTransitionName() ).andReturn( "testTransition" ).anyTimes();

        ctrl.replay();

        Collection<IStateTransition> collection = StateTransitionManager.instance()
            .getListOfTransitionsForState( mockStateOne );
        assertThat( 0, equalTo( collection.size() ) );
        LOG.debug( "Retrieved [" + collection.size() + "] transitions for mocked state transition" );

        LOG.debug( "Loading Mock transition" );
        StateTransitionManager.instance().addTransitionToManager( mockTransitionOne );

        collection = StateTransitionManager.instance().getListOfTransitionsForState( mockStateOne );
        assertThat( 1, equalTo( collection.size() ) );
        LOG.debug( "Retrieved [" + collection.size() + "] transitions for mocked state transition" );
        LOG.debug( StateTransitionManager.instance() );

        assertThat( mockTransitionOne, equalTo( collection.iterator().next() ) );

        ctrl.verify();
    }

    /**
     * Tests that if no transitions are loaded, no state transitions
     * are returned.
     */
    @SuppressWarnings("boxing")
    @Test
    public void retrieveNoTransition()
    {
        expect( mockStateOne.getStateName() ).andReturn( "testState" ).anyTimes();

        ctrl.replay();

        LOG.debug( "Calling StateTransitionManager with state=[" + mockStateOne.getStateName()
                   + "] argument so expecting no transitions to be returned" );
        Collection<IStateTransition> collection = StateTransitionManager.instance()
            .getListOfTransitionsForState( mockStateOne );

        assertThat( 0, equalTo( collection.size() ) );
        LOG.debug( StateTransitionManager.instance() );

        ctrl.verify();
    }

    /**
     * Tests that when we try and retrieve a transition for a null
     * state, we get a decent exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void retrieveTransitionForNullState()
    {
        LOG.debug( "Calling StateTransitionManager with null argument so expecting exception" );
        StateTransitionManager.instance().getListOfTransitionsForState( null );
    }

    /**
     * Tests that when two transitions with the same name are loaded
     * for the same state that an exception is thrown.
     */
    @Test(expected = IllegalStateException.class)
    public void exceptionThrownWhenTwoTransitionsForSameStateLoaded()
    {
        expect( mockTransitionOne.getStartingState() ).andReturn( mockStateOne ).anyTimes();
        expect( mockStateOne.getStateName() ).andReturn( "testState" ).anyTimes();
        expect( mockTransitionOne.getTransitionName() ).andReturn( "testTransition" ).anyTimes();
        expect( mockTransitionOne.getStartingState() ).andReturn( mockStateOne ).anyTimes();

        expect( mockTransitionTwo.getStartingState() ).andReturn( mockStateOne ).anyTimes();
        expect( mockTransitionTwo.getStartingState() ).andReturn( mockStateOne ).anyTimes();

        ctrl.replay();

        LOG.debug( "Loading Mock transitions" );
        StateTransitionManager.instance().addTransitionToManager( mockTransitionOne );
        StateTransitionManager.instance().addTransitionToManager( mockTransitionTwo );

        ctrl.verify();
    }

    /**
     * Tests that if we try and add a null transition into the manaer
     * we get some level of hissy fit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void exceptionThrownWhenNullTransitionAddedToManager()
    {
        LOG.debug( "Loading NULL transitions" );
        StateTransitionManager.instance().addTransitionToManager( null );
    }

    /**
     * Tests that if we try and add a null transition into the manaer
     * we get some level of hissy fit.
     */
    @Test(expected = IllegalArgumentException.class)
    public void exceptionThrownWhenNullTransitionsSetOnManager()
    {
        LOG.debug( "Loading NULL transitions" );
        StateTransitionManager.instance().setTransitions( null );
    }

    /**
     * Tests that for a number of state transitions loaded for a given
     * state that it will return multiple state transitions associated
     * with that state.
     */
    @SuppressWarnings("boxing")
    @Test
    public void addAndRetrieveMultipleTransitionForState()
    {
        expect( mockTransitionOne.getStartingState() ).andReturn( mockStateOne ).anyTimes();
        expect( mockStateOne.getStateName() ).andReturn( "testState1" ).anyTimes();
        expect( mockTransitionOne.getTransitionName() ).andReturn( "testTransition1" ).anyTimes();

        expect( mockTransitionTwo.getStartingState() ).andReturn( mockStateOne ).anyTimes();
        expect( mockTransitionTwo.getTransitionName() ).andReturn( "testTransition2" ).anyTimes();

        expect( mockTransitionThree.getStartingState() ).andReturn( mockStateOne ).anyTimes();
        expect( mockTransitionThree.getTransitionName() ).andReturn( "testTransition3" ).anyTimes();

        expect( mockTransitionFour.getStartingState() ).andReturn( mockStateTwo ).anyTimes();
        expect( mockStateTwo.getStateName() ).andReturn( "testState2" ).anyTimes();
        expect( mockTransitionFour.getTransitionName() ).andReturn( "testTransition4" ).anyTimes();

        ctrl.replay();

        LOG.debug( "Loading Mock transitions" );
        StateTransitionManager.instance().addTransitionToManager( mockTransitionOne );
        StateTransitionManager.instance().addTransitionToManager( mockTransitionTwo );
        StateTransitionManager.instance().addTransitionToManager( mockTransitionThree );
        StateTransitionManager.instance().addTransitionToManager( mockTransitionFour );
        LOG.debug( StateTransitionManager.instance() );

        Collection<IStateTransition> collection = StateTransitionManager.instance()
            .getListOfTransitionsForState( mockStateOne );
        Assert.assertEquals( 3, collection.size() );
        LOG.debug( "Retrieved [" + collection.size() + "] transitions for mocked state 1" );

        collection = StateTransitionManager.instance().getListOfTransitionsForState( mockStateTwo );
        Assert.assertEquals( 1, collection.size() );
        LOG.debug( "Retrieved [" + collection.size() + "] transitions for mocked state 2" );

        collection = StateTransitionManager.instance().getAllTransitions();
        assertThat( 4, equalTo( collection.size() ) );
        Assert.assertEquals( 4, collection.size() );
        LOG.debug( "Retrieved [" + collection.size() + "] transitions for all states" );

        ctrl.verify();
    }

}
