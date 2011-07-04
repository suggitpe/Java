package com.ubs.gfit.buildpipeline.pages;

import org.jbehave.web.selenium.WebDriverProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Builder class for all page objects.
 * <p/>
 * User: suggitpe
 * Date: 04/07/11
 * Time: 18:50
 */

public class Pages {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( Pages.class );

    private final WebDriverProvider webDriverProvider;
    private HomePage homePage;
    private ReleaseManagementPage releaseManagementPage;
    private VersionDisplayPage versionDisplayPage;

    public Pages( WebDriverProvider aWebDriverProvider ) {
        webDriverProvider = aWebDriverProvider;
    }

    public HomePage homePage() {
        if ( homePage == null ) {
            homePage = new HomePage( webDriverProvider );
        }
        return homePage;
    }

    public ReleaseManagementPage releaseManagementPage() {
        if ( releaseManagementPage == null ) {
            releaseManagementPage = new ReleaseManagementPage( webDriverProvider );
        }
        return releaseManagementPage;
    }

    public VersionDisplayPage versionDisplayPage() {
        if ( versionDisplayPage == null ) {
            versionDisplayPage = new VersionDisplayPage( webDriverProvider );
        }
        return versionDisplayPage;
    }

}
