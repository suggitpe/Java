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
 * Factory class to enable you to create RMI clients.
 * 
 * @author suggitpe
 * @version 1.0 14 Feb 2008
 */
public class RmiClientFactory

{

    /**
     * Static method to return an instance of the JMXConnector
     * 
     * @return the JMXConnector
     */
    public static final MBeanServerConnection getClient() throws IOException
    {
        String rmiUrl = JmxBookConfig.getInstance().getRmiUrl();
        JMXConnector c = JMXConnectorFactory.connect( new JMXServiceURL( rmiUrl ) );
        c.connect();
        return c.getMBeanServerConnection();
    }

}
