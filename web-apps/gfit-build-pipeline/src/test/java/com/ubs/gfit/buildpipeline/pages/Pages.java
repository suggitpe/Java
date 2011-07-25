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

public final class Pages {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( Pages.class );

    private final WebDriverProvider webDriver;
    private HomePage homePage;
    private ReleaseManagementPage releaseManagementPage;
    private ReleaseVersionForm releaseVersionForm;
    private ReleaseVersionShow releaseVersionShow;

    public Pages( WebDriverProvider aWebDriver ) {
        webDriver = aWebDriver;
    }

    public HomePage homePage() {
        if ( homePage == null ) {
            homePage = new HomePage( webDriver );
        }
        return homePage;
    }

    public ReleaseManagementPage releaseManagementPage() {
        if ( releaseManagementPage == null ) {
            releaseManagementPage = new ReleaseManagementPage( webDriver );
        }
        return releaseManagementPage;
    }

    public ReleaseVersionForm releaseVersionForm() {
        if ( releaseVersionForm == null ) {
            releaseVersionForm = new ReleaseVersionForm( webDriver );
        }
        return releaseVersionForm;
    }

    public ReleaseVersionShow releaseVersionShow() {
        if ( releaseVersionShow == null ) {
            releaseVersionShow = new ReleaseVersionShow( webDriver );
        }
        return releaseVersionShow;
    }

    public void closeBrowser() {
        webDriver.get().close();
    }

}
