/*
 * Log.java created on 18 Sep 2009 19:19:46 by suggitpe for project SandBox - OddsAndSods
 * 
 */
package org.suggs.sandbox.oddsandsods.logger;

/**
 * Wrapper for Logger class, allows unguarded log statements in source
 * code.
 */
public interface Log
{

    /**
     * @return whether <code>INFO</code> level logging is enabled
     */
    boolean isInfoEnabled();

    /**
     * @return whether <code>DEBUG</code> level logging is enabled
     */
    boolean isDebugEnabled();

    /**
     * @return whether <code>TRACE</code> level logging is enabled
     */
    boolean isTraceEnabled();

    /**
     * Log a basic message.
     * 
     * @param message
     *            to log
     */
    void fatal( String message );

    /**
     * Log a message, with optional varargs parameter substitution.
     * 
     * @param message
     *            to log
     * @param args
     *            to format and include
     */
    void fatal( String message, Object... args );

    /**
     * Log a message, with a {@link Throwable} included.
     * 
     * @param message
     *            to log
     * @param throwable
     *            to include
     */
    void fatal( String message, Throwable throwable );

    /**
     * Log a basic message.
     * 
     * @param message
     *            to log
     */
    void error( String message );

    /**
     * Log a message, with optional varargs parameter substitution.
     * 
     * @param message
     *            to log
     * @param args
     *            to format and include
     */
    void error( String message, Object... args );

    /**
     * Log a message, with a {@link Throwable} included.
     * 
     * @param message
     *            to log
     * @param throwable
     *            to include
     */
    void error( String message, Throwable throwable );

    /**
     * Log a basic message.
     * 
     * @param message
     *            to log
     */
    void warn( String message );

    /**
     * Log a message, with optional varargs parameter substitution.
     * 
     * @param message
     *            to log
     * @param args
     *            to format and include
     */
    void warn( String message, Object... args );

    /**
     * Log a message, with a {@link Throwable} included.
     * 
     * @param message
     *            to log
     * @param throwable
     *            to include
     */
    void warn( String message, Throwable throwable );

    /**
     * Log a basic message.
     * 
     * @param message
     *            to log
     */
    void info( String message );

    /**
     * Log a message, with optional varargs parameter substitution.
     * 
     * @param message
     *            to log
     * @param args
     *            to format and include
     */
    void info( String message, Object... args );

    /**
     * Log a message, with a {@link Throwable} included.
     * 
     * @param message
     *            to log
     * @param throwable
     *            to include
     */
    void info( String message, Throwable throwable );

    /**
     * Log a basic message.
     * 
     * @param message
     *            to log
     */
    void debug( String message );

    /**
     * Log a message, with optional varargs parameter substitution.
     * 
     * @param message
     *            to log
     * @param args
     *            to format and include
     */
    void debug( String message, Object... args );

    /**
     * Log a message, with a {@link Throwable} included.
     * 
     * @param message
     *            to log
     * @param throwable
     *            to include
     */
    void debug( String message, Throwable throwable );

    /**
     * Log a basic message.
     * 
     * @param message
     *            to log
     */
    void trace( String message );

    /**
     * Log a message, with optional varargs parameter substitution.
     * 
     * @param message
     *            to log
     * @param args
     *            to format and include
     */
    void trace( String message, Object... args );

    /**
     * Log a message, with a {@link Throwable} included.
     * 
     * @param message
     *            to log
     * @param throwable
     *            to include
     */
    void trace( String message, Throwable throwable );
}
