/*
 * ContextHelper.java created on 16 Apr 2009 06:19:12 by suggitpe for project SandBox - JMS
 * 
 */
package org.suggs.sandbox.jms;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The sole purpose of this class is to provide a mechanism by which we can create the initial context into
 * the broker.
 */
public final class ContextHelper {

    private static final Logger LOG = LoggerFactory.getLogger( ContextHelper.class );

    private ContextHelper() {}

    static InitialContext createInitialContext() throws NamingException {
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put( Context.INITIAL_CONTEXT_FACTORY,
                 ConfigManager.instance().getProperty( ConfigManager.CONTEXT_FACTORY ) );
        env.put( Context.PROVIDER_URL, ConfigManager.instance().getProperty( ConfigManager.PROVIDER_URL ) );
        String user = ConfigManager.instance().getProperty( ConfigManager.USERNAME );
        if ( user != null && !user.equals( "" ) ) {
            env.put( Context.SECURITY_PRINCIPAL, user );
            String pass = ConfigManager.instance().getProperty( ConfigManager.PASSWORD );
            if ( pass != null && !pass.equals( "" ) ) {
                env.put( Context.SECURITY_CREDENTIALS, pass );
            }
        }
        LOG.debug( "Initial context created" );
        return new InitialContext( env );
    }

}
