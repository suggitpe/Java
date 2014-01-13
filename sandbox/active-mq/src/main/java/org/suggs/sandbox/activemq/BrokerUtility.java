package org.suggs.sandbox.activemq;

public interface BrokerUtility {

    JmsUtility withDestination(String destination) throws Exception;

    void stopTheRunningAmqBroker() throws Exception;

    boolean isBrokerRunning() throws Exception;

    String getBrokerUrl();

    BrokerUtility startBroker() throws Exception;
}
