/*
 * ContextHelper.java created on 16 Apr 2009 06:19:12 by suggitpe for project SandBox - JMS
 * 
 */
package com.ubs.orca.orcabridge.jmsclient.impl;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.orca.orcabridge.jmsclient.JmsClientException;

/**
 * The sole purpose of this class is to provide a mechanism by which
 * we can create the initial context for a JNDI resource.
 * 
 * @author suggitpe
 * @version 1.0 16 Apr 2009
 */
public class ContextBuilder
{

    private static final Log LOG = LogFactory.getLog( ContextBuilder.class );

    /**
     * This class only contains static methods and thus we have no
     * need for a ctor.
     */
    private ContextBuilder()
    {}

    /**
     * Creates an initial context to a JNDI managed resource.
     * 
     * @param aCtxFactory
     *            the name of the context factory class
     * @param aBrokerUrl
     *            the URL of the broker
     * @return the initial context to the JNDI broker
     * @throws JmsClientException
     */
    public static final Context buildInitialContextToJndi( String aCtxFactory, String aBrokerUrl )
                    throws JmsClientException
    {
        return buildInitialContextToJndi( aCtxFactory, aBrokerUrl, null, null );
    }

    /**
     * Creates an initial context to a JNDI managed resource.
     * 
     * @param aCtxFactory
     *            the name of the context factory class
     * @param aBrokerUrl
     *            the URL of the broker
     * @param aUsername
     *            username for the JNDI broker
     * @param aPassword
     *            password to the JNDI broker
     * @return the initial context to the JNDI broker
     * @throws JmsClientException
     */
    public static final InitialContext buildInitialContextToJndi( String aCtxFactory,
                                                                  String aBrokerUrl,
                                                                  String aUsername, String aPassword )
                    throws JmsClientException
    {
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put( Context.INITIAL_CONTEXT_FACTORY, aCtxFactory );
        env.put( Context.PROVIDER_URL, aBrokerUrl );
        if ( aUsername != null && !aUsername.equals( "" ) )
        {
            env.put( Context.SECURITY_PRINCIPAL, aUsername );
            if ( aPassword != null && !aPassword.equals( "" ) )
            {
                env.put( Context.SECURITY_CREDENTIALS, aPassword );
            }
        }

        try
        {
            return new InitialContext( env );
        }
        catch ( NamingException ne )
        {
            String err = "Failed to create initial context for " + aBrokerUrl;
            LOG.error( err, ne );
            throw new JmsClientException( err, ne );
        }
    }
}
