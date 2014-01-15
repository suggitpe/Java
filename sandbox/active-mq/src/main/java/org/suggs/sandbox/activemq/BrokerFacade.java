package org.suggs.sandbox.activemq;

public interface BrokerFacade {

    JmsPersistenceFacade withDestination(String destination) throws Exception;

    void stopTheRunningAmqBroker() throws Exception;

    boolean isBrokerRunning() throws Exception;

    String getBrokerUrl();

    BrokerFacade startBroker() throws Exception;
}
