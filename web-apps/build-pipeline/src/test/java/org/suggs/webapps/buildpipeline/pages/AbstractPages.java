package org.suggs.webapps.buildpipeline.pages;

import org.suggs.webapps.buildpipeline.pages.pageobjects.HomePage;
import org.suggs.webapps.buildpipeline.pages.pageobjects.ReleaseManagementPage;
import org.suggs.webapps.buildpipeline.pages.pageobjects.ReleaseVersionForm;
import org.suggs.webapps.buildpipeline.pages.pageobjects.ReleaseVersionShow;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.thoughtworks.selenium.Selenium;

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

    protected Selenium getSelenium(){
        return new WebDriverBackedSelenium( getWebDriver(), "http://foo" );
    }

    public HomePage homePage() {
        if ( homePage == null ) {
            homePage = new HomePage( getSelenium() );
        }
        return homePage;
    }

    public ReleaseManagementPage releaseManagementPage() {
        if ( releaseManagementPage == null ) {
            releaseManagementPage = new ReleaseManagementPage( getSelenium() );
        }
        return releaseManagementPage;
    }

    public ReleaseVersionForm releaseVersionForm() {
        if ( releaseVersionForm == null ) {
            releaseVersionForm = new ReleaseVersionForm( getSelenium() );
        }
        return releaseVersionForm;
    }


    public ReleaseVersionShow releaseVersionShow() {
        if ( releaseVersionShow == null ) {
            releaseVersionShow = new ReleaseVersionShow( getSelenium() );
        }
        return releaseVersionShow;
    }

    public void closeBrowser() {
        getWebDriver().close();
    }
}
