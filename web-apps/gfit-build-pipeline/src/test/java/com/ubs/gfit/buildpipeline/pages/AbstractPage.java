package com.ubs.gfit.buildpipeline.pages;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.internal.matchers.StringContains.containsString;

/**
 * Abstract page class that all pages will derive from.
 * <p/>
 * User: suggitpe
 * Date: 04/07/11
 * Time: 18:58
 */

abstract class AbstractPage extends WebDriverPage {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( AbstractPage.class );


    protected static final String BASE_URL = "http://localhost:9099/gfit-build-pipeline";

    AbstractPage( WebDriverProvider aWebDriver ) {
        super( aWebDriver );
    }

    public void found( String aText ) {
        found( getPageSource(), aText );
    }

    public void found( String aPageSource, String aText ) {
        assertThat( aPageSource, containsString( escapeHtml( aText ) ) );
    }

    private String escapeHtml( String aText ) {
        return aText.replace( "<", "&lt;" ).replace( ">", "&gt;" );
    }

    public void returnToHome() {
        findElement( By.id( "home" ) ).click();
    }

    public void returnToHomePage() {
        findElement( By.id( "homeLink" ) ).click();
    }

    public void isShown() {
        String pageTitle = findElement( By.id( "title" ) ).getText();
        LOG.info( "Checking that the title [" + pageTitle + "] is equal to the expected [" + expectedPageTitle() + "]" );
        assertThat( pageTitle, equalTo( expectedPageTitle() ) );
    }

    protected abstract String expectedPageTitle();
}
