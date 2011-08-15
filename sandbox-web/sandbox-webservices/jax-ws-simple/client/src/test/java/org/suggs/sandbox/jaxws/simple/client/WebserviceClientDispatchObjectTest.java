/*
 * WebserviceClientDispatchSourceTest.java created on 21 Jun 2010 19:39:06 by suggitpe for project sandbox-webservices-jax-ws-simple-client
 * 
 */
package org.suggs.sandbox.jaxws.simple.client;

import org.suggs.sandbox.jaxws.simple.client.impl.HelloWorldDispatchObjectClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tests the calling of a webservice using the dispatch cource mechanism
 * 
 * @author suggitpe
 * @version 1.0 21 Jun 2010
 */
public class WebserviceClientDispatchObjectTest extends AbstractWebserviceClientTest {

    private static final Logger LOG = LoggerFactory.getLogger( WebserviceClientDispatchObjectTest.class );

    /**
     * @see org.suggs.sandbox.jaxws.simple.client.AbstractWebserviceClientTest#doCreateClientCallback()
     */
    @Override
    protected ClientCallback doCreateClientCallback() {
        return new ClientCallback() {

            @Override
            public String callClient( String aName ) {
                LOG.debug( "Calling Dispatch Object Webservice ..." );
                HelloWorldDispatchObjectClient client = new HelloWorldDispatchObjectClient();
                return client.callWebService( aName );

            }
        };
    }
}
