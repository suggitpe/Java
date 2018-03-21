/*
 * JmxBookConfig.java created on 18 Feb 2008 19:31:19 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.jmxbook.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Singleton class to encapsulate the properties for the JmxBook application
 */
public final class JmxBookConfig {

    private static final Logger LOG = LoggerFactory.getLogger(JmxBookConfig.class);

    private static JmxBookConfig instance;

    private Properties properties;

    public static final String HTTP_PORT = "agent.http.port";
    public static final String RMI_PORT = "agent.rmi.port";
    public static final String RMI_URL_PREFIX = "agent.rmi.url.prefix";
    public static final String RMI_URL_POSTFIX = "agent.rmi.url.postfix";
    public static final String MBEAN_SERVERNAME = "server.mbean.name";

    static {
        instance = new JmxBookConfig();
        instance.loadConfig();
    }

    private JmxBookConfig() {
        LOG.debug("Creating new instance of the JMX Book Configuration helper");
        properties = new Properties();
    }

    public static JmxBookConfig getInstance() {
        return instance;
    }

    private void loadConfig() {
        String name = System.getProperty("jmxbook.config.filename");
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("System requires system property [jmxbook.config.filename] set to the name of the configuration filename");
        }

        LOG.debug("Loading system properties from file [" + name + "]");
        InputStream is = JmxBookConfig.class.getClassLoader().getResourceAsStream(name);
        if (is == null) {
            throw new IllegalStateException("Could not load configuration file [" + name + "]");
        }

        try {
            properties.load(is);
            is.close();
        } catch (IOException ioe) {
            throw new IllegalStateException("Could not load configuration file [" + name + "]", ioe);
        }
    }

    public String getCfgProperty(String aPropertyName) {
        return properties.getProperty(aPropertyName);
    }

    public String getRmiUrl() {
        return new StringBuffer(getCfgProperty(JmxBookConfig.RMI_URL_PREFIX)).append(getCfgProperty(JmxBookConfig.RMI_PORT))
                .append(getCfgProperty(JmxBookConfig.RMI_URL_POSTFIX))
                .toString();
    }

}
