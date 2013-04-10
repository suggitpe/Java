/*
 * ConfigManager.java created on 15 Apr 2009 06:45:44 by suggitpe for project SandBox - JMS
 * 
 */
package org.suggs.sandbox.jms;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Wrapper class to encapsulate the configuration file.
 * 
 * @author suggitpe
 * @version 1.0 15 Apr 2009
 */
public final class ConfigManager {

    private static final Logger LOG = LoggerFactory.getLogger( ConfigManager.class );
    private static final String PROP_FILE = "connection.properties";

    // here we pop all of the properties that we wish to manage
    /** */
    public static final String CONTEXT_FACTORY = "context.factory";
    /** */
    public static final String PROVIDER_URL = "provider.url";
    /** */
    public static final String USERNAME = "username";
    /** */
    public static final String PASSWORD = "password";
    /** */
    public static final String TOPIC_CONN_FACT = "topic.connectionfactory";
    /** */
    public static final String SUBSCR_MSG_SELECTOR = "subscriber.message.selector";
    /** */
    public static final String SUBSCR_DURABLE = "subscriber.durable.name";

    private static ConfigManager instance;
    private Properties data;

    // load up the instance of the properties file
    static {
        instance = new ConfigManager();
    }

    /**
     * Instance implementation from which we can get the
     * 
     * @return the instance of the singleton
     */
    public static ConfigManager instance() {
        return instance;
    }

    /**
     * Constructs a new instance.
     */
    private ConfigManager() {
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
            throw new IllegalStateException( "no configuration data found for " + aPropName );
        }
        return ret;
    }

    /**
     * Accessor method to the underlying properties
     * 
     * @param aPropName
     *            the name of the property to set
     * @return the configuration property that the name relates to
     */
    public String getNullableProperty( String aPropName ) {
        return data.getProperty( aPropName );
    }

}
