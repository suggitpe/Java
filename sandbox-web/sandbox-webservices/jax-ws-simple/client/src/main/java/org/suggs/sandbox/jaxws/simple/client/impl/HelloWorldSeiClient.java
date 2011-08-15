/*
 * HelloWorldSeiClient.java created on 10 Jun 2010 19:10:22 by suggitpe for project sandbox-webservices-jax-ws-simple-client
 * 
 */
package org.suggs.sandbox.jaxws.simple.client.impl;

import org.suggs.sandbox.jaxws.simple.client.bindings.HelloWorld;
import org.suggs.sandbox.jaxws.simple.client.bindings.HelloWorldService;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Client that will call a wesbervice
 * 
 * @author suggitpe
 * @version 1.0 10 Jun 2010
 */
public final class HelloWorldSeiClient {

    private static final Logger LOG = LoggerFactory.getLogger( HelloWorldSeiClient.class );
    private WebServiceClient webServiceClient = HelloWorldService.class.getAnnotation( WebServiceClient.class );

    public String callWebService( String aName ) {
        LOG.debug( "Calling webservice with client [" + webServiceClient + "]" );

        HelloWorld service = createWebService();
        String serviceResponse = service.sayHello( aName );
        return serviceResponse;
    }

    private HelloWorld createWebService() {
        try {
            HelloWorldService service = new HelloWorldService( new URL( HelloWorldBindings.WS_URL ),
                                                               new QName( webServiceClient.targetNamespace(),
                                                                          webServiceClient.name() ) );
            return service.getHelloWorldPort();
        }
        catch ( MalformedURLException mue ) {
            throw new IllegalArgumentException( "URL to webservice does not exist", mue );
        }
    }

}
