/*
 * AbstractWebserviceClientTest.java created on 21 Jun 2010 19:29:07 by suggitpe for project sandbox-webservices-jax-ws-simple-client
 * 
 */
package org.suggs.sandbox.jaxws.simple.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Abstract class for all of the webservices tests.
 * 
 * @author suggitpe
 * @version 1.0 21 Jun 2010
 */
public abstract class AbstractWebserviceClientTest {

    private static final Logger LOG = LoggerFactory.getLogger( AbstractWebserviceClientTest.class );
    private static final String NAME = "Pete";
    private static final String RESPONSE = "Hello " + NAME + "!";

    @Before
    public void doBefore() {
        LOG.debug( "-----------------------" );
    }

    @Test
    public void testClient() {
        callClient( doCreateClientCallback() );
    }

    protected abstract ClientCallback doCreateClientCallback();

    private static void callClient( ClientCallback aClientCallback ) {
        long start = System.currentTimeMillis();
        String response = aClientCallback.callClient( NAME );
        long end = System.currentTimeMillis();
        LOG.debug( "Response=[" + response + "]" );
        assertThat( response, equalTo( RESPONSE ) );

        LOG.debug( "Webservice call complete in [" + ( end - start ) + "] millis" );
    }

    interface ClientCallback {

        String callClient( String aName );
    }
}
