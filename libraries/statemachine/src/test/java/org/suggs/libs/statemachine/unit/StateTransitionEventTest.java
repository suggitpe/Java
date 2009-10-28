/*
 * StateTransitionEventTest.java created on 2 Sep 2009 07:11:50 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine.unit;

import org.suggs.libs.statemachine.IStateTransitionEvent;
import org.suggs.libs.statemachine.impl.StateTransitionEventImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

/**
 * Test suite for the state transition event implementation.
 * 
 * @author suggitpe
 * @version 1.0 2 Sep 2009
 */
public class StateTransitionEventTest
{

    private static final Log LOG = LogFactory.getLog( StateTransitionEventTest.class );

    /** */
    @BeforeClass
    public static void doBeforeClass()
    {
        LOG.debug( "===================" + StateTransitionEventTest.class.getSimpleName() );
    }

    /** */
    @Before
    public void doBefore()
    {
        LOG.debug( "------------------- " );
    }

    /**
     * Simply tests that the state name has been correctly set at
     * state construction
     */
    @Test
    public void testStateNameExtraction()
    {
        final String EVENT_NAME = "TestEvent";
        IStateTransitionEvent event = new StateTransitionEventImpl( EVENT_NAME );

        assertThat( event.getEventName(), equalTo( EVENT_NAME ) );
        LOG.debug( "Successfully created stateTransitionEvent=[" + event + "]" );
    }

    /**
     * Tests the that the equals, hashcode and toString methods work
     * correctly
     */
    @SuppressWarnings("boxing")
    @Test
    public void testEqualsHashcodeAndToString()
    {
        StateTransitionEventImpl event1a = new StateTransitionEventImpl( "event1" );
        StateTransitionEventImpl event1b = new StateTransitionEventImpl( "event1" );
        StateTransitionEventImpl event2 = new StateTransitionEventImpl( "event2" );

        // check equals method
        assertThat( event1a, not( sameInstance( event1b ) ) );
        assertThat( event1a, not( sameInstance( event2 ) ) );
        assertThat( event1a, equalTo( event1b ) );
        assertThat( event1a, not( equalTo( event2 ) ) );

        // check hashcode
        assertThat( event1a.hashCode(), equalTo( event1b.hashCode() ) );
        assertThat( event1a.hashCode(), not( equalTo( event2.hashCode() ) ) );

        LOG.debug( "StateTransitionEvent1a: " + event1a );
        LOG.debug( "StateTransitionEvent2: " + event2 );
    }

}
