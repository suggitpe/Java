/*
 * SchemaCreatorTest.java created on 13 Oct 2010 19:54:13 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.support;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * TODO Write javadoc for SchemaCreatorTest
 * 
 * @author suggitpe
 * @version 1.0 13 Oct 2010
 */
public class SchemaCreatorTest {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( SchemaCreatorTest.class );

    @Test
    public void findEntitiesAndCreateDdl() throws Exception {
        String localPackageDir = getLocalPackageDirectory();
        Enumeration<URL> urls = getClass().getClassLoader().getResources( localPackageDir );
        while ( urls.hasMoreElements() ) {
            URL url = urls.nextElement();
            LOG.debug( url.toString() );
        }

        LOG.debug( "Local packge is [" + localPackageDir + "]" );
    }

    private String getLocalPackageDirectory() {
        String localPackage = getLocalPackageName();
        return localPackage.replace( '.', '/' );
    }

    private String getLocalPackageName() {
        String retValue = getClass().getName();

        int lastIndexOfDot = retValue.lastIndexOf( '.' );
        retValue = retValue.substring( 0, lastIndexOfDot );

        return retValue;
    }

    /**
     * Scans all classes accessible from the context class loader which belong to the given package and
     * subpackages.
     * 
     * @param packageName
     *            The base package
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     */
    private static Class[] getClasses( String packageName ) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace( '.', '/' );
        Enumeration<URL> resources = classLoader.getResources( path );
        List<File> dirs = new ArrayList<File>();
        while ( resources.hasMoreElements() ) {
            URL resource = resources.nextElement();
            dirs.add( new File( resource.getFile() ) );
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for ( File directory : dirs ) {
            classes.addAll( findClasses( directory, packageName ) );
        }
        return classes.toArray( new Class[classes.size()] );
    }

    /**
     * Recursive method used to find all classes in a given directory and subdirs.
     * 
     * @param directory
     *            The base directory
     * @param packageName
     *            The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    private static List<Class> findClasses( File directory, String packageName )
                    throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if ( !directory.exists() ) {
            return classes;
        }
        File[] files = directory.listFiles();
        for ( File file : files ) {
            if ( file.isDirectory() ) {
                assert !file.getName().contains( "." );
                classes.addAll( findClasses( file, packageName + "." + file.getName() ) );
            }
            else if ( file.getName().endsWith( ".class" ) ) {
                classes.add( Class.forName( packageName + '.'
                                            + file.getName().substring( 0, file.getName().length() - 6 ) ) );
            }
        }
        return classes;
    }

}
