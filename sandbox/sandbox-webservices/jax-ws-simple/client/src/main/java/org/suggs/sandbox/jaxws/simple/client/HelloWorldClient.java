/*
 * HelloWorldClient.java created on 10 Jun 2010 19:10:22 by suggitpe for project sandbox-webservices-jax-ws-simple-client
 * 
 */
package org.suggs.sandbox.jaxws.simple.client;

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

    public void callWebService() {
        LOG.debug( "Calling webservice" );

    }
}
