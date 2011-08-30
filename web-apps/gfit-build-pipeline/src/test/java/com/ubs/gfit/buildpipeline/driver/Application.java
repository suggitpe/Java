package com.ubs.gfit.buildpipeline.driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ubs.gfit.buildpipeline.dsl.ReleaseVersion;
import com.ubs.gfit.buildpipeline.pages.SeleniumPages;

/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe
 * Date: 30/08/11
 * Time: 14:55
 */

public final class Application {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( Application.class );

    private SeleniumPages pages = new SeleniumPages();

    public String getDescriptionFor( ReleaseVersion aVersion ) {
        pages.releaseVersionShow().openWithVersion( aVersion.getVersionNumber() );
        return pages.releaseVersionShow().getDescription();
    }

    public ReleaseVersion createReleaseFor( ReleaseVersion aVersion ) {
        pages.releaseVersionForm().openForNew();
        pages.releaseVersionForm().setDescription( aVersion.getDescriptionForNewReleaseVersion() );
        pages.releaseVersionForm().completeNew();
        String versionNumber = pages.releaseVersionShow().getVersion();
        aVersion.setVersionNumber(versionNumber);
        return aVersion;
    }
}
