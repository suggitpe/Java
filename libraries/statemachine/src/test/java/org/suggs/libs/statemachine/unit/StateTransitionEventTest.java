/*
 * StateTransitionEventTest.java created on 2 Sep 2009 07:11:50 by suggitpe for project Libraries - State Machine
 * 
 */
package org.suggs.libs.statemachine.unit;

import org.suggs.libs.statemachine.StateTransitionEvent;
import org.suggs.libs.statemachine.impl.StateTransitionEventImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * Test suite for the state transition event implementation.
 * 
 * @author suggitpe
 * @version 1.0 2 Sep 2009
 */
public class StateTransitionEventTest {

    private static final Logger LOG = LoggerFactory.getLogger( StateTransitionEventTest.class );

    /** */
    @BeforeClass
    public static void doBeforeClass() {
        LOG.debug( "===================" + StateTransitionEventTest.class.getSimpleName() );
    }

    /** */
    @Before
    public void doBefore() {
        LOG.debug( "------------------- " );
    }

    /**
     * Simply tests that the state name has been correctly set at state construction
     */
    @Test
    public void stateNameExtraction() {
        final String EVENT_NAME = "TestEvent";
        StateTransitionEvent event = new StateTransitionEventImpl( EVENT_NAME );

        assertThat( event.getEventName(), equalTo( EVENT_NAME ) );
        LOG.debug( "Successfully created stateTransitionEvent=[" + event + "]" );
    }

    @Test
    public void equalsFalseForNullAndDifferentObject() {
        StateTransitionEventImpl eventA = new StateTransitionEventImpl( "event A" );
        assertFalse( eventA.equals( null ) );
        assertFalse( eventA.equals( new String( "Boom!" ) ) );
    }

    @SuppressWarnings("boxing")
    @Test
    public void hashcodeAndEqualsMatchForSameObject() {
        StateTransitionEventImpl eventA = new StateTransitionEventImpl( "event A" );
        StateTransitionEventImpl eventB = new StateTransitionEventImpl( "event A" );
        assertThat( eventA, equalTo( eventA ) );
        assertThat( eventA, equalTo( eventB ) );
        assertThat( eventA.hashCode(), equalTo( eventB.hashCode() ) );
    }

    @SuppressWarnings("boxing")
    @Test
    public void hashcodeAndEqualsMisMatchForDifferentObjects() {
        StateTransitionEventImpl eventA = new StateTransitionEventImpl( "event A" );
        StateTransitionEventImpl eventB = new StateTransitionEventImpl( "event B" );
        assertThat( eventA, not( equalTo( eventB ) ) );
        assertThat( eventA.hashCode(), not( equalTo( eventB.hashCode() ) ) );
    }

    @SuppressWarnings("boxing")
    @Test
    public void hashcodeAndEqualsMismatchForNull() {
        StateTransitionEventImpl eventA = new StateTransitionEventImpl( "event A" );
        StateTransitionEventImpl eventB = new StateTransitionEventImpl( null );
        assertThat( eventA, not( equalTo( eventB ) ) );
        assertThat( eventA.hashCode(), not( equalTo( eventB.hashCode() ) ) );
    }

}
