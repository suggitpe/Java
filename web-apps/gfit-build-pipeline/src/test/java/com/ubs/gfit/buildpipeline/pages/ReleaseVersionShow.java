package com.ubs.gfit.buildpipeline.pages;

import org.jbehave.web.selenium.WebDriverProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe
 * Date: 15/07/11
 * Time: 08:44
 */

public class ReleaseVersionShow extends AbstractPage {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ReleaseVersionShow.class );

    public ReleaseVersionShow( WebDriverProvider aWebDriverProvider ) {
        super( aWebDriverProvider );
    }

    @Override
    protected String expectedPageTitle() {
        return "Release Version Summary";
    }
}
