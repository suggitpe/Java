/*
 * ContextBuilderTest.java created on 6 Oct 2009 07:21:43 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.jmsclient.impl;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ubs.orca.orcabridge.jmsclient.JmsClientException;

/**
 * Test suite to test the ContextBuilder class.
 * 
 * @author suggitpe
 * @version 1.0 6 Oct 2009
 */
public class ContextBuilderTest
{

    private static final Log LOG = LogFactory.getLog( ContextBuilderTest.class );

    private static final String FACTORY = "com.tibco.tibjms.naming.TibjmsInitialContextFactory";
    private static final String URL = "tcp://localhost:7222";
    private static final String TIB_URL = "tibjmsnaming://localhost:7222";

    @BeforeClass
    public static void doBeforeClass()
    {
        LOG.debug( "=================== " + ContextBuilderTest.class.getSimpleName() );
    }

    @Before
    public void doBefore()
    {
        LOG.debug( "-------------" );
    }

    /**
     * Tests that the hashtable created for the initial context
     * contains all the required data based on the initial parameters.
     */
    @Test
    public void testEnvHashtableWithAllDetails()
    {
        String username = "foo";
        String password = "bar";
        LOG.debug( "Building env table with all details passed in" );
        Hashtable<String, String> env = ContextBuilder.createEnvHashTable( FACTORY,
                                                                           URL,
                                                                           username,
                                                                           password );

        Assert.assertThat( env.get( Context.INITIAL_CONTEXT_FACTORY ),
                           new IsEqual<String>( FACTORY ) );
        Assert.assertThat( env.get( Context.PROVIDER_URL ), new IsEqual<String>( URL ) );
        Assert.assertThat( env.get( Context.SECURITY_PRINCIPAL ), new IsEqual<String>( username ) );
        Assert.assertThat( env.get( Context.SECURITY_CREDENTIALS ), new IsEqual<String>( password ) );
    }

    /**
     * Tests that if we provide no security into the hashtable that it
     * will not populate any security
     */
    @Test
    public void testHashtableWithNoSecurity()
    {
        LOG.debug( "Building env table with no security details passed in" );
        Hashtable<String, String> env = ContextBuilder.createEnvHashTable( FACTORY, URL, null, null );

        Assert.assertThat( env.get( Context.INITIAL_CONTEXT_FACTORY ),
                           new IsEqual<String>( FACTORY ) );
        Assert.assertThat( env.get( Context.PROVIDER_URL ), new IsEqual<String>( URL ) );
        Assert.assertThat( env.get( Context.SECURITY_PRINCIPAL ), new IsNull<String>() );
        Assert.assertThat( env.get( Context.SECURITY_CREDENTIALS ), new IsNull<String>() );
    }

    /**
     * Tests that if we only pass in a username that the password
     * remains null
     */
    @Test
    public void testHashtableWithOnlyUsername()
    {
        String username = "foo";
        LOG.debug( "Building env table with only username passed in" );
        Hashtable<String, String> env = ContextBuilder.createEnvHashTable( FACTORY,
                                                                           URL,
                                                                           username,
                                                                           null );

        Assert.assertThat( env.get( Context.INITIAL_CONTEXT_FACTORY ),
                           new IsEqual<String>( FACTORY ) );
        Assert.assertThat( env.get( Context.PROVIDER_URL ), new IsEqual<String>( URL ) );
        Assert.assertThat( env.get( Context.SECURITY_PRINCIPAL ), new IsEqual<String>( username ) );
        Assert.assertThat( env.get( Context.SECURITY_CREDENTIALS ), new IsNull<String>() );
    }

    /**
     * Tests that if we pass in a username and blank password that the
     * password remains null
     */
    @Test
    public void testHashtableWithUsernameAndBlankPassword()
    {
        String username = "foo";
        String password = "";
        LOG.debug( "Building env table with username and a blank username passed in" );
        Hashtable<String, String> env = ContextBuilder.createEnvHashTable( FACTORY,
                                                                           URL,
                                                                           username,
                                                                           password );

        Assert.assertThat( env.get( Context.INITIAL_CONTEXT_FACTORY ),
                           new IsEqual<String>( FACTORY ) );
        Assert.assertThat( env.get( Context.PROVIDER_URL ), new IsEqual<String>( URL ) );
        Assert.assertThat( env.get( Context.SECURITY_PRINCIPAL ), new IsEqual<String>( username ) );
        Assert.assertThat( env.get( Context.SECURITY_CREDENTIALS ), new IsNull<String>() );
    }

    /**
     * Tests that when we create an initial context with no security
     * details then we get a valid context.
     * 
     * @throws JmsClientException
     * @throws NamingException
     */
    @Test
    public void testContextCreationFromNoSecurity() throws JmsClientException, NamingException
    {
        LOG.debug( "Building Initial Context with no security details" );
        Context ctx = ContextBuilder.buildInitialContextToJndi( FACTORY, URL );

        Assert.assertThat( (String) ctx.getEnvironment().get( Context.INITIAL_CONTEXT_FACTORY ),
                           new IsEqual<String>( FACTORY ) );
        Assert.assertThat( (String) ctx.getEnvironment().get( Context.PROVIDER_URL ),
                           new IsEqual<String>( TIB_URL ) );
        Assert.assertThat( (String) ctx.getEnvironment().get( Context.SECURITY_PRINCIPAL ),
                           new IsNull<String>() );
        Assert.assertThat( (String) ctx.getEnvironment().get( Context.SECURITY_CREDENTIALS ),
                           new IsNull<String>() );
    }

    /**
     * Test initial context creation for full information
     * 
     * @throws JmsClientException
     * @throws NamingException
     */
    @Test
    public void testContextCreationFromFullDetails() throws JmsClientException, NamingException
    {
        String username = "foo";
        String password = "bar";
        LOG.debug( "Building Initial Context with full details" );
        Context ctx = ContextBuilder.buildInitialContextToJndi( FACTORY, URL, username, password );

        Assert.assertThat( (String) ctx.getEnvironment().get( Context.INITIAL_CONTEXT_FACTORY ),
                           new IsEqual<String>( FACTORY ) );
        Assert.assertThat( (String) ctx.getEnvironment().get( Context.PROVIDER_URL ),
                           new IsEqual<String>( TIB_URL ) );
        Assert.assertThat( (String) ctx.getEnvironment().get( Context.SECURITY_PRINCIPAL ),
                           new IsEqual<String>( username ) );
        Assert.assertThat( (String) ctx.getEnvironment().get( Context.SECURITY_CREDENTIALS ),
                           new IsEqual<String>( password ) );
    }
}
