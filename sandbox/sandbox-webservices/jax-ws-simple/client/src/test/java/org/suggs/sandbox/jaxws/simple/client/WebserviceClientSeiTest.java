/*
 * WebserviceClientSeiTest.java created on 21 Jun 2010 19:27:48 by suggitpe for project sandbox-webservices-jax-ws-simple-client
 * 
 */
package org.suggs.sandbox.jaxws.simple.client;

import org.suggs.sandbox.jaxws.simple.client.impl.HelloWorldSeiClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test class for the SEI Webservice
 * 
 * @author suggitpe
 * @version 1.0 21 Jun 2010
 */
public class WebserviceClientSeiTest extends AbstractWebserviceClientTest {

    private static final Logger LOG = LoggerFactory.getLogger( WebserviceClientSeiTest.class );

    /**
     * @see org.suggs.sandbox.jaxws.simple.client.AbstractWebserviceClientTest#doCreateClientCallback()
     */
    @Override
    protected ClientCallback doCreateClientCallback() {
        return new ClientCallback() {

            @Override
            public String callClient( String aName ) {
                LOG.debug( "Calling SEI Webservice ..." );
                HelloWorldSeiClient client = new HelloWorldSeiClient();
                return client.callWebService( aName );
            }
        };
    }
}
