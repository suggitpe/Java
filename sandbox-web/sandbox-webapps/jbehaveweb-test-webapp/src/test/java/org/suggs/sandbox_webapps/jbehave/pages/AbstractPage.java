package org.suggs.sandbox_webapps.jbehave.pages;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.internal.matchers.StringContains.containsString;

/**
 * Abstract class for all pages within this mvc test module.
 * <p/>
 * User: suggitpe
 * Date: 12/04/11
 * Time: 07:18
 */

public abstract class AbstractPage extends WebDriverPage {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( AbstractPage.class );


    public AbstractPage( WebDriverProvider aProvider ) {
        super( aProvider );
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
