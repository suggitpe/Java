/*
 * AbstractWebserviceClientTest.java created on 21 Jun 2010 19:29:07 by suggitpe for project sandbox-webservices-jax-ws-simple-client
 * 
 */
package org.suggs.sandbox.jaxws.simple.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;

/**
 * Abstract class for all of the webservices tests.
 * 
 * @author suggitpe
 * @version 1.0 21 Jun 2010
 */
public abstract class AbstractWebserviceClientTest {

    private static final Log LOG = LogFactory.getLog( AbstractWebserviceClientTest.class );

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
        aClientCallback.callClient();
        long end = System.currentTimeMillis();

        LOG.debug( "Webservice call complete in [" + ( end - start ) + "] millis" );
    }

    interface ClientCallback {

        void callClient();
    }
}
