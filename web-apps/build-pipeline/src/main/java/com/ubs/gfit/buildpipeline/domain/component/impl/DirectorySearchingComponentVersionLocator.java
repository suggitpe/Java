package com.ubs.gfit.buildpipeline.domain.component.impl;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ubs.gfit.buildpipeline.domain.component.ComponentBean;
import com.ubs.gfit.buildpipeline.domain.component.ComponentVersionService;
import com.ubs.gfit.buildpipeline.domain.component.ComponentVersionsBean;

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
    private Set<String> testComponents = new TreeSet<String>();

    private static final FileFilter DIRECTORY_FILTER = new FileFilter() {
        @Override
        public boolean accept( File aPathname ) {
            return aPathname.isDirectory();
        }
    };

    @Override
    public ComponentVersionsBean getComponentVersions() {
        File file = new File( componentInstallDirectory );

        if ( !file.exists() ) {
            throw new IllegalStateException( "Cannot find directory [" + componentInstallDirectory + "]" );
        }

        ComponentVersionsBean wrapper = new ComponentVersionsBean();

        for ( File compDir : file.listFiles( DIRECTORY_FILTER ) ) {
            for ( File verDir : compDir.listFiles( DIRECTORY_FILTER ) ) {
                wrapper.addVersion( new ComponentBean( compDir.getName(),
                        isTestSuite( compDir.getName() ) ), verDir.getName() );
            }
        }

        return wrapper;
    }

    private boolean isTestSuite( String aName ) {
        if ( testComponents.contains( aName ) ) {
            return true;
        }
        return false;
    }

    public String getComponentInstallDirectory() {
        return componentInstallDirectory;
    }

    public void setComponentInstallDirectory( String aComponentInstallDirectory ) {
        componentInstallDirectory = aComponentInstallDirectory;
    }

    public void setTestComponents( Set<String> aTestComponents ) {
        testComponents = aTestComponents;
    }

    public void addTestComponent( String aTestComponent ) {
        testComponents.add( aTestComponent );
    }
}
