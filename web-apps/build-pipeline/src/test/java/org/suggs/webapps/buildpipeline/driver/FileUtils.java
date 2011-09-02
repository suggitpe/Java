package org.suggs.webapps.buildpipeline.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe
 * Date: 01/09/11
 * Time: 19:18
 */

public final class FileUtils {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( FileUtils.class );

    private FileUtils() {
    }

    static ComponentImpl createFreshComponentDirectory( String aComponentName, File aComponentInstallDir ) {
        deleteComponentDirectoryIfExists( aComponentName, aComponentInstallDir );
        File newComponentDirectory = new File( aComponentInstallDir.getAbsolutePath() + File.separator + aComponentName );
        newComponentDirectory.mkdir();
        if ( !newComponentDirectory.exists() || !newComponentDirectory.isDirectory() ) {
            throw new IllegalStateException( "Failed to create component install directory [" + aComponentName + "] in [" + aComponentInstallDir.getAbsolutePath() + "]" );
        }
        return new ComponentImpl( newComponentDirectory );
    }

    static String readComponentInstallDirectory() throws IOException {
        URL url = ClassLoader.getSystemResource( "real.properties" );
        Properties properties = new Properties();
        properties.load( new FileInputStream( new File( url.getFile() ) ) );
        return properties.getProperty( "component.install.dir" );
    }

    private static void deleteComponentDirectoryIfExists( final String aDirectoryToDelete, File aComponentInstallDir ) {
        if ( countNumberOfFilesInDirectoryWithName( aDirectoryToDelete, aComponentInstallDir ) == 0 ) {
            return;
        }
        recursivelyDeleteFilesInDirectoryWithName( new File( aComponentInstallDir + File.separator + aDirectoryToDelete ) );
    }

    private static void recursivelyDeleteFilesInDirectoryWithName( File aFileLocationDirectory ) {
        for ( File file : aFileLocationDirectory.listFiles() ) {
            if ( file.isDirectory() ) {
                recursivelyDeleteFilesInDirectoryWithName( file );
            }
            else {
                deleteFile( file );
            }
        }
        deleteFile( aFileLocationDirectory );
    }

    private static void deleteFile( File aFile ) {
        LOG.info( "Deleting file [" + aFile.getAbsolutePath() + "]" );
        if ( !aFile.delete() ) {
            throw new IllegalStateException( "Failed to delete file [" + aFile.getAbsolutePath() + "]" );
        }
    }

    private static int countNumberOfFilesInDirectoryWithName( final String aNameOfFile, File aDirectoryToSearchThrough ) {
        return aDirectoryToSearchThrough.listFiles( new FilenameFilter() {
            @Override
            public boolean accept( File dir, String name ) {
                return name.equals( aNameOfFile );
            }
        } ).length;
    }
}
