package org.suggs.sandbox.activemq;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.suggs.sandbox.activemq.ActiveMqBrokerUtility.createARunningAmqBrokerOnAnyAvailablePort;
import static org.suggs.sandbox.activemq.ActiveMqBrokerUtility.createAStoppedAmqBrokerOnAnyAvailablePort;


public class ActiveMqBrokerUtilityTest {

    private ActiveMqBrokerUtility activeMqBroker;
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
    public void doesNotThrowExceptionWhenUnstartedServiceIsStopped() throws Exception {
        ActiveMqBrokerUtility amqBroker = createAStoppedAmqBrokerOnAnyAvailablePort();
        amqBroker.stopTheRunningAmqBroker();
    }

    @Test
    public void createsARunningBroker() throws Exception {
        ActiveMqBrokerUtility amqBroker = createARunningAmqBrokerOnAnyAvailablePort();
        amqBroker.stopTheRunningAmqBroker();
    }
}
