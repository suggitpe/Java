/*
 * ExceptionUtil.java created on 13 Feb 2008 19:17:47 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.jmxbook;

import javax.management.MBeanException;

/**
 * Class to manage the printing of an exception stack
 * 
 * @author suggitpe
 * @version 1.0 13 Feb 2008
 */
public class ExceptionUtil
{

    /**
     * print exceptuion utility for exceptions caught
     * 
     * @param e
     *            the exception to print
     */
    public static final void printException( Exception e )
    {
        System.out.println( "============ [Exception] ==============" );
        e.printStackTrace();
        if ( e instanceof MBeanException )
        {
            boolean hasEmbeddedException = true;
            Exception embeddedExc = e;
            while ( hasEmbeddedException )
            {
                embeddedExc = ( (MBeanException) embeddedExc ).getTargetException();
                System.out.println( "-------- [Embedded Exception] ---------" );

                embeddedExc.printStackTrace();

                if ( !( embeddedExc instanceof MBeanException ) )
                {
                    hasEmbeddedException = false;
                }
            }
        }
        System.out.println( "==========================" );
    }
}
