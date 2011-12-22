package org.suggs.webapps.buildpipeline.pageobjects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.thoughtworks.selenium.Selenium;

import static org.junit.Assert.assertThat;
import static org.junit.internal.matchers.StringContains.containsString;

/**
 * Abstract page class that all pages will derive from.
 * <p/>
 * User: suggitpe Date: 04/07/11 Time: 18:58
 */

abstract class AbstractPage {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( AbstractPage.class );


    protected static final String BASE_URL = "http://localhost:9099/build-pipeline";
    private Selenium selenium;

    AbstractPage( Selenium aSelenium ) {
        selenium = aSelenium;
    }

    public void found( String aText ) {
        found( getSelenium().getHtmlSource(), aText );
    }

    public void found( String aPageSource, String aText ) {
        assertThat( aPageSource, containsString( escapeHtml( aText ) ) );
    }

    private String escapeHtml( String aText ) {
        return aText.replace( "<", "&lt;" ).replace( ">", "&gt;" );
    }

    public void returnToHome() {
        getSelenium().click( "id=home" );
    }

    public void returnToHomePage() {
        getSelenium().click( "id=homeLink" );
    }

    public boolean isShown() {
        String pageTitle = getSelenium().getText( "id=title" );
        LOG.info( "Checking that the title [" + pageTitle + "] is equal to the expected [" + expectedPageTitle() + "]" );
        return pageTitle.equals( expectedPageTitle() );
    }

    protected Selenium getSelenium() {
        return selenium;
    }

    protected abstract String expectedPageTitle();
}
