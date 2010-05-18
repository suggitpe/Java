/*
 * TestMain.java created on 4 Jun 2009 20:04:03 by suggitpe for project Sandbox
 * 
 */
package org.suggs.sandbox.sandbox;

import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class should be used only for doing on the fly tests where necessary.
 * 
 * @author suggitpe
 * @version 1.0 4 Jun 2009
 */
public class TestMain {

    private static final Log LOG = LogFactory.getLog( TestMain.class );
    private static final String FILE_LOC = "/home/suggitpe/deleteme.txt";

    /**
     * Main method called through the underling main from init
     * 
     * @param args
     *            the args from the command line
     */
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
        LOG.debug( f.getAbsoluteFile() );
        LOG.debug( f.getCanonicalPath() );
        LOG.debug( f.getName() );
        LOG.debug( f.toURI().toString() );

    }

    private static void testTokeniser() {
        LOG.debug( "Testing tokeniser" );
        // System.out.println( System.getProperty(
        // "subversion.native.library" ) );
        String path = System.getProperty( "java.library.path" );

        StringTokenizer t = new StringTokenizer( path, ":" );
        System.out.println( t.countTokens() );
        while ( t.hasMoreTokens() ) {
            System.out.println( t.nextToken() );
        }
    }

}
