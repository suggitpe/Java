package com.ubs.gfit.buildpipeline.domain.component.impl;

import java.io.File;
import java.io.FileFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ubs.gfit.buildpipeline.domain.component.Component;
import com.ubs.gfit.buildpipeline.domain.component.ComponentVersionService;
import com.ubs.gfit.buildpipeline.domain.component.ComponentVersionWrapper;

/**
 * Simple class that will read from a file structure and build up a picture of all of the application versions that are
 * installed.
 * <p/>
 * User: suggitpe
 * Date: 12/07/11
 * Time: 07:27
 */

public class DirectorySearchingComponentVersionLocator implements ComponentVersionService {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( DirectorySearchingComponentVersionLocator.class );

    private String componentInstallDirectory;

    private static final FileFilter DIRECTORY_FILTER = new FileFilter() {
        @Override
        public boolean accept( File aPathname ) {
            return aPathname.isDirectory();
        }
    };

    @Override
    public ComponentVersionWrapper getComponentVersions() {
        File file = new File( componentInstallDirectory );

        if ( !file.exists() ) {
            throw new IllegalStateException( "Cannot find directory [" + componentInstallDirectory + "]" );
        }

        ComponentVersionWrapper wrapper = new ComponentVersionWrapper();

        for ( File compDir : file.listFiles( DIRECTORY_FILTER ) ) {
            if ( isDirectoryGglComponent( compDir ) ) {
                for ( File verDir : compDir.listFiles( DIRECTORY_FILTER ) ) {
                    wrapper.addVersion( Component.valueOf( compDir.getName() ), verDir.getName() );
                }
            }
        }

        return wrapper;
    }

    private boolean isDirectoryGglComponent( File aDirectory ) {
        try {
            Component.valueOf( aDirectory.getName() );
        }
        catch ( IllegalArgumentException iae ) {
            return false;
        }
        return true;
    }

    public String getComponentInstallDirectory() {
        return componentInstallDirectory;
    }

    public void setComponentInstallDirectory( String aComponentInstallDirectory ) {
        componentInstallDirectory = aComponentInstallDirectory;
    }
}
