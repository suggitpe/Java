package com.ubs.gfit.buildpipeline.pages;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.Matchers.equalTo;
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

    AbstractPage( WebDriverProvider aWebDriverProvider ) {
        super( aWebDriverProvider );
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

    public void foundTitle( String aSoughtTitle ) {
        foundTitle( getTitle(), aSoughtTitle );
    }

    public void foundTitle( String aActualTitle, String aSoughtTitle ) {
        LOG.info( "Checking that actual title[" + aActualTitle + "] matches with correct title[" + aSoughtTitle + "]" );
        assertThat( aSoughtTitle, equalTo( aActualTitle ) );
    }

    public void returnToHomePage() {
        findElement( By.id( "homeLink" ) ).click();
    }
}
