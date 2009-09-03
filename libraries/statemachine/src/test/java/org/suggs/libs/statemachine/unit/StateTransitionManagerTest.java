/*
 * TransitionManagerTests.java created on 24 Aug 2009 07:11:03 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine.unit;

import org.suggs.libs.statemachine.IState;
import org.suggs.libs.statemachine.IStateTransition;
import org.suggs.libs.statemachine.impl.StateTransitionManager;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test suite for the transition manager.
 * 
 * @author suggitpe
 * @version 1.0 24 Aug 2009
 */
public class StateTransitionManagerTest
{

    private static final Log LOG = LogFactory.getLog( StateTransitionManagerTest.class );

    @BeforeClass
    public static void doBeforeClass()
    {
        LOG.debug( "===================" + StateTransitionManagerTest.class.getSimpleName() );
    }

    @Before
    public void doBefore()
    {
        LOG.debug( "------------------- " );
        LOG.debug( "Clearing all transitions from manager" );
        StateTransitionManager.instance().clearTransitionsFromTransitionManager();
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

        Assert.assertSame( transMgr1, transMgr2 );
    }

    /**
     * Tests that we can add a transition to a the transition manager
     * and then retrieve the same transition through a separate call
     * to the transition manager.
     */
    @Test
    public void testAddAndRetrieveSameTransitionForState()
    {
        // prep mock
        IState stateMock = EasyMock.createMock( IState.class );
        IStateTransition transMock = EasyMock.createMock( IStateTransition.class );

        EasyMock.expect( stateMock.getStateName() ).andReturn( "testState" ).anyTimes();

        EasyMock.expect( transMock.getStartingState() ).andReturn( stateMock ).anyTimes();
        EasyMock.expect( transMock.getTransitionName() ).andReturn( "testTransition" ).anyTimes();

        // load mock
        EasyMock.replay( transMock );
        EasyMock.replay( stateMock );

        // test exec
        List<IStateTransition> list = StateTransitionManager.instance()
            .getListOfTransitionsForState( stateMock );
        Assert.assertEquals( 0, list.size() );
        LOG.debug( "Retrieved [" + list.size() + "] transitions for mocked state transition" );
        LOG.debug( StateTransitionManager.instance() );

        LOG.debug( "Loading Mock transition" );
        StateTransitionManager.instance().addTransitionToManager( transMock );

        list = StateTransitionManager.instance().getListOfTransitionsForState( stateMock );
        Assert.assertEquals( 1, list.size() );
        LOG.debug( "Retrieved [" + list.size() + "] transitions for mocked state transition" );
        LOG.debug( StateTransitionManager.instance() );

        Assert.assertEquals( transMock, list.get( 0 ) );

        // verify mock
        EasyMock.verify( transMock );
        EasyMock.verify( stateMock );
    }

    /**
     * Tests that if no transitions are loaded, no state transitions
     * are returned.
     */
    @Test
    public void testRetrieveNoTransition()
    {
        // prep mock
        IState stateMock = EasyMock.createMock( IState.class );
        EasyMock.expect( stateMock.getStateName() ).andReturn( "testState" ).anyTimes();

        // load mock
        EasyMock.replay( stateMock );

        // test exec
        LOG.debug( "Calling StateTransitionManager with state=[" + stateMock.getStateName()
                   + "] argument so expecting no transitions to be returned" );
        List<IStateTransition> list = StateTransitionManager.instance()
            .getListOfTransitionsForState( stateMock );
        Assert.assertEquals( 0, list.size() );
        LOG.debug( StateTransitionManager.instance() );

        EasyMock.verify( stateMock );
    }

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
        // prep mock
        IState stateMock = EasyMock.createMock( IState.class );
        IStateTransition transMock1 = EasyMock.createMock( IStateTransition.class );
        IStateTransition transMock2 = EasyMock.createMock( IStateTransition.class );

        EasyMock.expect( transMock1.getStartingState() ).andReturn( stateMock ).anyTimes();
        EasyMock.expect( stateMock.getStateName() ).andReturn( "testState" ).anyTimes();
        EasyMock.expect( transMock1.getTransitionName() ).andReturn( "testTransition" ).anyTimes();
        EasyMock.expect( transMock1.getStartingState() ).andReturn( stateMock ).anyTimes();

