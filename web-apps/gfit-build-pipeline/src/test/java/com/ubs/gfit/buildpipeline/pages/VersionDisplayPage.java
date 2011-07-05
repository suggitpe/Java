package com.ubs.gfit.buildpipeline.pages;

import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
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

    private static final String VERSION_DISPLAY_PAGE_TITLE = "Release Management";
    private static final String RETURN_TO_RM_PAGE_LINK = "ReturnToReleasemanagement";
    private static final String VERSION_NUMBER_ID = "VersionNumberId";

    public VersionDisplayPage( WebDriverProvider aWebDriverProvider ) {
        super( aWebDriverProvider );
    }

    public void pageIsShown() {
        foundTitle( VERSION_DISPLAY_PAGE_TITLE );
    }

    public void returnToReleaseManagementPage() {
        findElement( By.id( RETURN_TO_RM_PAGE_LINK ) ).click();
    }

    public int uniqueIdentifierIsDisplayed() {
        String versionNumber = findElement( By.id( VERSION_NUMBER_ID)).getValue();
        try{
            return Integer.parseInt( versionNumber);
        }catch ( NumberFormatException nfe ){
            throw new IllegalStateException( "failed to convert release id from String ["+versionNumber+"]", nfe);
        }
    }
}
