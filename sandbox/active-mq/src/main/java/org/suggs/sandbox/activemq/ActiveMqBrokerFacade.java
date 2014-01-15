package org.suggs.sandbox.activemq;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.usage.SystemUsage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

import static org.suggs.sandbox.activemq.SocketFinder.findNextAvailablePortBetween;

public class ActiveMqBrokerFacade implements BrokerFacade {

    public static final int ONE_MEGABYTE = 1024 * 1024 * 8;
    private static final Logger LOG = LoggerFactory.getLogger(ActiveMqBrokerFacade.class);

    private static final int DEFAULT_PORT = 61616;
    private static final int MAX_PORT_NUMBER = 70000;
    private static final String DEFAULT_HOST = "tcp://localhost:";
    public static final String ACTIVEMQ_CONNECTION_FACTORY = "org.apache.activemq.jndi.ActiveMQInitialContextFactory";
    private final String brokerUrl;
    private BrokerService brokerService;

    public static BrokerFacade createAStoppedAmqBrokerOnAnyAvailablePort() throws Exception {
        return new ActiveMqBrokerFacade(DEFAULT_HOST + findNextAvailablePortBetween(DEFAULT_PORT, MAX_PORT_NUMBER));
    }

    public static BrokerFacade createARunningAmqBrokerOnAnyAvailablePort() throws Exception {
        return createAStoppedAmqBrokerOnAnyAvailablePort().startBroker();
    }

    public static BrokerFacade connectToAnExistingBrokerOn(String aBrokerUrl) throws Exception {
        return new ActiveMqBrokerFacade(aBrokerUrl);
    }

    public String getBrokerUrl() {
        return brokerUrl;
    }

    public boolean isBrokerRunning() {
        return brokerService.isStarted();
    }

    private ActiveMqBrokerFacade(String aBrokerUrl) throws Exception {
        brokerUrl = aBrokerUrl;
        buildBroker();
        LOG.debug("Connected to AMQ broker on [" + brokerUrl + "]");
    }

    private void buildBroker() throws Exception {
        brokerService = new BrokerService();
        brokerService.addConnector(brokerUrl);
        brokerService.setPersistent(false);
        brokerService.setUseJmx(true);
        SystemUsage systemUsage = brokerService.getSystemUsage();
        systemUsage.getStoreUsage().setLimit(ONE_MEGABYTE);
        systemUsage.getTempUsage().setLimit(ONE_MEGABYTE);
    }

    public ActiveMqBrokerFacade startBroker() throws Exception {
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

    public JmsPersistenceFacade withDestination(String destination) throws Exception {
        return new JmsPersistenceFacade(createJndiContext(), destination);
    }

    private Context createJndiContext() throws NamingException {
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, ACTIVEMQ_CONNECTION_FACTORY);
        env.put(Context.PROVIDER_URL, brokerUrl);
        return new InitialContext(env);
    }
}
