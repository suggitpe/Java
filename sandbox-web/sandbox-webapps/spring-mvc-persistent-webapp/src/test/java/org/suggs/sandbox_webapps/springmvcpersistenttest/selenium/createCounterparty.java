package org.suggs.sandbox_webapps.springmvcpersistenttest.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe Date: 14/03/11 Time: 09:01
 */

public class createCounterparty {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( createCounterparty.class );

    private static final String BASE_URL = "http://localhost:9089/";


    private final WebDriver webDriver = new FirefoxDriver();

    @Before
    public void onSetup() {
        webDriver.get( BASE_URL );
    }

    @After
    public void onTeardown() {
        webDriver.close();
    }

    @Test
    public void createCounterpary() {
        assertThat( webDriver.getTitle(), is( equalTo( "Data Access" ) ) );
    }

    @Test
    public void clickingCounterpartiesGetsListOfCounterparties() {
        final WebElement pingLink = webDriver.findElement( By.name( "pingTest" ) );
        pingLink.click();
        assertThat( webDriver.getTitle(), is(equalTo( "Ping Test" )) );
    }
}
