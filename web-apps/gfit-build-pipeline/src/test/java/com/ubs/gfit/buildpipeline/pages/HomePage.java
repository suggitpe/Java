package com.ubs.gfit.buildpipeline.pages;

import org.jbehave.web.selenium.WebDriverProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to represent the Home Page of the webapp
 * <p/>
 * User: suggitpe
 * Date: 04/07/11
 * Time: 18:56
 */

public final class HomePage extends AbstractPage {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( HomePage.class );

    public HomePage( WebDriverProvider aWebDriverProvider ) {
        super( aWebDriverProvider );
    }

}
