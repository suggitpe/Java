/*
 * RmiClientFactory.java created on 14 Feb 2008 08:23:54 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.jmxbook.client;

import org.suggs.sandbox.jmx.jmxbook.config.JmxBookConfig;

import java.io.IOException;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

/**
 * Factory class to enable you to create RMI clients. This centralises the process of discovery of the JMX
 * Agent (and thus the MBEan server).
 */
public final class RmiClientFactory {

    private RmiClientFactory() {}

    public static final MBeanServerConnection getClient() throws IOException {
        String rmiUrl = JmxBookConfig.getInstance().getRmiUrl();
        JMXConnector c = JMXConnectorFactory.connect( new JMXServiceURL( rmiUrl ) );
        c.connect();
        return c.getMBeanServerConnection();
    }
}
