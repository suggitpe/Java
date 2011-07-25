package com.ubs.gfit.buildpipeline.pages;

import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

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
    private static final String NEW_RELEASE_ID = "newVersionLink";

    public ReleaseManagementPage( WebDriverProvider aWebDriver ) {
        super( aWebDriver );
    }

    public void open() {
        getWebDriver().get( BASE_URL + "/release-management" );
    }

    protected String expectedPageTitle() {
        return RELEASE_MANAGEMENT_PAGE_TITLE;
    }

    public void requestNewRelease() {
        getWebDriver().findElement( By.id( NEW_RELEASE_ID ) ).click();
    }

    private WebElement getLinkForReleaseVersionDescription( String aDescription ) {
        return getWebDriver().findElement( By.xpath( "//table[@id='releasesTable']//tr[@id='" + aDescription + "']//td[@class='rvVersion']//a" ) );
    }

    public WebElement findLinkForReleaseVersionDesription( String aDescription ) {
        WebElement elem = getLinkForReleaseVersionDescription( aDescription );
        assertThat( elem, is( notNullValue() ) );
        return elem;
    }

    public String findVersionWithDescription( String aDescription ) {
        WebElement element = getWebDriver().findElement( By.xpath( "//table[@id='releasesTable']//tr[@id='" + aDescription + "']//td[@class='rvVersion']" ) );
        LOG.info( "Found version [" + element.getText() + "] from description [" + aDescription + "]" );
        assertThat( element, is( notNullValue() ) );
        return element.getText();
    }

    public void assertNoReleaseWithDescriptionOf( String aDescription ) {
        try {
            LOG.debug( "Checking that no releases exist with description of [" + aDescription + "]" );
            getLinkForReleaseVersionDescription( aDescription );
        }
        catch ( NoSuchElementException e ) {
            LOG.debug( "Correctly found that element does not exist for description [" + aDescription + "]" );
            return;
        }
        fail( "Expected exception to be thrown in searching for element with description of [" + aDescription + "]" );
    }
}
