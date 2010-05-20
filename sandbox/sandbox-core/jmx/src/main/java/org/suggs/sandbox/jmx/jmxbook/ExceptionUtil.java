/*
 * ExceptionUtil.java created on 13 Feb 2008 19:17:47 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.jmxbook;

import javax.management.MBeanException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class to manage the printing of an exception stack
 * 
 * @author suggitpe
 * @version 1.0 13 Feb 2008
 */
public final class ExceptionUtil {

    private static final Log LOG = LogFactory.getLog( ExceptionUtil.class );

    private ExceptionUtil() {}

    /**
     * print exceptuion utility for exceptions caught
     * 
     * @param e
     *            the exception to print
     */
    public static final void printException( Exception e ) {
        LOG.debug( "============ [Exception] ==============" );
        LOG.error( e );
        if ( e instanceof MBeanException ) {
            boolean hasEmbeddedException = true;
            Exception embeddedExc = e;
            while ( hasEmbeddedException ) {
                embeddedExc = ( (MBeanException) embeddedExc ).getTargetException();
                LOG.debug( "-------- [Embedded Exception] ---------" );

                LOG.error( embeddedExc );

                if ( !( embeddedExc instanceof MBeanException ) ) {
                    hasEmbeddedException = false;
                }
            }
        }
        LOG.debug( "==========================" );
    }
}
