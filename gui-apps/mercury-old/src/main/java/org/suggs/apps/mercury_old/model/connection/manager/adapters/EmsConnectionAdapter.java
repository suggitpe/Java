/*
 * EmsConnectionAdapter.java created on 3 Aug 2007 06:13:32 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury_old.model.connection.manager.adapters;

import org.suggs.apps.mercury_old.model.connection.IConnectionDetails;
import org.suggs.apps.mercury_old.model.connection.manager.IConnectionAdapter;

import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * Adapter for the EMS middleware implementation.
 * 
 * @author suggitpe
 * @version 1.0 3 Aug 2007
 */
public class EmsConnectionAdapter implements IConnectionAdapter, InitializingBean {

    private static final Log LOG = LogFactory.getLog( EmsConnectionAdapter.class );
    private String initialontextFactory;

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() {
        Assert.notNull( initialontextFactory,
                        "Muct inject the initial context factory into the Ems Connection Adapter" );
    }

    /**
     * @see org.suggs.apps.mercury_old.model.connection.manager.IConnectionAdapter#createJmsContext(org.suggs.apps.mercury_old.model.connection.IConnectionDetails)
     */
    @Override
    public Context createJmsContext( IConnectionDetails aConnDetails ) {
        String url = "tcp://" + aConnDetails.getHostname() + ":" + aConnDetails.getPort();
        LOG.debug( "Creating EMS context with [" + initialontextFactory + "] and [" + url + "]" );
        Properties p = new Properties();
        p.put( Context.INITIAL_CONTEXT_FACTORY, initialontextFactory );
        p.put( Context.PROVIDER_URL, url );

        Context ret = null;
        try {
            ret = new InitialContext( p );
        }
        catch ( NamingException ne ) {
            LOG.warn( "Failed to create initial context" );
        }

        return ret;
    }

    /**
     * @see org.suggs.apps.mercury_old.model.connection.manager.IConnectionAdapter#findAllBrokerObjects()
     */
    @Override
    public Map<String, String> findAllBrokerObjects() {
        // TODO need to do the impl for all of the broker metadata
        // info
        return null;
    }

    /**
     * Getter for the initial context factory
     * 
     * @return the name of the initial context factory
     */
    public String getInitialContextFactory() {
        return initialontextFactory;
    }

    /**
     * Setter for the initial context factory
     * 
     * @param aInitialContext
     *            the initial context factory
     */
    public void setInitialContextFactory( String aInitialContext ) {
        initialontextFactory = aInitialContext;
    }

}
