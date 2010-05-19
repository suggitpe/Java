/*
 * PersonProxyHelper.java created on 18 Sep 2007 06:45:09 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.proxy.dynamicproxy;

import org.suggs.sandbox.patterns.structural.proxy.dynamicproxy.invocationhandlers.NonOwnerInvocationHandler;
import org.suggs.sandbox.patterns.structural.proxy.dynamicproxy.invocationhandlers.OwnerInvocationHandler;

import java.lang.reflect.Proxy;

/**
 * Helper class for the construction of proxy objects
 * 
 * @author suggitpe
 * @version 1.0 18 Sep 2007
 */
public class PersonProxyHelper {

    private PersonProxyHelper() {}

    /**
     * Creates a dynamic proxy for the IPerson interface using the Owner Invocation Hanler
     * 
     * @param aPerson
     *            the person object to create a proxy for
     * @return a new IPerson proxy
     */
    public static final IPerson getOwnerProxy( IPerson aPerson ) {
        return (IPerson) Proxy.newProxyInstance( aPerson.getClass().getClassLoader(), aPerson.getClass()
            .getInterfaces(), new OwnerInvocationHandler( aPerson ) );
    }

    /**
     * Creates a dynamic proxy for the IPerson interface using a NonOwner Invocation handler
     * 
     * @param aPerson
     *            the object to create a proxy for
     * @return the proxy object
     */
    public static final IPerson getNonOwnerProxy( IPerson aPerson ) {
        return (IPerson) Proxy.newProxyInstance( aPerson.getClass().getClassLoader(), aPerson.getClass()
            .getInterfaces(), new NonOwnerInvocationHandler( aPerson ) );
    }

}
