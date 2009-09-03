/*
 * StateTransitionEventTest.java created on 2 Sep 2009 07:11:50 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine.unit;

import org.suggs.libs.statemachine.IStateTransitionEvent;
import org.suggs.libs.statemachine.impl.StateTransitionEventImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test suite for the state transition event implementation.
 * 
 * @author suggitpe
 * @version 1.0 2 Sep 2009
 */
public class StateTransitionEventTest
{

    private static final Log LOG = LogFactory.getLog( StateTransitionEventTest.class );

    @BeforeClass
    public static void doBeforeClass()
    {
        LOG.debug( "===================" + StateTransitionEventTest.class.getSimpleName() );
    }

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

        Assert.assertEquals( event.getEventName(), EVENT_NAME );
        LOG.debug( "Successfully created stateTransitionEvent=[" + event + "]" );
    }

    /**
     * Tests the that the equals, hashcode and toString methods work
     * correctly
     */
    @Test
    public void testEqualsHashcodeAndToString()
    {
        StateTransitionEventImpl event1a = new StateTransitionEventImpl( "event1" );
        StateTransitionEventImpl event1b = new StateTransitionEventImpl( "event1" );
        StateTransitionEventImpl event2 = new StateTransitionEventImpl( "event2" );

        // check equals method
        Assert.assertNotSame( event1a, event1b );
        Assert.assertNotSame( event1a, event2 );
        Assert.assertEquals( event1a, event1b );
        Assert.assertFalse( event1a.equals( event2 ) );

        // check hashcode
        Assert.assertEquals( event1a.hashCode(), event1b.hashCode() );
        Assert.assertFalse( event1a.hashCode() == event2.hashCode() );

        LOG.debug( "StateTransitionEvent1a: " + event1a );
        LOG.debug( "StateTransitionEvent2: " + event2 );
    }

}
