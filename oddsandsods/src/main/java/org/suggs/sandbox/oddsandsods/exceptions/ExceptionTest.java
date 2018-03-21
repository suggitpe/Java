/*
 * ExceptionTests.java created on 7 Oct 2009 18:22:12 by suggitpe for project SandBox - OddsAndSods
 * 
 */
package org.suggs.sandbox.oddsandsods.exceptions;

import org.suggs.sandbox.oddsandsods.logger.Log;
import org.suggs.sandbox.oddsandsods.logger.LogFactory;

/**
 * TODO Write javadoc for ExceptionTests
 * 
 * @author suggitpe
 * @version 1.0 7 Oct 2009
 */
public final class ExceptionTest {

    private static final Log LOG = LogFactory.getLog( ExceptionTest.class );

    private ExceptionTest() {}

    /**
     * @param args
     */
    public static void main( String[] args ) {
        try {
            testme();
        }
        catch ( Exception e ) {
            // whatever!!!
            LOG.error( "This is from main", e );
        }
    }

    /**
     * @throws Exception
     */
    public static void testme() throws Exception {
        try {
            LOG.debug( "In try" );
            throw new Exception( "This is the main exception in the try" );
        }
        catch ( Exception e ) {
            LOG.debug( "In catch" );
            throw new Exception( "this is in the catch" );
        }
        finally {
            LOG.debug( "In finally)" );
            if ( true ) {
                throw new Exception( "this is in finally" );
            }
        }
    }
}
