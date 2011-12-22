package org.suggs.webapps.buildpipeline.driver;

import org.suggs.webapps.buildpipeline.dsl.Component;
import org.suggs.webapps.buildpipeline.dsl.ComponentVersion;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to encapsulate a Component.
 * <p/>
 * User: suggitpe Date: 31/08/11 Time: 20:19
 */

public final class ComponentImpl implements Component {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ComponentImpl.class );
    private File componentInstallDirectory;
    private Application applicationContext;

    public ComponentImpl( Application aApplicationContext, File aComponentInstallDirectory ) {
        applicationContext = aApplicationContext;
        componentInstallDirectory = aComponentInstallDirectory;
    }

    @Override
    public ComponentVersion addVersion( String aComponentVersionNumber ) {
        return applicationContext.createInstalledComponentVersion( this, aComponentVersionNumber );
    }

    @Override
    public String getName() {
        return componentInstallDirectory.getName();
    }

    public File getComponentInstallDirectory() {
        return componentInstallDirectory;
    }

    @Override
    public String toString() {
        return getName();
    }


}
