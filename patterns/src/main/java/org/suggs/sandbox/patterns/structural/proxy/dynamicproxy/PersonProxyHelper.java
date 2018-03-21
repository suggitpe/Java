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
 */
public final class PersonProxyHelper {

    private PersonProxyHelper() {
    }

    public static final IPerson getOwnerProxy(IPerson aPerson) {
        return (IPerson) Proxy.newProxyInstance(aPerson.getClass().getClassLoader(), aPerson.getClass()
                .getInterfaces(), new OwnerInvocationHandler(aPerson));
    }

    public static final IPerson getNonOwnerProxy(IPerson aPerson) {
        return (IPerson) Proxy.newProxyInstance(aPerson.getClass().getClassLoader(), aPerson.getClass()
                .getInterfaces(), new NonOwnerInvocationHandler(aPerson));
    }

}
