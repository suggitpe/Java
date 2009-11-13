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

    private IMocksControl ctrl_;
    private IState mockStateOne_;
    private IState mockStateTwo_;
    private IStateTransition mockTransitionOne_;
    private IStateTransition mockTransitionTwo_;
    private IStateTransition mockTransitionThree_;
    private IStateTransition mockTransitionFour_;

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
        ctrl_ = createControl();
        mockStateOne_ = ctrl_.createMock( IState.class );
        mockStateTwo_ = ctrl_.createMock( IState.class );
        mockTransitionOne_ = ctrl_.createMock( IStateTransition.class );
        mockTransitionTwo_ = ctrl_.createMock( IStateTransition.class );
        mockTransitionThree_ = ctrl_.createMock( IStateTransition.class );
        mockTransitionFour_ = ctrl_.createMock( IStateTransition.class );
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
        expect( mockStateOne_.getStateName() ).andReturn( "testState" ).anyTimes();

        expect( mockTransitionOne_.getStartingState() ).andReturn( mockStateOne_ ).anyTimes();
        expect( mockTransitionOne_.getTransitionName() ).andReturn( "testTransition" ).anyTimes();

        ctrl_.replay();

        Collection<IStateTransition> collection = StateTransitionManager.instance()
            .getListOfTransitionsForState( mockStateOne_ );
        assertThat( 0, equalTo( collection.size() ) );
        LOG.debug( "Retrieved [" + collection.size() + "] transitions for mocked state transition" );

        LOG.debug( "Loading Mock transition" );
        StateTransitionManager.instance().addTransitionToManager( mockTransitionOne_ );

        collection = StateTransitionManager.instance().getListOfTransitionsForState( mockStateOne_ );
        assertThat( 1, equalTo( collection.size() ) );
        LOG.debug( "Retrieved [" + collection.size() + "] transitions for mocked state transition" );
        LOG.debug( StateTransitionManager.instance() );

        assertThat( mockTransitionOne_, equalTo( collection.iterator().next() ) );

        ctrl_.verify();
    }

    /**
     * Tests that if no transitions are loaded, no state transitions
     * are returned.
     */
    @SuppressWarnings("boxing")
    @Test
    public void testRetrieveNoTransition()
    {
        expect( mockStateOne_.getStateName() ).andReturn( "testState" ).anyTimes();

        ctrl_.replay();

        LOG.debug( "Calling StateTransitionManager with state=[" + mockStateOne_.getStateName()
                   + "] argument so expecting no transitions to be returned" );
        Collection<IStateTransition> collection = StateTransitionManager.instance()
            .getListOfTransitionsForState( mockStateOne_ );

        assertThat( 0, equalTo( collection.size() ) );
        LOG.debug( StateTransitionManager.instance() );

        ctrl_.verify();
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
        expect( mockTransitionOne_.getStartingState() ).andReturn( mockStateOne_ ).anyTimes();
        expect( mockStateOne_.getStateName() ).andReturn( "testState" ).anyTimes();
        expect( mockTransitionOne_.getTransitionName() ).andReturn( "testTransition" ).anyTimes();
        expect( mockTransitionOne_.getStartingState() ).andReturn( mockStateOne_ ).anyTimes();

        expect( mockTransitionTwo_.getStartingState() ).andReturn( mockStateOne_ ).anyTimes();
        expect( mockTransitionTwo_.getStartingState() ).andReturn( mockStateOne_ ).anyTimes();

        ctrl_.replay();

        LOG.debug( "Loading Mock transitions" );
        StateTransitionManager.instance().addTransitionToManager( mockTransitionOne_ );
        StateTransitionManager.instance().addTransitionToManager( mockTransitionTwo_ );

        ctrl_.verify();
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
        expect( mockTransitionOne_.getStartingState() ).andReturn( mockStateOne_ ).anyTimes();
        expect( mockStateOne_.getStateName() ).andReturn( "testState1" ).anyTimes();
        expect( mockTransitionOne_.getTransitionName() ).andReturn( "testTransition1" ).anyTimes();

        expect( mockTransitionTwo_.getStartingState() ).andReturn( mockStateOne_ ).anyTimes();
        expect( mockTransitionTwo_.getTransitionName() ).andReturn( "testTransition2" ).anyTimes();

        expect( mockTransitionThree_.getStartingState() ).andReturn( mockStateOne_ ).anyTimes();
        expect( mockTransitionThree_.getTransitionName() ).andReturn( "testTransition3" )
            .anyTimes();

        expect( mockTransitionFour_.getStartingState() ).andReturn( mockStateTwo_ ).anyTimes();
        expect( mockStateTwo_.getStateName() ).andReturn( "testState2" ).anyTimes();
        expect( mockTransitionFour_.getTransitionName() ).andReturn( "testTransition4" ).anyTimes();

        ctrl_.replay();

        LOG.debug( "Loading Mock transitions" );
        StateTransitionManager.instance().addTransitionToManager( mockTransitionOne_ );
        StateTransitionManager.instance().addTransitionToManager( mockTransitionTwo_ );
        StateTransitionManager.instance().addTransitionToManager( mockTransitionThree_ );
        StateTransitionManager.instance().addTransitionToManager( mockTransitionFour_ );
        LOG.debug( StateTransitionManager.instance() );

        Collection<IStateTransition> collection = StateTransitionManager.instance()
            .getListOfTransitionsForState( mockStateOne_ );
        Assert.assertEquals( 3, collection.size() );
        LOG.debug( "Retrieved [" + collection.size() + "] transitions for mocked state 1" );

        collection = StateTransitionManager.instance().getListOfTransitionsForState( mockStateTwo_ );
        Assert.assertEquals( 1, collection.size() );
        LOG.debug( "Retrieved [" + collection.size() + "] transitions for mocked state 2" );

        collection = StateTransitionManager.instance().getAllTransitions();
        assertThat( 4, equalTo( collection.size() ) );
        Assert.assertEquals( 4, collection.size() );
        LOG.debug( "Retrieved [" + collection.size() + "] transitions for all states" );

        ctrl_.verify();
    }

}
