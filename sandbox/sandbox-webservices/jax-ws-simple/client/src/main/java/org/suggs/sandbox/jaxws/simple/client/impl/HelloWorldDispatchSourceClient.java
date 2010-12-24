/*
 * HelloWorldDispatchSourceClient.java created on 21 Jun 2010 19:17:35 by suggitpe for project sandbox-webservices-jax-ws-simple-client
 * 
 */
package org.suggs.sandbox.jaxws.simple.client.impl;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO Write javadoc for HelloWorldDispatchSourceClient
 * 
 * @author suggitpe
 * @version 1.0 21 Jun 2010
 */
public class HelloWorldDispatchSourceClient {

    private static final Logger LOG = LoggerFactory.getLogger( HelloWorldDispatchSourceClient.class );

    public String callWebService( String aName ) {
        QName serviceQName = new QName( HelloWorldBindings.WS_TARGET_NS, HelloWorldBindings.WS_NAME );
        QName portQName = new QName( HelloWorldBindings.WS_TARGET_NS, HelloWorldBindings.WS_PORT );

        try {
            Service service = Service.create( new URL( HelloWorldBindings.WS_URL ), serviceQName );
            // INTERESTING: using payload or message denotes whether message will include the soap envelope.
            Dispatch<Source> dispatch = service.createDispatch( portQName, Source.class, Service.Mode.PAYLOAD );

            Source request = new StreamSource( createSoapRequestReader( aName ) );
            Source response = dispatch.invoke( request );
            return debugResponse( response );
        }
        catch ( MalformedURLException mue ) {
            throw new IllegalArgumentException( "URL to webservice does not exist", mue );
        }
    }

    private StringReader createSoapRequestReader( String aName ) {
        StringBuilder builder = new StringBuilder();
        builder.append( "<ns1:sayHello xmlns:ns1=\"http://test.suggs.org.uk\">" );
        builder.append( "<name>" ).append( aName ).append( "</name>" );
        builder.append( "</ns1:sayHello>" );
        return new StringReader( builder.toString() );
    }

    private String debugResponse( Source aResponse ) {
        try {
            Transformer copier = TransformerFactory.newInstance().newTransformer();
            Writer stringWriter = new StringWriter();
            copier.transform( aResponse, new StreamResult( stringWriter ) );
            String xmlResponse = stringWriter.toString();
            return extractResponseFromXml( xmlResponse );
        }
        catch ( TransformerConfigurationException tce ) {
            throw new IllegalArgumentException( "Failed to build and configure the transformer", tce );
        }
        catch ( TransformerException te ) {
            throw new IllegalArgumentException( "Failed to transform", te );
        }

    }

    private String extractResponseFromXml( String xmlResponse ) {
        try {
            LOG.debug( "response = [" + xmlResponse + "]" );
            XMLInputFactory xmlFactory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = xmlFactory.createXMLEventReader( new StringReader( xmlResponse ) );
            try {
                while ( eventReader.hasNext() ) {
                    XMLEvent event = eventReader.nextEvent();
                    if ( event.isCharacters() ) {
                        Characters chars = event.asCharacters();
                        if ( !chars.isWhiteSpace() ) {
                            return chars.getData();
                        }
                    }
                }
            }
            finally {
                eventReader.close();
            }
        }
        catch ( XMLStreamException xse ) {
            throw new IllegalStateException( "failed to create xml stream reader", xse );
        }
        LOG.info( "Could not find any text to return, so returning null" );
        return null;
    }
}
