/*
 * TibcoAdminConnectionProperties.java created on 6 Aug 2008 18:17:06 by suggitpe for project SandBox - Tibco
 * 
 */
package org.suggs.sandbox.tibco;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tibco.tibjms.admin.TibjmsAdmin;
import com.tibco.tibjms.admin.TibjmsAdminException;

/**
 * Helper class to manage the creation of a Tibco Admin hook
 */
public final class TibcoAdminConnectionProperties {

    private static final Logger LOG = LoggerFactory.getLogger( TibcoAdminConnectionProperties.class );
    private static final String PROP_FILE = "tibco_connection.properties";

    private static TibcoAdminConnectionProperties instance;
    private Properties data;
    private TibjmsAdmin admin;

    // load up the instance of the properties file
    static {
        instance = new TibcoAdminConnectionProperties();
    }

    public static TibcoAdminConnectionProperties instance() {
        return instance;
    }

    private TibcoAdminConnectionProperties() {

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

    public String getProviderUrl() {
        return data.getProperty( "provider.url" );
    }

    public String getUsername() {
        return data.getProperty( "tibco.username" );
    }

    public String getPassword() {
        return data.getProperty( "tibco.pasword" );
    }

    public TibjmsAdmin getAdmin() {
        synchronized ( this ) {
            if ( admin == null ) {
                try {
                    admin = new TibjmsAdmin( getProviderUrl(), getUsername(), getPassword() );
                }
                catch ( TibjmsAdminException tjae ) {
                    LOG.error( "Error when creating the Tib JMS admin object", tjae );
                    throw new IllegalStateException( "Unable to connect to the Tibco EMS broker as admin" );
                }
            }
            return admin;
        }
    }

}
