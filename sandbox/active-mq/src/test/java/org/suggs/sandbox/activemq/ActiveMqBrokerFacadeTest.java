package org.suggs.sandbox.activemq;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
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
        activeMqBroker.stopTheRunningAmqBroker();
    }

    @Test
    public void createsAStoppedBroker() throws Exception {
        BrokerFacade stoppedBroker = createAStoppedAmqBrokerOnAnyAvailablePort();
        assertThat(stoppedBroker.isBrokerRunning(), is(false));
    }

    @Test
    public void doesNotThrowAnExceptionWhenAttemptingToStopAStoppedBroker() throws Exception {
        BrokerFacade stoppedBroker = createAStoppedAmqBrokerOnAnyAvailablePort();
        stoppedBroker.stopTheRunningAmqBroker();
    }

    @Test
    public void createsARunningBroker() throws Exception {
        assertThat(activeMqBroker.isBrokerRunning(), is(true));
    }

    @Test
    @Ignore
    public void connectsToAnAlreadyRunningBroker() throws Exception {
        BrokerFacade otherBroker = bindToAnExistingBrokerOn(activeMqBroker.getBrokerUrl());
        assertThat(otherBroker.isBrokerRunning(), is(true));
        assertThat(activeMqBroker.isBrokerRunning(), is(true));
        assertThat(otherBroker.getBrokerUrl(), equalTo(activeMqBroker.getBrokerUrl()));
    }

    @Test
    public void readsAndWritesMessagesToADestinmation() throws Exception {
        activeMqBroker.withDestination(DESTINATION).writeMessage(MESSAGE);
        String theReadMessage = activeMqBroker.withDestination(DESTINATION).readMessage();
        assertThat(theReadMessage, is(equalTo(MESSAGE)));
    }
}
