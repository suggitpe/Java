package com.ubs.gfit.buildpipeline.pages;

import org.jbehave.web.selenium.WebDriverProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private WebDriverProvider webDriverProvider;

    AbstractPage( WebDriverProvider aWebDriverProvider ) {
        webDriverProvider = aWebDriverProvider;
    }
}
