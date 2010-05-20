/*
 * TibcoConnectionProperties.java created on 5 Aug 2008 18:39:04 by suggitpe for project SandBox - Tibco
 * 
 */
package org.suggs.sandbox.tibco;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Singleton class that encapsulates the access to the configuration file.
 * 
 * @author suggitpe
 * @version 1.0 5 Aug 2008
 */
public final class TibcoConnectionProperties {

    private static final Log LOG = LogFactory.getLog( TibcoConnectionProperties.class );
    private static final String PROP_FILE = "tibco_connection.properties";

    private static TibcoConnectionProperties instance;
    private Properties data;

    // load up the instance of the properties file
    static {
        instance = new TibcoConnectionProperties();
    }

    /**
     * Instance implementation from which we can get the
     * 
     * @return the instance of the singleton
     */
    public static TibcoConnectionProperties instance() {
        return instance;
    }

    /**
     * Constructs a new instance.
     */
    private TibcoConnectionProperties() {
        data = new Properties();
        try {
            data.load( this.getClass().getClassLoader().getResourceAsStream( PROP_FILE ) );
        }
        catch ( IOException ioe ) {
            throw new IllegalStateException( "Failed to load properties file [" + PROP_FILE + "]" );
        }

        if ( LOG.isDebugEnabled() ) {
            LOG.debug( "Loaded properties from [" + PROP_FILE + "]" );
        }
    }

    /**
     * Getter for the context factory
     * 
     * @return the context factory value
     */
    public String getContextFactory() {
        return data.getProperty( "context.factory" );
    }

    /**
     * Getter for the provider URL
     * 
     * @return the provider URL
     */
    public String getProviderUrl() {
        return data.getProperty( "provider.url" );
    }

    /**
     * Getter for the user name.
     * 
     * @return the user name.
     */
    public String getUsername() {
        return data.getProperty( "tibco.username" );
    }

    /**
     * Getter for the password.
     * 
     * @return the password.
     */
    public String getPassword() {
        return data.getProperty( "tibco.pasword" );
    }

    /**
     * Helper method to put together the initial context environment together.
     * 
     * @return a hashtable of the relevant properties for the Tibco Initial Context
     * @throws NamingException
     */
    public InitialContext getInitialContext() throws NamingException {
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put( Context.INITIAL_CONTEXT_FACTORY, getContextFactory() );
        env.put( Context.PROVIDER_URL, getProviderUrl() );
        String user = getUsername();
        if ( user != null && !user.equals( "" ) ) {
            env.put( Context.SECURITY_PRINCIPAL, user );
            String pass = getPassword();
            if ( pass != null && !pass.equals( "" ) ) {
                env.put( Context.SECURITY_CREDENTIALS, pass );
            }
        }

        LOG.debug( "Creating initial context with: " + env );

        return new InitialContext( env );
    }

}
