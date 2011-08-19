package com.ubs.gfit.buildpipeline.pages;

import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Builder class for all page objects.
 * <p/>
 * User: suggitpe
 * Date: 04/07/11
 * Time: 18:50
 */

public final class JbehavePages extends AbstractPages {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( JbehavePages.class );

    private final WebDriverProvider webDriver;

    public JbehavePages( WebDriverProvider aWebDriver ) {
        webDriver = aWebDriver;
    }

    @Override
    protected WebDriver getWebDriver() {
        return webDriver.get();
    }

}
