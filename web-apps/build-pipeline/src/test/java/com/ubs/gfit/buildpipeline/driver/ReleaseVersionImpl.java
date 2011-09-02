package com.ubs.gfit.buildpipeline.driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ubs.gfit.buildpipeline.dsl.Component;
import com.ubs.gfit.buildpipeline.dsl.ReleaseVersion;

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
