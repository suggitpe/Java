package org.suggs.sandbox.oddsandsods.logger;

import java.util.IllegalFormatException;

import org.apache.log4j.Logger;

/**
 * Implementation of Log class targeted for Log4J as the underlying mechanism.
 */

class Log4JLog implements Log {

    private final Logger logger;

    /**
     * Creates a {@link Log4JLog log}, using the class name.
     * 
     * @param targetClass
     *            the class to create a <code>log</code> for.
     */
    public Log4JLog( Class<?> targetClass ) {
        logger = Logger.getLogger( targetClass );
    }

    /**
     * Creates a Log4JLog, using a {@link String string} argument.
     * 
     * @param targetString
     *            the name to create a {@link Log4JLog log} for.
     */
    public Log4JLog( String targetString ) {
        logger = Logger.getLogger( targetString );
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.suggs.sandbox.oddsandsods.logger.Log#isTraceEnabled()
     */
    @Override
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.suggs.sandbox.oddsandsods.logger.Log#isDebugEnabled()
     */
    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.suggs.sandbox.oddsandsods.logger.Log#isInfoEnabled()
     */
    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.suggs.sandbox.oddsandsods.logger.Log#fatal(java.lang.String)
     */
    @Override
    public void fatal( String message ) {
        logger.fatal( message );
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.suggs.sandbox.oddsandsods.logger.Log#fatal(java.lang.String, java.lang.Object[])
     */
    @Override
    public void fatal( String message, Object... args ) {
        logger.fatal( format( message, args ), getThrowableArgumentFromArgs( args ) );
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.suggs.sandbox.oddsandsods.logger.Log#fatal(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void fatal( String message, Throwable throwable ) {
        logger.fatal( message, throwable );
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.suggs.sandbox.oddsandsods.logger.Log#error(java.lang.String)
     */
    @Override
    public void error( String message ) {
        logger.error( message );
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.suggs.sandbox.oddsandsods.logger.Log#error(java.lang.String, java.lang.Object[])
     */
    @Override
    public void error( String message, Object... args ) {
        logger.error( format( message, args ), getThrowableArgumentFromArgs( args ) );
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.suggs.sandbox.oddsandsods.logger.Log#error(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void error( String message, Throwable throwable ) {
        logger.error( message, throwable );
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.suggs.sandbox.oddsandsods.logger.Log#warn(java.lang.String)
     */
    @Override
    public void warn( String message ) {
        logger.warn( message );
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.suggs.sandbox.oddsandsods.logger.Log#warn(java.lang.String, java.lang.Object[])
     */
    @Override
    public void warn( String message, Object... args ) {
        logger.warn( format( message, args ), getThrowableArgumentFromArgs( args ) );
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.suggs.sandbox.oddsandsods.logger.Log#warn(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void warn( String message, Throwable throwable ) {
        logger.warn( message, throwable );
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.suggs.sandbox.oddsandsods.logger.Log#info(java.lang.String)
     */
    @Override
    public void info( String message ) {
        logger.info( message );
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.suggs.sandbox.oddsandsods.logger.Log#info(java.lang.String, java.lang.Object[])
     */
    @Override
    public void info( String message, Object... args ) {
        if ( logger.isInfoEnabled() ) {
            logger.info( format( message, args ), getThrowableArgumentFromArgs( args ) );
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.suggs.sandbox.oddsandsods.logger.Log#info(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void info( String message, Throwable throwable ) {
        logger.info( message, throwable );
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.suggs.sandbox.oddsandsods.logger.Log#debug(java.lang.String)
     */
    @Override
    public void debug( String message ) {
        logger.debug( message );
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.suggs.sandbox.oddsandsods.logger.Log#debug(java.lang.String, java.lang.Object[])
     */
    @Override
    public void debug( String message, Object... args ) {
        if ( logger.isDebugEnabled() ) {
            logger.debug( format( message, args ), getThrowableArgumentFromArgs( args ) );
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.suggs.sandbox.oddsandsods.logger.Log#debug(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void debug( String message, Throwable throwable ) {
        logger.debug( message, throwable );
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.suggs.sandbox.oddsandsods.logger.Log#trace(java.lang.String)
     */
    @Override
    public void trace( String message ) {
        logger.trace( message );
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.suggs.sandbox.oddsandsods.logger.Log#trace(java.lang.String, java.lang.Object[])
     */
    @Override
    public void trace( String message, Object... args ) {
        if ( logger.isTraceEnabled() ) {
            logger.trace( format( message, args ), getThrowableArgumentFromArgs( args ) );
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.suggs.sandbox.oddsandsods.logger.Log#trace(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void trace( String message, Throwable throwable ) {
        logger.trace( message, throwable );
    }

    /**
     * * A wrapped version of String.format(String, Object...) that makes sure some message gets out even if
     * the formatting is bad. If the format is bad, the message will be displayed without the argument
     * substitution, followed by a warning about the formatting failure and also the exception message about
     * the failure.
     * 
     * @param message
     *            the message to be formatted
     * @param args
     *            the arguments to be substituted into the message
     * @return the message, formatted if possible
     * @see java.lang.String#format(java.lang.String, java.lang.Object[])
     */
    private static String format( String message, Object... args ) {
        String localMessage = message;
        try {
            localMessage = String.format( localMessage, args );
        }
        catch ( IllegalFormatException ife ) {
            localMessage = message + " (LOG ERROR: BAD FORMAT [" + ife.toString() + "])";
        }
        return localMessage;
    }

    /**
     * Checks if the last object in the array is of type
     * 
     * @link{Throwable . If so, the <code>Throwable</code> is returned. If not, a null is returned.
     * @param an
     *            array of objects
     * @return the last argument if it's a <code>Throwable</code> instance, <code>null</code> otherwise
     */
    private Throwable getThrowableArgumentFromArgs( Object... args ) {
        Throwable throwable = null;
        if ( args != null && args.length > 0 ) {
            Object lastArgument = args[args.length - 1];
            // gets the last object from the array
            if ( lastArgument instanceof Throwable ) {
                throwable = (Throwable) lastArgument;
            }
        }
        return throwable;
    }
}
