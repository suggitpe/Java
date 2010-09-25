/*
 * StateMachineSteps.java created on 7 Sep 2010 18:31:15 by suggitpe for project state-machine-lib
 * 
 */
package org.suggs.libs.statemachine.jbehave.steps;

import org.suggs.libs.statemachine.State;
import org.suggs.libs.statemachine.StateMachine;
import org.suggs.libs.statemachine.StateMachineContext;
import org.suggs.libs.statemachine.StateMachineException;
import org.suggs.libs.statemachine.StateTransitionEvent;
import org.suggs.libs.statemachine.impl.StateTransitionEventImpl;
import org.suggs.libs.statemachine.jbehave.springstories.TraverseStateMachine;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.BeforeStories;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Steps implementation for the State Machine tests.
 * 
 * @author suggitpe
 * @version 1.0 7 Sep 2010
 */
public class StateMachineSteps {

    private static final Log LOG = LogFactory.getLog( StateMachineSteps.class );

    protected StateMachine stateMachine;

    private Map<String, State> stateMap;

    @BeforeStories
    public void doBeforeStories() {
        LOG.debug( "=================== Start: " + TraverseStateMachine.class.getSimpleName() );
    }

    @AfterStories
    public void doAfterStories() {
        LOG.debug( "=================== End: " + TraverseStateMachine.class.getSimpleName() );
    }

    @Given("an unused state machine")
    public void aStateMachineExists() {
        stateMachine.reset();
    }

    @When("no events are received")
    public void noEventsReceived() {
        LOG.debug( "Not sending any events at the state manager" );
    }

    @When("a $eventType event is received")
    public void aEventIsReceived( @Named("eventType") String eventType ) throws StateMachineException {
        LOG.debug( "Sending a [" + eventType + "] event to the state machine under test" );
        stateMachine.step( createStateMachineContext( eventType ) );
    }

    @Then("the state should be $stateName")
    public void theStateShouldBe( @Named("stateName") String aStateName ) {
        LOG.debug( "Checking that the state is [" + aStateName + "]" );
        assertThat( "In the transition we have ended up in the wrong state.",
                    stateMachine.getCurrentState(),
                    equalTo( stateMap.get( aStateName ) ) );
    }

    private StateMachineContext createStateMachineContext( final String aEventType ) {
        return new StateMachineContext() {

            @Override
            public StateTransitionEvent getStateTransitionEvent() {
                return new StateTransitionEventImpl( aEventType );
            }
        };
    }

    /**
     * Sets the stateMachine field to the specified value.
     * 
     * @param aStateMachine
     *            The stateMachine to set.
     */
    public void setStateMachine( StateMachine aStateMachine ) {
        stateMachine = aStateMachine;
    }

    /**
     * Returns the value of stateMachine.
     * 
     * @return Returns the stateMachine.
     */
    public StateMachine getStateMachine() {
        return stateMachine;
    }

    /**
     * Returns the value of stateMap.
     * 
     * @return Returns the stateMap.
     */
    public Map<String, State> getStateMap() {
        return stateMap;
    }

    /**
     * Sets the stateMap field to the specified value.
     * 
     * @param aStateMap
     *            The stateMap to set.
     */
    public void setStateMap( Map<String, State> aStateMap ) {
        stateMap = aStateMap;
    }

}
