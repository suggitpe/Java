package org.suggs.sandbox.activemq;

public interface BrokerFacade {

    JmsPersistenceFacade forDestination(String destination) throws Exception;

    void stopTheRunningBroker() throws Exception;

    boolean isBrokerRunning() throws Exception;

    String getBrokerUrl();

    BrokerFacade startBroker() throws Exception;
}
