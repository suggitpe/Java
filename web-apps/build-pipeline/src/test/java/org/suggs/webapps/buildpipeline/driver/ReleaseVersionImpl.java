package org.suggs.webapps.buildpipeline.driver;

import org.suggs.webapps.buildpipeline.dsl.Component;
import org.suggs.webapps.buildpipeline.dsl.ReleaseVersion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe
 * Date: 30/08/11
 * Time: 14:26
 */

public final class ReleaseVersionImpl implements ReleaseVersion {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ReleaseVersionImpl.class );

    private Application application;
    private String versionNumber;

    public ReleaseVersionImpl( Application aDriver ) {
        application = aDriver;
    }

    @Override
    public String getVersionNumber() {
        return versionNumber;
    }

    @Override
    public String getVersionOfComponent( Component aComponent ) {
        throw new UnsupportedOperationException();
    }

    public void setVersionNumber( String aVersionNumber ) {
        versionNumber = aVersionNumber;
    }

    @Override
    public String getDescription() {
        return application.getDescriptionFor( versionNumber );
    }

}
