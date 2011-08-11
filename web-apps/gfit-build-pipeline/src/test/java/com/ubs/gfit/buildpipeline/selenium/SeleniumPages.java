package com.ubs.gfit.buildpipeline.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.thoughtworks.selenium.Selenium;
import com.ubs.gfit.buildpipeline.pages.AbstractPages;

/**
 * Class to build out the selenium webdriver implementation.
 * <p/>
 * User: suggitpe
 * Date: 11/08/11
 * Time: 18:16
 */

public final class SeleniumPages extends AbstractPages {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( SeleniumPages.class );

    private final WebDriver webDriver = new HtmlUnitDriver( true );
    private final Selenium selenium = new WebDriverBackedSelenium(webDriver, "http://foo");


    @Override
    protected WebDriver getWebDriver() {
        return webDriver;
    }
}
