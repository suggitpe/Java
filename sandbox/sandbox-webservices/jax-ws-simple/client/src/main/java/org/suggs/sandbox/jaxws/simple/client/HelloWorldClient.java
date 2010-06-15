/*
 * HelloWorldClient.java created on 10 Jun 2010 19:10:22 by suggitpe for project sandbox-webservices-jax-ws-simple-client
 * 
 */
package org.suggs.sandbox.jaxws.simple.client;

import org.suggs.sandbox.jaxws.simple.client.bindings.HelloWorld;
import org.suggs.sandbox.jaxws.simple.client.bindings.HelloWorldService;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Client that will call a wesbervice
 * 
 * @author suggitpe
 * @version 1.0 10 Jun 2010
 */
public final class HelloWorldClient {

    private static final Log LOG = LogFactory.getLog( HelloWorldClient.class );
    private static final String WS_URL = "http://sandboxhost:9091/jax-ws-simple/helloService";

    public void callWebService() {
        LOG.debug( "Calling webservice" );

        HelloWorld service = createWebService();
        String serviceResponse = service.sayHello( "Pete" );

        LOG.debug( "WeService response is [" + serviceResponse + "]" );
    }

    private HelloWorld createWebService() {
        try {
            WebServiceClient client = HelloWorldService.class.getAnnotation( WebServiceClient.class );
            HelloWorldService service = new HelloWorldService( new URL( WS_URL ),
                                                               new QName( client.targetNamespace(),
                                                                          client.name() ) );
            return service.getHelloWorldPort();
        }
        catch ( MalformedURLException mue ) {
            throw new IllegalArgumentException( "URL to webservice does not exist", mue );
        }
    }
}
