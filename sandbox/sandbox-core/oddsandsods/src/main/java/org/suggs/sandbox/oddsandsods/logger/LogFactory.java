/*
 * LogFactory.java created on 18 Sep 2009 19:22:13 by suggitpe for project SandBox - OddsAndSods
 * 
 */
package org.suggs.sandbox.oddsandsods.logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Log Factory for retrieving {@link Log} objects.
 */
public final class LogFactory {

    private static Map<Class<?>, Log> logCacheForClassNames = new ConcurrentHashMap<Class<?>, Log>();
    private static Map<String, Log> logCacheForStringNames = new ConcurrentHashMap<String, Log>();

    /**
     * Factory class, no instantiation, all visible methods are static.
     */
    private LogFactory() {
        super();
    }

    /**
     * Get a {@link Log log} instance from the internal cache or instantiate a new one.
     * 
     * @param targetClass
     *            class the logging is for.
     * @return {@link Log log} for the supplied class.
     */
    public static Log getLog( Class<?> targetClass ) {
        Log log = logCacheForClassNames.get( targetClass );
        if ( log == null ) {
            log = new Log4JLog( targetClass );
            logCacheForClassNames.put( targetClass, log );
        }
        return log;
    }

    /**
     * Get a {@link Log log} instance from the internal cache of {@link String names} or instantiate a new
     * one.
     * 
     * @param target
     *            name of the class/log target you are logging for.
     * @return <code>Log</code> for your supplied target.
     * @see #getLog(Class)
     */
    public static Log getLog( String target ) {
        Log log = logCacheForStringNames.get( target );
        if ( log == null ) {
            log = new Log4JLog( target );
            logCacheForStringNames.put( target, log );
        }
        return log;
    }
}
