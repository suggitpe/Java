package org.suggs.webapps.buildpipeline.pageobjects;

import org.hamcrest.CoreMatchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.thoughtworks.selenium.Selenium;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Class to represent the release management page.
 * <p/>
 * User: suggitpe Date: 04/07/11 Time: 18:57
 */

public final class ReleaseManagementPage extends AbstractPage {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ReleaseManagementPage.class );

    private static final String RELEASE_MANAGEMENT_PAGE_TITLE = "Release Management";
    private static final String NEW_RELEASE_ID = "newVersionLink";

    public ReleaseManagementPage( Selenium aSelenium ) {
        super( aSelenium );
    }

    public void open() {
        getSelenium().open( BASE_URL + "/release-management" );
    }

    protected String expectedPageTitle() {
        return RELEASE_MANAGEMENT_PAGE_TITLE;
    }

    public void requestNewRelease() {
        getSelenium().click( "id=" + NEW_RELEASE_ID );
    }

    public void assertNoReleaseWithDescriptionOf( String aDescription ) {
        String shouldBeNull = getSelenium().getText( "xpath=//table[@id='releasesTable']//tr[@id='\" + aDescription + \"']//td[@class='rvVersion']\"" );
        assertThat( shouldBeNull, is( CoreMatchers.<Object>nullValue() ) );
    }
}
