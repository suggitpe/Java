package org.suggs.webapps.buildpipeline.driver;

import java.io.File;
import java.io.FilenameFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class manages the interaction with the filesystem only.
 * <p/>
 * User: suggitpe Date: 01/09/11 Time: 19:18
 */

public final class FileUtils {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( FileUtils.class );

    private FileUtils() {
    }

    static File createDirectoryReplacingIfNecessary( String aNewDirectory, File aParentDirectory ) {
        deleteComponentDirectoryIfExists( aNewDirectory, aParentDirectory );
        File newComponentDirectory = new File( aParentDirectory.getAbsolutePath() + File.separator + aNewDirectory );
        newComponentDirectory.mkdir();
        if ( !newComponentDirectory.exists() || !newComponentDirectory.isDirectory() ) {
            throw new IllegalStateException( "Failed to create component install directory [" + aNewDirectory + "] in [" + aParentDirectory.getAbsolutePath() + "]" );
        }
        return newComponentDirectory;
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
