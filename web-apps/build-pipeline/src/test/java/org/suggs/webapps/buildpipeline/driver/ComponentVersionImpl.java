package org.suggs.webapps.buildpipeline.driver;

import org.suggs.webapps.buildpipeline.dsl.Component;
import org.suggs.webapps.buildpipeline.dsl.ComponentVersion;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to encapsulate a Component version.
 * <p/>
 * User: suggitpe Date: 02/09/11 Time: 18:12
 */

public final class ComponentVersionImpl implements ComponentVersion {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ComponentVersionImpl.class );

    private Component component;
    private String versionNumber;
    private File versionInstallDirectory;

    public ComponentVersionImpl( Component aComponent, String aVersionNumber, File aVersionInstallDirectory ) {
        component = aComponent;
        versionNumber = aVersionNumber;
        versionInstallDirectory = aVersionInstallDirectory;
    }

    @Override
    public String getVersionNumber() {
        return versionNumber;
    }

    @Override
    public Component getComponent() {
        return component;
    }
}
