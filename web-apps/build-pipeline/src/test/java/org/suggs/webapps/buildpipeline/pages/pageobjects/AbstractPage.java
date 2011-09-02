package org.suggs.webapps.buildpipeline.pages.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertThat;
import static org.junit.internal.matchers.StringContains.containsString;

/**
 * Abstract page class that all pages will derive from.
 * <p/>
 * User: suggitpe
 * Date: 04/07/11
 * Time: 18:58
 */

abstract class AbstractPage {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( AbstractPage.class );


    protected static final String BASE_URL = "http://localhost:9099/gfit-build-pipeline";
    private WebDriver webDriver;

    AbstractPage( WebDriver aWebDriver ) {
        webDriver = aWebDriver;
    }

    public void found( String aText ) {
        found( getWebDriver().getPageSource(), aText );
    }

    public void found( String aPageSource, String aText ) {
        assertThat( aPageSource, containsString( escapeHtml( aText ) ) );
    }

    private String escapeHtml( String aText ) {
        return aText.replace( "<", "&lt;" ).replace( ">", "&gt;" );
    }

    public void returnToHome() {
        getWebDriver().findElement( By.id( "home" ) ).click();
    }

    public void returnToHomePage() {
        getWebDriver().findElement( By.id( "homeLink" ) ).click();
    }

    public boolean isShown() {
        String pageTitle = getWebDriver().findElement( By.id( "title" ) ).getText();
        LOG.info( "Checking that the title [" + pageTitle + "] is equal to the expected [" + expectedPageTitle() + "]" );
        return pageTitle.equals( expectedPageTitle() );
    }

    protected WebDriver getWebDriver() {
        return webDriver;
    }

    protected abstract String expectedPageTitle();
}
