package com.ubs.gfit.buildpipeline.pages;

import org.jbehave.web.selenium.WebDriverProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to encapsulate the Release Version Form.
 * <p/>
 * User: suggitpe
 * Date: 07/07/11
 * Time: 19:34
 */

public final class ReleaseVersionForm extends AbstractPage {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ReleaseVersionForm.class );

    private static final String RELEASE_VERSION_FORM_TITLE = "Release Version";

    public ReleaseVersionForm( WebDriverProvider aWebDriverProvider ) {
        super( aWebDriverProvider );
    }

    public void isShown() {
        foundTitle( RELEASE_VERSION_FORM_TITLE );
    }

    public void addDescription( String aDescription ) {
    }
}
