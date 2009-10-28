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

    private IMocksControl mCtrl_;

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
        mCtrl_ = createControl();
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
    public void testAddAndRetrieveSameTransitionForState()
    {
        IState stateMock = mCtrl_.createMock( IState.class );
        IStateTransition transMock = mCtrl_.createMock( IStateTransition.class );

        expect( stateMock.getStateName() ).andReturn( "testState" ).anyTimes();

        expect( transMock.getStartingState() ).andReturn( stateMock ).anyTimes();
        expect( transMock.getTransitionName() ).andReturn( "testTransition" ).anyTimes();

        mCtrl_.replay();

        Collection<IStateTransition> collection = StateTransitionManager.instance()
            .getListOfTransitionsForState( stateMock );
        assertThat( 0, equalTo( collection.size() ) );
        LOG.debug( "Retrieved [" + collection.size() + "] transitions for mocked state transition" );

        LOG.debug( "Loading Mock transition" );
        StateTransitionManager.instance().addTransitionToManager( transMock );

        collection = StateTransitionManager.instance().getListOfTransitionsForState( stateMock );
        assertThat( 1, equalTo( collection.size() ) );
        LOG.debug( "Retrieved [" + collection.size() + "] transitions for mocked state transition" );
        LOG.debug( StateTransitionManager.instance() );

        assertThat( transMock, equalTo( collection.iterator().next() ) );

        mCtrl_.verify();
    }

    /**
     * Tests that if no transitions are loaded, no state transitions
     * are returned.
     */
    @SuppressWarnings("boxing")
    @Test
    public void testRetrieveNoTransition()
    {
        IState stateMock = mCtrl_.createMock( IState.class );
        expect( stateMock.getStateName() ).andReturn( "testState" ).anyTimes();

        mCtrl_.replay();

        LOG.debug( "Calling StateTransitionManager with state=[" + stateMock.getStateName()
                   + "] argument so expecting no transitions to be returned" );
        Collection<IStateTransition> collection = StateTransitionManager.instance()
            .getListOfTransitionsForState( stateMock );

        assertThat( 0, equalTo( collection.size() ) );
        LOG.debug( StateTransitionManager.instance() );

        mCtrl_.verify();
    }

    /**
     * Tests that when we try and retrieve a transition for a null
     * state, we get a decent exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRetrieveTransitionForNullState()
    {
        LOG.debug( "Calling StateTransitionManager with null argument so expecting exception" );
        StateTransitionManager.instance().getListOfTransitionsForState( null );
    }

    /**
     * Tests that when two transitions with the same name are loaded
     * for the same state that an exception is thrown.
     */
    @Test(expected = IllegalStateException.class)
    public void testExceptionThrownWhenTwoTransitionsForSameStateLoaded()
    {
        IState stateMock = mCtrl_.createMock( IState.class );
        IStateTransition transMock1 = mCtrl_.createMock( IStateTransition.class );
        IStateTransition transMock2 = mCtrl_.createMock( IStateTransition.class );

        expect( transMock1.getStartingState() ).andReturn( stateMock ).anyTimes();
        expect( stateMock.getStateName() ).andReturn( "testState" ).anyTimes();
        expect( transMock1.getTransitionName() ).andReturn( "testTransition" ).anyTimes();
        expect( transMock1.getStartingState() ).andReturn( stateMock ).anyTimes();

        expect( transMock2.getStartingState() ).andReturn( stateMock ).anyTimes();
        expect( transMock2.getStartingState() ).andReturn( stateMock ).anyTimes();

        mCtrl_.replay();

        LOG.debug( "Loading Mock transitions" );
        StateTransitionManager.instance().addTransitionToManager( transMock1 );
        StateTransitionManager.instance().addTransitionToManager( transMock2 );

        mCtrl_.verify();
    }

    /**
     * Tests that for a number of state transitions loaded for a given
     * state that it will return multiple state transitions associated
     * with that state.
     */
    @SuppressWarnings("boxing")
    @Test
    public void testAddAndRetrieveMultipleTransitionForState()
    {
        // prep mock
        IState stateMock1 = mCtrl_.createMock( IState.class );
        IState stateMock2 = mCtrl_.createMock( IState.class );
        IStateTransition transMock1 = mCtrl_.createMock( IStateTransition.class );
        IStateTransition transMock2 = mCtrl_.createMock( IStateTransition.class );
        IStateTransition transMock3 = mCtrl_.createMock( IStateTransition.class );
        IStateTransition transMock4 = mCtrl_.createMock( IStateTransition.class );

        expect( transMock1.getStartingState() ).andReturn( stateMock1 ).anyTimes();
        expect( stateMock1.getStateName() ).andReturn( "testState1" ).anyTimes();
        expect( transMock1.getTransitionName() ).andReturn( "testTransition1" ).anyTimes();

        expect( transMock2.getStartingState() ).andReturn( stateMock1 ).anyTimes();
        expect( transMock2.getTransitionName() ).andReturn( "testTransition2" ).anyTimes();

        expect( transMock3.getStartingState() ).andReturn( stateMock1 ).anyTimes();
        expect( transMock3.getTransitionName() ).andReturn( "testTransition3" ).anyTimes();

        expect( transMock4.getStartingState() ).andReturn( stateMock2 ).anyTimes();
        expect( stateMock2.getStateName() ).andReturn( "testState2" ).anyTimes();
        expect( transMock4.getTransitionName() ).andReturn( "testTransition4" ).anyTimes();

        mCtrl_.replay();

        LOG.debug( "Loading Mock transitions" );
        StateTransitionManager.instance().addTransitionToManager( transMock1 );
        StateTransitionManager.instance().addTransitionToManager( transMock2 );
        StateTransitionManager.instance().addTransitionToManager( transMock3 );
        StateTransitionManager.instance().addTransitionToManager( transMock4 );
        LOG.debug( StateTransitionManager.instance() );

        Collection<IStateTransition> collection = StateTransitionManager.instance()
            .getListOfTransitionsForState( stateMock1 );
        Assert.assertEquals( 3, collection.size() );
        LOG.debug( "Retrieved [" + collection.size() + "] transitions for mocked state 1" );

        collection = StateTransitionManager.instance().getListOfTransitionsForState( stateMock2 );
        Assert.assertEquals( 1, collection.size() );
        LOG.debug( "Retrieved [" + collection.size() + "] transitions for mocked state 2" );

        collection = StateTransitionManager.instance().getAllTransitions();
        assertThat( 4, equalTo( collection.size() ) );
        Assert.assertEquals( 4, collection.size() );
        LOG.debug( "Retrieved [" + collection.size() + "] transitions for all states" );

        mCtrl_.verify();
    }

}
