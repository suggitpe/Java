/*
 * Logger.java created on 20 Sep 2007 18:18:06 by suggitpe for project SandBox - OddsAndSods
 * 
 */
package org.suggs.sandbox.oddsandsods.initialisationorder.specific;

import java.util.List;
import java.util.Vector;

import org.slf4j.LoggerFactory;

/**
 * Static class to catch the output of the tests in the correct order
 * 
 * @author suggitpe
 * @version 1.0 20 Sep 2007
 */
public final class Logger {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger( Logger.class );
    private static final List<String> LOGS = new Vector<String>( 10 );

    private Logger() {}

    /**
     * Adds a message to the internal vector
     * 
     * @param aMsg
     *            the message to add
     */
    public static synchronized void log( String aMsg ) {
        LOGS.add( aMsg );
    }

    /**
     * Outputs the contents of the internal vector to the console
     */
    public static synchronized void output() {
        for ( String s : LOGS ) {
            LOG.debug( s );
        }
    }

    /**
     * Empty all messages from the logs
     */
    public static synchronized void clearLogs() {
        LOGS.clear();
    }

}
