package com.ubs.gfit.buildpipeline.pages;

import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to represent the release management page.
 * <p/>
 * User: suggitpe
 * Date: 04/07/11
 * Time: 18:57
 */

public final class ReleaseManagementPage extends AbstractPage {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ReleaseManagementPage.class );

    private static final String RELEASE_MANAGEMENT_PAGE_TITLE = "Release Management";
    private static final String NEW_RELEASE_ID = "NewRelease";

    public ReleaseManagementPage( WebDriverProvider aWebDriverProvider ) {
        super( aWebDriverProvider );
    }

    public void open() {
        get( BASE_URL + "/release-management" );
    }

    public void pageIsShown() {
        foundTitle( RELEASE_MANAGEMENT_PAGE_TITLE );
    }

    public void requestNewRelease() {
        findElement( By.id( NEW_RELEASE_ID ) ).click();
    }
}
