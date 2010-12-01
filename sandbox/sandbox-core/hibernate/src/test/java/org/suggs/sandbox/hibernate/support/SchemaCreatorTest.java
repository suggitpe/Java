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

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * TODO Write javadoc for SchemaCreatorTest
 * 
 * @author suggitpe
 * @version 1.0 13 Oct 2010
 */
public class SchemaCreatorTest {

    private static final Log LOG = LogFactory.getLog( SchemaCreatorTest.class );
    private static final String LOCAL_PACKAGE = "org.suggs.sandbox.hibernate";

    @Test
    public void findEntitiesAndCreateDdl() throws Exception {
        List<File> directories = getListOfSearchDirectories();
        List<Class<?>> classes = new ArrayList<Class<?>>();
        for ( File dir : directories ) {
            classes.addAll( findClassesWithinDirectory( dir, LOCAL_PACKAGE ) );
        }

        String sql = createDdlFromEntityClasses( classes );
        LOG.debug( "Created DDL as:\n" + sql );
    }

    private List<File> getListOfSearchDirectories() throws IOException {
        String localPackageDir = getLocalPackageDirectory();
        Enumeration<URL> urls = getClass().getClassLoader().getResources( localPackageDir );
        List<File> directories = new ArrayList<File>();
        while ( urls.hasMoreElements() ) {
            URL url = urls.nextElement();
            directories.add( new File( url.getFile() ) );
        }
        return directories;
    }

    private String getLocalPackageDirectory() {
        return LOCAL_PACKAGE.replace( '.', '/' );
    }

    private List<Class<?>> findClassesWithinDirectory( File aDirectory, String aPackageName )
                    throws ClassNotFoundException {
        List<Class<?>> clazzList = new ArrayList<Class<?>>();
        if ( !aDirectory.exists() ) {
            return clazzList;
        }

        File[] files = aDirectory.listFiles();
        for ( File file : files ) {
            if ( file.isDirectory() ) {
                clazzList.addAll( findClassesWithinDirectory( file, aPackageName + "." + file.getName() ) );
            }
            else if ( file.getName().endsWith( ".class" ) ) {
                String fullClassName = aPackageName + "." + file.getName();
                clazzList.add( Class.forName( fullClassName.substring( 0, fullClassName.length() - 6 ) ) );
            }
        }

        return clazzList;
    }

    private String createDdlFromEntityClasses( List<Class<?>> aClasses ) {
        StringBuilder builder = new StringBuilder();
        LOG.debug( "Generating DDL" );
        builder.append( "-- ############################\n" );
        AnnotationConfiguration annotationCfg = new AnnotationConfiguration();
        annotationCfg.configure();
        for ( Class<?> clazz : aClasses ) {
            annotationCfg.addAnnotatedClass( clazz );
        }

        SchemaExport export = new SchemaExport( annotationCfg );
        export.setDelimiter( ";" );
        export.create( true, false );

        return builder.toString();
    }
}
