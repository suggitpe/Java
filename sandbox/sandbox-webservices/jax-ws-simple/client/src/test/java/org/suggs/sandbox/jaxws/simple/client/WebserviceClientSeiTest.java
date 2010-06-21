/*
 * WebserviceClientSeiTest.java created on 21 Jun 2010 19:27:48 by suggitpe for project sandbox-webservices-jax-ws-simple-client
 * 
 */
package org.suggs.sandbox.jaxws.simple.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Test class for the SEI Webservice
 * 
 * @author suggitpe
 * @version 1.0 21 Jun 2010
 */
public class WebserviceClientSeiTest extends AbstractWebserviceClientTest {

    private static final Log LOG = LogFactory.getLog( WebserviceClientSeiTest.class );

    /**
     * @see org.suggs.sandbox.jaxws.simple.client.AbstractWebserviceClientTest#doCreateClientCallback()
     */
    @Override
    protected ClientCallback doCreateClientCallback() {
        return new ClientCallback() {

            @Override
            public void callClient() {
                LOG.debug( "Calling SEI Webservice ..." );
                HelloWorldSeiClient client = new HelloWorldSeiClient();
                client.callWebService();
            }
        };
    }
}
