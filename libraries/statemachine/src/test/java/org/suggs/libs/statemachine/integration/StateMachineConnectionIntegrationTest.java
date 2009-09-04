/*
 * StateMachineConnectionIntegrationTest.java created on 3 Sep 2009 20:14:30 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine.integration;

import org.suggs.libs.statemachine.IState;
import org.suggs.libs.statemachine.IStateMachine;
import org.suggs.libs.statemachine.IStateMachineContext;
import org.suggs.libs.statemachine.IStateTransitionEvent;
import org.suggs.libs.statemachine.StateMachineException;
import org.suggs.libs.statemachine.impl.StateTransitionEventImpl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Integration test that uses a spring injected state machine to
 * replicate how the state machine library can be used to navigate
 * your way through a state machine. In this test we use the example
 * of a simple connection to show how the navigation process works.
 * 
 * @author suggitpe
 * @version 1.0 3 Sep 2009
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:xml/it-state-machine-connection-test-statemachine.xml" })
public class StateMachineConnectionIntegrationTest
{

    private static final Log LOG = LogFactory.getLog( StateMachineConnectionIntegrationTest.class );

    @Resource(name = "stateMachine")
    IStateMachine mStateMachine_;

    @Resource(name = "disconnectedState")
    IState mDisconnectedState_;

    @Resource(name = "initialState")
    IState mInitialState_;

    @Resource(name = "connectedState")
    IState mConnectedState_;

    @BeforeClass
    public static void doBeforeClass()
    {
        LOG.debug( "==================="
                   + StateMachineConnectionIntegrationTest.class.getSimpleName() );
    }

    @Before
    public void doBefore()
    {
        LOG.debug( "------------------- " );
    }

    @After
    public void doAfter()
    {
        LOG.debug( "------------------- " );
    }

    /**
     * Tests that we
     * 
     * @throws StateMachineException
     *             from the call to step
     */
    @Test
    public void testInitialisationOfStateMachineThroughSpring() throws StateMachineException
    {
        LOG.info( "Testing that we can initialise the state machine through Spring ... sanity check" );
        IState initial = mStateMachine_.getCurrentState();
        LOG.debug( "Injected state machine: " + mStateMachine_ );
        Assert.assertEquals( initial, mInitialState_ );
    }

    /**
     * Tests that we will transition from initial to disconnected and
     * stop there.
     * 
     * @throws StateMachineException
     *             from the call to step
     */
    @Test
    public void testTransitionFromInitialToDisconnected() throws StateMachineException
    {
        LOG.info( "Checking that with any event we will transition from Initial to Disconnected" );
        IState initial = mStateMachine_.getCurrentState();
        Assert.assertEquals( initial, mInitialState_ );

        mStateMachine_.step( new IStateMachineContext()
        {

            @Override
            public IStateTransitionEvent getStateTransitionEvent()
            {
                return new StateTransitionEventImpl( "DumyEvent for initial test" );
            }
        } );

        IState newState = mStateMachine_.getCurrentState();
        Assert.assertEquals( newState, mDisconnectedState_ );
        LOG.debug( "Verified that the state machine has correctly transitioned to the Disconnected State" );
    }

    /**
     * Tests that if we pass in an event that is not the 'connect'
     * event that we remain in the same state
     * 
     * @throws StateMachineException
     *             from the call to step
     */
    @Test
    public void testNoTransitionOccursFromIrrelevantEvent() throws StateMachineException
    {
        LOG.info( "Checking that we pass in a totally random event we stay in the same overall state" );
        Assert.assertEquals( mStateMachine_.getCurrentState(), mDisconnectedState_ );
        mStateMachine_.step( new IStateMachineContext()
        {

            @Override
            public IStateTransitionEvent getStateTransitionEvent()
            {
                return new StateTransitionEventImpl( "notRelevantEvent" );
            }
        } );
        Assert.assertEquals( mStateMachine_.getCurrentState(), mDisconnectedState_ );
    }

    /**
     * Tests that if the 'connect' event
     * 
     * @throws StateMachineException
     */
    @Test
    public void testTransitionFromDisconnectedToConnected() throws StateMachineException
    {
        LOG.info( "Checking that when we pass in a connect event that we transition through the connecting state and onto the connected state" );
        Assert.assertEquals( mStateMachine_.getCurrentState(), mDisconnectedState_ );
        mStateMachine_.step( new IStateMachineContext()
        {

            @Override
            public IStateTransitionEvent getStateTransitionEvent()
            {
                return new StateTransitionEventImpl( "connect" );
            }
        } );
        Assert.assertEquals( mStateMachine_.getCurrentState(), mConnectedState_ );
    }

    /**
     * Tests that when we pass in an event of disconnect that we
     * transition to the disconnected state
     * 
     * @throws StateMachineException
     *             from the call to step
     */
    @Test
    public void testTransitionFromConnectedToDisconnected() throws StateMachineException
    {
        LOG.info( "Checking that when we pass in a disconnect event that we transition through the disconnecting state and onto the disconnected state" );
        Assert.assertEquals( mStateMachine_.getCurrentState(), mConnectedState_ );
        mStateMachine_.step( new IStateMachineContext()
        {

            @Override
            public IStateTransitionEvent getStateTransitionEvent()
            {
                return new StateTransitionEventImpl( "disconnect" );
            }
        } );
        Assert.assertEquals( mStateMachine_.getCurrentState(), mDisconnectedState_ );
    }
}
