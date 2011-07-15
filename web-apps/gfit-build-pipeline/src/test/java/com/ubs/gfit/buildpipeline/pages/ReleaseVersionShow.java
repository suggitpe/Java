package com.ubs.gfit.buildpipeline.pages;

import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


/**
 * Class to encapsulate the Release Version display page.
 * <p/>
 * User: suggitpe
 * Date: 15/07/11
 * Time: 08:44
 */

public final class ReleaseVersionShow extends AbstractPage {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ReleaseVersionShow.class );

    public ReleaseVersionShow( WebDriverProvider aWebDriverProvider ) {
        super( aWebDriverProvider );
    }

    @Override
    protected String expectedPageTitle() {
        return "Release Version Summary";
    }

    public void ensureDescribes( String aDescription ) {
        String description = findElement( By.id( "description" ) ).getText();
        assertThat( description, equalTo( aDescription ) );
    }
}
