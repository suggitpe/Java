package org.suggs.webapps.buildpipeline.pageobjects;

import org.suggs.webapps.buildpipeline.dsl.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.thoughtworks.selenium.Selenium;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Class to encapsulate the Release ReleaseVersionImpl display page.
 * <p/>
 * User: suggitpe Date: 15/07/11 Time: 08:44
 */
public final class ReleaseVersionShow extends AbstractPage {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ReleaseVersionShow.class );


    public ReleaseVersionShow( Selenium aSelenium ) {
        super( aSelenium );
    }

    @Override
    protected String expectedPageTitle() {
        return "Release ReleaseVersionImpl Summary";
    }

    public void ensureDescribes( String aDescription ) {
        LOG.debug( "Checking that the description of the release is [" + aDescription + "]" );
        String description = getSelenium().getText( "id=description" );
        assertThat( description, equalTo( aDescription ) );
    }

    public void editRelease() {
        LOG.debug( "Editing the release" );
        getSelenium().click( "editReleaseVersionLink" );
    }

    public String getVersion() {
        String version = getSelenium().getText( "releaseVersion" );
        LOG.debug( "Extracted version as [" + version + "]" );
        assertThat( version, is( notNullValue() ) );
        return version;
    }


    public void openWithVersion( String aVersionNumber ) {
        LOG.info( "Opening form for version [" + aVersionNumber + "]" );
        getSelenium().open( BASE_URL + "/release-management/" + aVersionNumber );
    }

    public String getDescription() {
        return getSelenium().getText( "id=description" );
    }

    public String getComponentVersion( Component aComponent ) {
        return getSelenium().getText( "id=" + aComponent + "Version" );
    }
}
