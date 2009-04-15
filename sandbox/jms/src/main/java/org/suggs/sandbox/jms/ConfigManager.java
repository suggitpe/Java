/*
 * ConfigManager.java created on 15 Apr 2009 06:45:44 by suggitpe for project SandBox - JMS
 * 
 */
package org.suggs.sandbox.jms;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Wrapper class to encapsulate the configuration file
 * 
 * @author suggitpe
 * @version 1.0 15 Apr 2009
 */
public class ConfigManager
{

    private static final Log LOG = LogFactory.getLog( ConfigManager.class );
    private static final String PROP_FILE = "tibco_connection.properties";

    // here we pop all of the properties that we wish to manage
    public static final String CONTEXT_FACTORY = "context.factory";
    public static final String PROVIDER_URL = "provider.url";
    public static final String USERNAME = "tibco.username";
    public static final String PASSWORD = "tibco.password";
    public static final String TOPIC_CONN_FACT = "topic.connectionfactory";

    private static ConfigManager mInstance_;
    private Properties mData_;

    // load up the instance of the properties file
    static
    {
        mInstance_ = new ConfigManager();
    }

    /**
     * Instance implementation from which we can get the
     * 
     * @return the instance of the singleton
     */
    public static ConfigManager instance()
    {
        return mInstance_;
    }

    /**
     * Constructs a new instance.
     */
    private ConfigManager()
    {
        mData_ = new Properties();
        try
        {
            mData_.load( getClass().getClassLoader().getResourceAsStream( PROP_FILE ) );
        }
        catch ( IOException ioe )
        {
            throw new IllegalStateException( "Failed to load properties file [" + PROP_FILE + "]" );
        }

        if ( LOG.isDebugEnabled() )
        {
            LOG.debug( "Loaded properties from [" + PROP_FILE + "]" );
        }
    }

    /**
     * Accessor method to the underlying properties
     * 
     * @return the configuration property that the name relates to
     */
    public String getProperty( String aPropName )
    {
        String ret = mData_.getProperty( aPropName );
        if ( ret == null )
            throw new IllegalStateException( "no configuration data found for " + aPropName );
        return ret;
    }

}
