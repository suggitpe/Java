package org.suggs.sandbox_webapps.selenium_test.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * This is a simple selenium test that allows us to demonstrate the use of selenium as a test tool against a web
 * application.
 * <p/>
 * User: suggitpe
 * Date: 05/05/11
 * Time: 18:33
 */

public class BasicNavigationTestWeb {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( BasicNavigationTestWeb.class );

    //private final WebDriver webDriver = new FirefoxDriver();
    private final WebDriver webDriver = new HtmlUnitDriver();

    private static final String BASE_URL = "http://localhost:9099/selenium-webapp-test";
    private static final String BASE_URL_TITLE = "Selenium Test WebApp";
    private static final String FUNKY_URL_TITLE = "Some Funky Page";

    @Before
    public void onSetup() {
        LOG.debug( "Getting base url" );
        webDriver.get( BASE_URL );
        assertThat( webDriver.getTitle(), equalTo( BASE_URL_TITLE ) );
        LOG.info( "Base URL retrieved, all is well" );
    }

    @After
    public void onTeardown() {
        LOG.debug( "Closing webdriver" );
        webDriver.close();
    }

    @Test
    public void navigateToFunkyPageAndThenBackToHome() {
        LOG.info( "Navigating to funky page" );
        webDriver.findElement( By.id( "funkyLink" ) ).click();
        LOG.info( "Verifying that we have the right page" );
        assertThat( webDriver.getTitle(), equalTo( FUNKY_URL_TITLE ) );

        LOG.info( "Navigating back to home" );
        webDriver.findElement( By.id( "homeLink" ) ).click();
        assertThat( webDriver.getTitle(), equalTo( BASE_URL_TITLE ) );
    }
}
