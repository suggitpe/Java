package com.ubs.gfit.buildpipeline.dsl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ubs.gfit.buildpipeline.driver.Application;
import com.ubs.gfit.buildpipeline.pages.pageobjects.ReleaseVersionForm;

/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe
 * Date: 30/08/11
 * Time: 14:26
 */

public final class ReleaseVersion {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ReleaseVersion.class );

    private Application application;
    private String versionNumber;
    private String description = "DefaultDescription";

    public ReleaseVersion( Application aApplication ) {
        application = aApplication;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber( String aVersionNumber ) {
        versionNumber = aVersionNumber;
    }

    public String getDescription() {
        return application.getDescriptionFor( this );
    }

    public ReleaseVersion withComponentVersions( ComponentVersion aComponentVersion1 ) {
        throw new UnsupportedOperationException();
    }

    public ReleaseVersion withDescription( String aDescription ) {
        description = aDescription;
        return this;
    }

    public ReleaseVersion build() {
        return application.createReleaseFor( this );
    }

    public String getDescriptionForNewReleaseVersion() {
        return description;
    }

    public void populate( ReleaseVersionForm aReleaseVersionForm ) {
        aReleaseVersionForm.setDescription( description);
    }
}
