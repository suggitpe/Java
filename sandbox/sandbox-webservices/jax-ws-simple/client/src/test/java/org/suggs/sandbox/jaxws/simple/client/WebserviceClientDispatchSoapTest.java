/*
 * WebserviceClientDispatchSourceTest.java created on 21 Jun 2010 19:39:06 by suggitpe for project sandbox-webservices-jax-ws-simple-client
 * 
 */
package org.suggs.sandbox.jaxws.simple.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Tests the calling of a webservice using the dispatch cource mechanism
 * 
 * @author suggitpe
 * @version 1.0 21 Jun 2010
 */
public class WebserviceClientDispatchSoapTest extends AbstractWebserviceClientTest {

    private static final Log LOG = LogFactory.getLog( WebserviceClientDispatchSoapTest.class );

    /**
     * @see org.suggs.sandbox.jaxws.simple.client.AbstractWebserviceClientTest#doCreateClientCallback()
     */
    @Override
    protected ClientCallback doCreateClientCallback() {
        return new ClientCallback() {

            @Override
            public void callClient() {
                LOG.debug( "Calling Dispatch Soap Webservice ..." );
                HelloWorldDispatchSoapClient client = new HelloWorldDispatchSoapClient();
                client.callWebServiceWithDispatchSoap();

            }
        };
    }
}
