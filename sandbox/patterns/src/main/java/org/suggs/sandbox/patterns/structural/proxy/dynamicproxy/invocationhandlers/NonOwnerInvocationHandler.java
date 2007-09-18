/*
 * NonOwnerInvocationHandler.java created on 17 Sep 2007 18:08:56 by suggitpe for project SandBox - Patterns
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
 * This invocation handler disallows calls to everything other than
 * the 'set hot or not' method on the person object.
 * 
 * @author suggitpe
 * @version 1.0 17 Sep 2007
 */
public class NonOwnerInvocationHandler implements InvocationHandler
{

    private static final Log LOG = LogFactory.getLog( NonOwnerInvocationHandler.class );

    private IPerson mPerson_;

    /**
     * Constructs a new instance.
     */
    public NonOwnerInvocationHandler( IPerson aPerson )
    {
        mPerson_ = aPerson;
    }

    /**
     * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object,
     *      java.lang.reflect.Method, java.lang.Object[])
     */
    public Object invoke( Object aObject, Method aMethod, Object[] aArgs ) throws Throwable
    {
        try
        {
            if ( aMethod.getName().startsWith( "get" ) )
            {
                return aMethod.invoke( mPerson_, aArgs );
            }
            else if ( aMethod.getName().equalsIgnoreCase( "sethotornotrating" ) )
            {
                return aMethod.invoke( mPerson_, aArgs );
            }
            else if ( aMethod.getName().startsWith( "set" ) )
            {
                throw new IllegalAccessException();
            }
        }
        catch ( InvocationTargetException ite )
        {
            LOG.error( "Exception thrown from object [" + aObject.getClass().getSimpleName() + "] in method [" + aMethod.getName()
                       + "]: error=[" + ite.getMessage() + "]" );
        }
        return null;
    }
}
