/*
 * HelloWorldDispatchSourceClient.java created on 21 Jun 2010 19:17:35 by suggitpe for project sandbox-webservices-jax-ws-simple-client
 * 
 */
package org.suggs.sandbox.jaxws.simple.client.impl;

import org.suggs.sandbox.jaxws.simple.client.WebServiceClient;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;

/**
 * TODO Write javadoc for HelloWorldDispatchSourceClient
 * 
 * @author suggitpe
 * @version 1.0 21 Jun 2010
 */
public class HelloWorldDispatchSoapClient implements WebServiceClient {

    @Override
    public String callWebService( String aName ) {
        QName serviceQName = new QName( HelloWorldBindings.WS_TARGET_NS, HelloWorldBindings.WS_NAME );
        QName portQName = new QName( HelloWorldBindings.WS_TARGET_NS, HelloWorldBindings.WS_PORT );

        try {
            Service service = Service.create( new URL( HelloWorldBindings.WS_URL ), serviceQName );
            // INTERESTING: using message or message denotes whether message will include the soap envelope.
            Dispatch<SOAPMessage> dispatch = service.createDispatch( portQName,
                                                                     SOAPMessage.class,
                                                                     Service.Mode.MESSAGE );

            SOAPMessage soapRequest = createSoapRequest( aName );
            SOAPMessage soapResponse = dispatch.invoke( soapRequest );
            return debugSoapMessage( soapResponse );
        }
        catch ( MalformedURLException mue ) {
            throw new IllegalArgumentException( "URL to webservice does not exist", mue );
        }
    }

    private SOAPMessage createSoapRequest( String aName ) {
        try {
            MessageFactory soapMessageFactory = MessageFactory.newInstance();

            SOAPMessage soapRequest = soapMessageFactory.createMessage();
            SOAPEnvelope soapEnvelope = soapRequest.getSOAPPart().getEnvelope();
            SOAPBody soapBody = soapRequest.getSOAPBody();
            soapBody.addBodyElement( soapEnvelope.createName( "sayHello", "ns1", "http://test.suggs.org.uk" ) )
                .addChildElement( soapEnvelope.createName( "name" ) )
                .addTextNode( aName );
            return soapRequest;
        }
        catch ( SOAPException se ) {
            throw new IllegalStateException( "Failed to create soap request message", se );
        }

    }

    private String debugSoapMessage( SOAPMessage aSoapMessage ) {
        try {
            String response = aSoapMessage.getSOAPBody()
                .getElementsByTagName( "return" )
                .item( 0 )
                .getFirstChild()
                .getNodeValue();
            return response;
        }
        catch ( SOAPException se ) {
            throw new IllegalStateException( "Failed to process soap response message", se );
        }

    }

}
