package com.ubs.gfit.buildpipeline.pages;

import org.jbehave.web.selenium.WebDriverProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to represent the version display page.
 * <p/>
 * User: suggitpe
 * Date: 04/07/11
 * Time: 18:57
 */

public final class VersionDisplayPage extends AbstractPage {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( VersionDisplayPage.class );

    public VersionDisplayPage( WebDriverProvider aWebDriverProvider ) {
        super( aWebDriverProvider );
    }
}
