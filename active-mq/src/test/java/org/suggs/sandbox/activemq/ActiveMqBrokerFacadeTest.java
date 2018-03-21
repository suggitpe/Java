package org.suggs.sandbox.activemq;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.suggs.sandbox.activemq.ActiveMqBrokerFacade.*;


public class ActiveMqBrokerFacadeTest {

    private static final String DESTINATION = "dynamicQueues/DestinationQueue";
    private BrokerFacade activeMqBroker;
    private static final String MESSAGE = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, " +
            "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim " +
            "veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. " +
            "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat " +
            "nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia " +
            "deserunt mollit anim id est laborum.";

    @Before
    public void onSetup() throws Exception {
        activeMqBroker = createARunningAmqBrokerOnAnyAvailablePort();
    }

    @After
    public void tearDown() throws Exception {
        activeMqBroker.stopTheRunningBroker();
    }

    @Test
    public void createsAStoppedBroker() throws Exception {
        BrokerFacade stoppedBroker = createAStoppedAmqBrokerOnAnyAvailablePort();
        assertThat(stoppedBroker.isBrokerRunning()).isFalse();
    }

    @Test
    public void doesNotThrowAnExceptionWhenAttemptingToStopAStoppedBroker() throws Exception {
        BrokerFacade stoppedBroker = createAStoppedAmqBrokerOnAnyAvailablePort();
        stoppedBroker.stopTheRunningBroker();
    }

    @Test
    public void createsARunningBroker() throws Exception {
        assertThat(activeMqBroker.isBrokerRunning()).isTrue();
    }

    @Test
    @Ignore
    public void connectsToAnAlreadyRunningBroker() throws Exception {
        BrokerFacade otherBroker = bindToAnExistingBrokerOn(activeMqBroker.getBrokerUrl());
        assertThat(otherBroker.isBrokerRunning()).isTrue();
        assertThat(activeMqBroker.isBrokerRunning()).isTrue();
        assertThat(otherBroker.getBrokerUrl()).isEqualTo(activeMqBroker.getBrokerUrl());
    }

    @Test
    public void readsAndWritesMessagesToADestinmation() throws Exception {
        activeMqBroker.forDestination(DESTINATION).sendMessage(MESSAGE);
        String theReadMessage = activeMqBroker.forDestination(DESTINATION).readMessage();
        assertThat(theReadMessage).isEqualTo(MESSAGE);
    }
}
