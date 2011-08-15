/*
 * HelloWorldDispatchSourceClient.java created on 21 Jun 2010 19:17:35 by suggitpe for project sandbox-webservices-jax-ws-simple-client
 * 
 */
package org.suggs.sandbox.jaxws.simple.client.impl;

import org.suggs.sandbox.jaxws.simple.client.WebServiceClient;
import org.suggs.sandbox.jaxws.simple.client.bindings.ObjectFactory;
import org.suggs.sandbox.jaxws.simple.client.bindings.SayHello;
import org.suggs.sandbox.jaxws.simple.client.bindings.SayHelloResponse;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;

/**
 * TODO Write javadoc for HelloWorldDispatchSourceClient
 * 
 * @author suggitpe
 * @version 1.0 21 Jun 2010
 */
public class HelloWorldDispatchObjectClient implements WebServiceClient {

    @Override
    public String callWebService( String aName ) {
        QName serviceQName = new QName( HelloWorldBindings.WS_TARGET_NS, HelloWorldBindings.WS_NAME );
        QName portQName = new QName( HelloWorldBindings.WS_TARGET_NS, HelloWorldBindings.WS_PORT );

        try {
            Service service = Service.create( new URL( HelloWorldBindings.WS_URL ), serviceQName );
            // INTERESTING: using payload or message denotes whether message will include the soap envelope.

            JAXBContext jaxbContext = JAXBContext.newInstance( "org.suggs.sandbox.jaxws.simple.client.bindings" );
            Dispatch<Object> dispatch = service.createDispatch( portQName, jaxbContext, Service.Mode.PAYLOAD );

            JAXBElement<SayHello> request = createJaxbRequest( aName );
            @SuppressWarnings("unchecked")
            JAXBElement<SayHelloResponse> response = (JAXBElement<SayHelloResponse>) dispatch.invoke( request );
            return debugResponse( response );

        }
        catch ( MalformedURLException mue ) {
            throw new IllegalArgumentException( "URL to webservice does not exist", mue );
        }
        catch ( JAXBException je ) {
            throw new IllegalArgumentException( "Issues in the JAXB layer", je );
        }
    }

    private JAXBElement<SayHello> createJaxbRequest( String aName ) {
        ObjectFactory factory = new ObjectFactory();
        SayHello hello = factory.createSayHello();

        hello.setName( aName );
        return factory.createSayHello( hello );
    }

    private String debugResponse( JAXBElement<SayHelloResponse> aResponse ) {
        return aResponse.getValue().getReturn();
    }

}
