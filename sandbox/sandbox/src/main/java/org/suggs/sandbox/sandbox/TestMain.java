/*
 * TestMain.java created on 4 Jun 2009 20:04:03 by suggitpe for project Sandbox
 * 
 */
package org.suggs.sandbox.sandbox;

import java.util.StringTokenizer;

/**
 * This class should be used only for doing on the fly tests where
 * necessary.
 * 
 * @author suggitpe
 * @version 1.0 4 Jun 2009
 */
public class TestMain
{

    /**
     * Main method called through the underling main from init
     * 
     * @param args
     *            the args from the command line
     */
    public static void main( String[] args )
    {
        // System.out.println( System.getProperty(
        // "subversion.native.library" ) );
        String path = System.getProperty( "java.library.path" );

        StringTokenizer t = new StringTokenizer( path, ":" );
        System.out.println( t.countTokens() );
        while ( t.hasMoreTokens() )
        {
            System.out.println( t.nextToken() );
        }
    }

}
