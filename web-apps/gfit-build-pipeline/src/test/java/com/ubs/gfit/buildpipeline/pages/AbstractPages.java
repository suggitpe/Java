package com.ubs.gfit.buildpipeline.pages;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ubs.gfit.buildpipeline.pages.pageobjects.HomePage;
import com.ubs.gfit.buildpipeline.pages.pageobjects.ReleaseManagementPage;
import com.ubs.gfit.buildpipeline.pages.pageobjects.ReleaseVersionForm;
import com.ubs.gfit.buildpipeline.pages.pageobjects.ReleaseVersionShow;

/**
 * Abstract class that contains all of the abstract page implementation.
 * <p/>
 * User: suggitpe
 * Date: 11/08/11
 * Time: 18:18
 */

public abstract class AbstractPages {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( AbstractPages.class );

    private HomePage homePage;
    protected ReleaseManagementPage releaseManagementPage;
    protected ReleaseVersionForm releaseVersionForm;
    protected ReleaseVersionShow releaseVersionShow;


    protected abstract WebDriver getWebDriver();

    public HomePage homePage() {
        if ( homePage == null ) {
            homePage = new HomePage( getWebDriver() );
        }
        return homePage;
    }

    public ReleaseManagementPage releaseManagementPage() {
        if ( releaseManagementPage == null ) {
            releaseManagementPage = new ReleaseManagementPage( getWebDriver() );
        }
        return releaseManagementPage;
    }

    public ReleaseVersionForm releaseVersionForm() {
        if ( releaseVersionForm == null ) {
            releaseVersionForm = new ReleaseVersionForm( getWebDriver() );
        }
        return releaseVersionForm;
    }


    public ReleaseVersionShow releaseVersionShow() {
        if ( releaseVersionShow == null ) {
            releaseVersionShow = new ReleaseVersionShow( getWebDriver() );
        }
        return releaseVersionShow;
    }

    public void closeBrowser() {
        getWebDriver().close();
    }
}