        EasyMock.expect( transMock2.getStartingState() ).andReturn( stateMock ).anyTimes();
        EasyMock.expect( transMock2.getStartingState() ).andReturn( stateMock ).anyTimes();

        // load mock
        EasyMock.replay( stateMock );
        EasyMock.replay( transMock1 );
        EasyMock.replay( transMock2 );

        // test exec
        LOG.debug( "Loading Mock transitions" );
        StateTransitionManager.instance().addTransitionToManager( transMock1 );
        StateTransitionManager.instance().addTransitionToManager( transMock2 );

        // verify mock
        EasyMock.verify( stateMock );
        EasyMock.verify( transMock1 );
        EasyMock.verify( transMock2 );
    }

    /**
     * Tests that for a number of state transitions loaded for a given
     * state that it will return multiple state transitions associated
     * with that state.
     */
    @Test
    public void testAddAndRetrieveMultipleTransitionForState()
    {
        // prep mock
        IState stateMock1 = EasyMock.createMock( IState.class );
        IState stateMock2 = EasyMock.createMock( IState.class );
        IStateTransition transMock1 = EasyMock.createMock( IStateTransition.class );
        IStateTransition transMock2 = EasyMock.createMock( IStateTransition.class );
        IStateTransition transMock3 = EasyMock.createMock( IStateTransition.class );
        IStateTransition transMock4 = EasyMock.createMock( IStateTransition.class );

        EasyMock.expect( transMock1.getStartingState() ).andReturn( stateMock1 ).anyTimes();
        EasyMock.expect( stateMock1.getStateName() ).andReturn( "testState1" ).anyTimes();
        EasyMock.expect( transMock1.getTransitionName() ).andReturn( "testTransition1" ).anyTimes();

        EasyMock.expect( transMock2.getStartingState() ).andReturn( stateMock1 ).anyTimes();
        EasyMock.expect( transMock2.getTransitionName() ).andReturn( "testTransition2" ).anyTimes();

        EasyMock.expect( transMock3.getStartingState() ).andReturn( stateMock1 ).anyTimes();
        EasyMock.expect( transMock3.getTransitionName() ).andReturn( "testTransition3" ).anyTimes();

        EasyMock.expect( transMock4.getStartingState() ).andReturn( stateMock2 ).anyTimes();
        EasyMock.expect( stateMock2.getStateName() ).andReturn( "testState2" ).anyTimes();
        EasyMock.expect( transMock4.getTransitionName() ).andReturn( "testTransition4" ).anyTimes();

        // load mock
        EasyMock.replay( transMock1 );
        EasyMock.replay( transMock2 );
        EasyMock.replay( transMock3 );
        EasyMock.replay( transMock4 );
        EasyMock.replay( stateMock1 );
        EasyMock.replay( stateMock2 );

        // test exec
        LOG.debug( "Loading Mock transitions" );
        StateTransitionManager.instance().addTransitionToManager( transMock1 );
        StateTransitionManager.instance().addTransitionToManager( transMock2 );
        StateTransitionManager.instance().addTransitionToManager( transMock3 );
        StateTransitionManager.instance().addTransitionToManager( transMock4 );
        LOG.debug( StateTransitionManager.instance() );

        List<IStateTransition> list = StateTransitionManager.instance()
            .getListOfTransitionsForState( stateMock1 );
        Assert.assertEquals( 3, list.size() );
        LOG.debug( "Retrieved [" + list.size() + "] transitions for mocked state 1" );

        list = StateTransitionManager.instance().getListOfTransitionsForState( stateMock2 );
        Assert.assertEquals( 1, list.size() );
        LOG.debug( "Retrieved [" + list.size() + "] transitions for mocked state 2" );

        list = StateTransitionManager.instance().getAllTransitions();
        Assert.assertEquals( 4, list.size() );
        LOG.debug( "Retrieved [" + list.size() + "] transitions for all states" );

        // verify mock
        EasyMock.verify( transMock1 );
        EasyMock.verify( transMock2 );
        EasyMock.verify( transMock3 );
        EasyMock.verify( transMock4 );
        EasyMock.verify( stateMock1 );
        EasyMock.verify( stateMock2 );
    }

}
