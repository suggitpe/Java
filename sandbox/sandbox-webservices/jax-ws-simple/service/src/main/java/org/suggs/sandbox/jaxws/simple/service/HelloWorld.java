/*
 * HelloWorld.java created on 8 Jun 2010 07:03:15 by suggitpe for project sandbox-webservices-jax-ws-simple-service
 * 
 */
package org.suggs.sandbox.jaxws.simple.service;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Really simple webservice for illustrative purposes only.
 * 
 * @author suggitpe
 * @version 1.0 8 Jun 2010
 */
@WebService(targetNamespace = "http://test.suggs.org.uk")
public class HelloWorld {

    public String sayHello( @WebParam(name = "name") String aName ) {
        return "Hello " + aName + "!";
    }

}
