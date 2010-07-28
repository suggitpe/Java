/*
 * OwnerInvocationHandler.java created on 17 Sep 2007 18:08:56 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.proxy.dynamicproxy.invocationhandlers;

import org.suggs.sandbox.patterns.structural.proxy.dynamicproxy.IPerson;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This invocation handler allows calls to everything other than the 'set hot or not' method on the person
 * object.
 * 
 * @author suggitpe
 * @version 1.0 17 Sep 2007
 */
public class OwnerInvocationHandler implements InvocationHandler {

    private static final Log LOG = LogFactory.getLog( OwnerInvocationHandler.class );
    private IPerson person;

    /**
     * Constructs a new instance.
     * 
     * @param aPerson
     *            the person to invoke
     */
    public OwnerInvocationHandler( IPerson aPerson ) {
        person = aPerson;
    }

    /**
     * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method,
     *      java.lang.Object[])
     */
    @Override
    public Object invoke( Object aObject, Method aMethod, Object[] aArgs ) throws Throwable {
        try {
            if ( aMethod.getName().startsWith( "get" ) ) {
                return aMethod.invoke( person, aArgs );
            }
            else if ( aMethod.getName().equalsIgnoreCase( "sethotornotrating" ) ) {
                throw new IllegalAccessException();
            }
            else if ( aMethod.getName().startsWith( "set" ) ) {
                return aMethod.invoke( person, aArgs );
            }
        }
        catch ( InvocationTargetException ite ) {
            LOG.error( "Exception thrown from object [" + aObject.getClass().getSimpleName()
                       + "] in method [" + aMethod.getName() + "]: error=[" + ite.getMessage() + "]" );
        }
        return null;

    }
}
