package org.suggs.sandbox.activemq;

import org.apache.activemq.broker.BrokerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.suggs.sandbox.activemq.SocketFinder.findNextAvailablePortBetween;

public class ActiveMqBrokerUtility {

    private static final Logger LOG = LoggerFactory.getLogger(ActiveMqBrokerUtility.class);

    private static final int DEFAULT_PORT = 61616;
    private static final int MAX_PORT_NUMBER = 70000;
    private static final String DEFAULT_HOST = "tcp://localhost:";
    private final String brokerUrl;
    private BrokerService brokerService;

    public static ActiveMqBrokerUtility createAStoppedAmqBrokerOnAnyAvailablePort() throws Exception {
        return new ActiveMqBrokerUtility(DEFAULT_HOST + findNextAvailablePortBetween(DEFAULT_PORT, MAX_PORT_NUMBER));
    }

    public static ActiveMqBrokerUtility connectToAnExistingBrokerOn(String aBrokerUrl) throws Exception {
        return new ActiveMqBrokerUtility(aBrokerUrl);
    }

    public static ActiveMqBrokerUtility createARunningAmqBrokerOnAnyAvailablePort() throws Exception {
        return createAStoppedAmqBrokerOnAnyAvailablePort().startBroker();
    }

    private ActiveMqBrokerUtility(String aBrokerUrl) throws Exception {
        brokerUrl = aBrokerUrl;
        brokerService = new BrokerService();
        brokerService.setPersistent(false);
        brokerService.setUseJmx(false);
        brokerService.addConnector(brokerUrl);
        LOG.debug("Connected to AMQ broker at [" + brokerUrl + "]");
    }

    public ActiveMqBrokerUtility startBroker() throws Exception {
        LOG.debug("Starting broker...");
        brokerService.start();
        return this;
    }

    public void stopTheRunningAmqBroker() throws Exception {
        if (brokerService == null) {
            return;
        }
        brokerService.stop();
        brokerService.waitUntilStopped();
    }

}
