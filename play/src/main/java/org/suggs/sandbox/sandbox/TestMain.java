/*
 * TestMain.java created on 4 Jun 2009 20:04:03 by suggitpe for project Sandbox
 * 
 */
package org.suggs.sandbox.sandbox;

import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class should be used only for doing on the fly tests where necessary.
 */
public final class TestMain {

    private static final Logger LOG = LoggerFactory.getLogger( TestMain.class );
    private static final String FILE_LOC = "/home/suggitpe/deleteme.txt";

    private TestMain() {}

    public static void main( String[] args ) {
        testTokeniser();

        try {
            testFiles();
        }
        catch ( Exception e ) {
            LOG.error( "Boohoo your test failed", e );
        }
    }

    private static void testFiles() throws IOException {
        LOG.debug( "Testing files" );

        File f = new File( FILE_LOC );
        LOG.debug( f.getAbsoluteFile().toString() );
        LOG.debug( f.getCanonicalPath() );
        LOG.debug( f.getName() );
        LOG.debug( f.toURI().toString() );

    }

    private static void testTokeniser() {
        LOG.debug( "Testing tokeniser" );
        String path = System.getProperty( "java.library.path" );

        StringTokenizer t = new StringTokenizer( path, ":" );
        LOG.debug( Integer.valueOf( t.countTokens() ).toString() );
        while ( t.hasMoreTokens() ) {
            LOG.debug( t.nextToken() );
        }
    }

}
