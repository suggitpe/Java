/*
 * HelloWorldDispatchSourceClient.java created on 21 Jun 2010 19:17:35 by suggitpe for project sandbox-webservices-jax-ws-simple-client
 * 
 */
package org.suggs.sandbox.jaxws.simple.client;

import org.suggs.sandbox.jaxws.simple.client.bindings.ObjectFactory;
import org.suggs.sandbox.jaxws.simple.client.bindings.SayHello;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * TODO Write javadoc for HelloWorldDispatchSourceClient
 * 
 * @author suggitpe
 * @version 1.0 21 Jun 2010
 */
public class HelloWorldDispatchObjectClient {

    private static final Log LOG = LogFactory.getLog( HelloWorldDispatchObjectClient.class );

    public void callWebServiceWithDispatchObject() {
        QName serviceQName = new QName( HelloWorldBindings.WS_TARGET_NS, HelloWorldBindings.WS_NAME );
        QName portQName = new QName( HelloWorldBindings.WS_TARGET_NS, HelloWorldBindings.WS_PORT );

        try {
            Service service = Service.create( new URL( HelloWorldBindings.WS_URL ), serviceQName );
            // INTERESTING: using payload or message denotes whether message will include the soap envelope.

            JAXBContext jaxbContext = JAXBContext.newInstance( "org.suggs.sandbox.jaxws.simple.client.bindings" );
            Dispatch<Source> dispatch = service.createDispatch( portQName, Source.class, Service.Mode.PAYLOAD );
            ObjectFactory factory = new ObjectFactory();

            SayHello hello = factory.createSayHello();

        }
        catch ( MalformedURLException mue ) {
            throw new IllegalArgumentException( "URL to webservice does not exist", mue );
        }
        catch ( JAXBException je ) {
            throw new IllegalArgumentException( "Issues in the JAXB layer", je );
        }
    }

    private StringReader createSoapRequestReader() {
        StringBuilder builder = new StringBuilder();
        builder.append( "<ns1:sayHello xmlns:ns1=\"http://test.suggs.org.uk\">" );
        builder.append( "<aName>wonderful</aName>" );
        builder.append( "</ns1:sayHello>" );
        return new StringReader( builder.toString() );
    }

    private void debugResponse( Source aResponse ) {
        try {
            Transformer copier = TransformerFactory.newInstance().newTransformer();
            Writer stringWriter = new StringWriter();
            copier.transform( aResponse, new StreamResult( stringWriter ) );
            LOG.debug( "Response =[" + stringWriter.toString() + "]" );
        }
        catch ( TransformerConfigurationException tce ) {
            throw new IllegalArgumentException( "Failed to build and configure the transformer", tce );
        }
        catch ( TransformerException te ) {
            throw new IllegalArgumentException( "Failed to transform", te );
        }

    }

}
