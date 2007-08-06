/*
 * EmsConnectionAdapter.java created on 3 Aug 2007 06:13:32 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.manager.adapters;

import org.suggs.apps.mercury.model.connection.IJmsConnectionDetails;
import org.suggs.apps.mercury.model.connection.manager.IConnectionAdapter;

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
public class EmsConnectionAdapter implements IConnectionAdapter, InitializingBean
{

    private static final Log LOG = LogFactory.getLog( EmsConnectionAdapter.class );
    private String mInitialontextFactory_;

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception
    {
        Assert.notNull( mInitialontextFactory_, "Muct inject the initial context factory into the Ems Connection Adapter" );
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.manager.IConnectionAdapter#createJmsContext(org.suggs.apps.mercury.model.connection.IJmsConnectionDetails)
     */
    public Context createJmsContext( IJmsConnectionDetails aConnDetails )
    {
        String url = "tcp://" + aConnDetails.getConnectionHostname() + ":" + aConnDetails.getConnectionPort();
        LOG.debug( "Creating EMS context with [" + mInitialontextFactory_ + "] and [" + url + "]" );
        Properties p = new Properties();
        p.put( Context.INITIAL_CONTEXT_FACTORY, mInitialontextFactory_ );
        p.put( Context.PROVIDER_URL, url );

        Context ret = null;
        try
        {
            ret = new InitialContext( p );
        }
        catch ( NamingException ne )
        {
            LOG.warn( "Failed to create initial context" );
        }

        return ret;
    }

    /**
     * Getter for the initial context factory
     * 
     * @return the name of the initial context factory
     */
    public String getInitialContextFactory()
    {
        return mInitialontextFactory_;
    }

    /**
     * Setter for the initial context factory
     * 
     * @param aInitialContext
     *            the initial context factory
     */
    public void setInitialContextFactory( String aInitialContext )
    {
        mInitialontextFactory_ = aInitialContext;
    }

}
