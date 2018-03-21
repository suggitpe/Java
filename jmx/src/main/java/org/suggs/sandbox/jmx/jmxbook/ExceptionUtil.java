/*
 * ExceptionUtil.java created on 13 Feb 2008 19:17:47 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.jmxbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.MBeanException;

/**
 * Class to manage the printing of an exception stack
 */
public final class ExceptionUtil {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionUtil.class);

    private ExceptionUtil() {
    }

    public static final void printException(Exception e) {
        LOG.debug("============ [Exception] ==============");
        LOG.error(e.toString());
        if (e instanceof MBeanException) {
            boolean hasEmbeddedException = true;
            Exception embeddedExc = e;
            while (hasEmbeddedException) {
                embeddedExc = ((MBeanException) embeddedExc).getTargetException();
                LOG.debug("-------- [Embedded Exception] ---------");

                LOG.error(embeddedExc.toString());

                if (!(embeddedExc instanceof MBeanException)) {
                    hasEmbeddedException = false;
                }
            }
        }
        LOG.debug("==========================");
    }
}
