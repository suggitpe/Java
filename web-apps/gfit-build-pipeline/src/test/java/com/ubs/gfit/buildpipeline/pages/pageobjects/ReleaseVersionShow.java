package com.ubs.gfit.buildpipeline.pages.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


/**
 * Class to encapsulate the Release ReleaseVersion display page.
 * <p/>
 * User: suggitpe
 * Date: 15/07/11
 * Time: 08:44
 */

public final class ReleaseVersionShow extends AbstractPage {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ReleaseVersionShow.class );


    public ReleaseVersionShow( WebDriver aWebDriver ) {
        super( aWebDriver );
    }

    @Override
    protected String expectedPageTitle() {
        return "Release ReleaseVersion Summary";
    }

    public void ensureDescribes( String aDescription ) {
        LOG.debug( "Checking that the description of the release is [" + aDescription + "]" );
        String description = getWebDriver().findElement( By.id( "description" ) ).getText();
        assertThat( description, equalTo( aDescription ) );
    }

    public void editRelease() {
        LOG.debug( "Editing the release" );
        WebElement elem = getWebDriver().findElement( By.id( "editReleaseVersionLink" ) );
        assertThat( elem, is( notNullValue() ) );
        elem.click();
    }

    public String getVersion() {
        String version = getWebDriver().findElement( By.id( "releaseVersion" ) ).getText();
        LOG.debug( "Extracted version as [" + version + "]" );
        assertThat( version, is( notNullValue() ) );
        return version;
    }



    public void openWithVersion( String aVersionNumber ) {
        getWebDriver().navigate().to( BASE_URL + "/release-management/" + aVersionNumber );
    }

    public String getDescription() {
        return getWebDriver().findElement( By.id("description" )).getText();
    }
}
