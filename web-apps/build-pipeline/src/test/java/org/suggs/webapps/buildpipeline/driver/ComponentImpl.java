package org.suggs.webapps.buildpipeline.driver;

import org.suggs.webapps.buildpipeline.dsl.Component;
import org.suggs.webapps.buildpipeline.dsl.ComponentVersion;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe
 * Date: 31/08/11
 * Time: 20:19
 */

public final class ComponentImpl implements Component {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ComponentImpl.class );
    File componentInstallDirectory;

    public ComponentImpl(File aComponentInstallDirectory){
        componentInstallDirectory = aComponentInstallDirectory;
    }

    @Override
    public ComponentVersion addVersion( String aComponentVersionNumber ) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getName() {
        return componentInstallDirectory.getName();
    }
}
