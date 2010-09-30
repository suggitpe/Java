/*
 * ConfigurationManager.java created on 15 Apr 2009 06:45:44 by suggitpe for project SandBox - JMS
 * 
 */
package com.ubs.reporting.configuration;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Wrapper class to encapsulate the configuration file.
 * 
 * @author suggitpe
 * @version 1.0 15 Apr 2009
 */
public final class ConfigurationManager {

    private static final Log LOG = LogFactory.getLog( ConfigurationManager.class );
    private static final String PROP_FILE = "masterfiles-receiver.properties";

    // here we pop all of the properties that we wish to manage
    /** */
    public static final String CONTEXT_FACTORY = "context.factory";
    /** */
    public static final String PROVIDER_URL = "provider.url";
    /** */
    public static final String USERNAME = "tibco.username";
    /** */
    public static final String PASSWORD = "tibco.password";
    /** */
    public static final String TOPIC_CONN_FACT = "topic.connectionfactory";
    /** */
    public static final String SUBSCR_MSG_SELECTOR = "subscriber.message.selector";
    /** */
    public static final String SUBSCR_DURABLE = "subscriber.durable.name";

    private static ConfigurationManager instance;
    private Properties data;

    // load up the instance of the properties file
    static {
        instance = new ConfigurationManager();
    }

    /**
     * Instance implementation from which we can get the
     * 
     * @return the instance of the singleton
     */
    public static ConfigurationManager instance() {
        return instance;
    }

    /**
     * Constructs a new instance.
     */
    private ConfigurationManager() {
        data = new Properties();
        try {
            data.load( getClass().getClassLoader().getResourceAsStream( PROP_FILE ) );
        }
        catch ( IOException ioe ) {
            throw new IllegalStateException( "Failed to load properties file [" + PROP_FILE + "]" );
        }

        if ( LOG.isDebugEnabled() ) {
            LOG.debug( "Loaded properties from [" + PROP_FILE + "]" );
        }
    }

    /**
     * Accessor method to the underlying properties, does not allow for null properties
     * 
     * @param aPropName
     *            the name of the property to set
     * @return the configuration property that the name relates to
     */
    public String getProperty( String aPropName ) {
        String ret = data.getProperty( aPropName );
        if ( ret == null ) {
            throw new IllegalStateException( "No configuration data found for " + aPropName );
        }
        return ret;
    }

}
