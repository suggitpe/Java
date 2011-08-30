package com.ubs.gfit.buildpipeline.driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ubs.gfit.buildpipeline.dsl.ComponentVersion;
import com.ubs.gfit.buildpipeline.dsl.ReleaseVersion;
import com.ubs.gfit.buildpipeline.pages.SeleniumPages;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe
 * Date: 30/08/11
 * Time: 14:55
 */

public final class Application {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( Application.class );

    private SeleniumPages pages = new SeleniumPages();

    public void openApplication() {
        pages.homePage().open();
    }

    public boolean checkApplicationIsOpen() {
        return pages.homePage().isShown();
    }

    public String getDescriptionFor( String aVersionNumber ) {
        pages.releaseVersionShow().openWithVersion( aVersionNumber );
        return pages.releaseVersionShow().getDescription();
    }

    public ReleaseVersionImpl createReleaseVersion( String aDescription ) {
        pages.releaseVersionForm().openForNew();
        pages.releaseVersionForm().setDescription( aDescription );
        pages.releaseVersionForm().completeNew();
        String versionNumber = pages.releaseVersionShow().getVersion();

        ReleaseVersionImpl releaseVersion = new ReleaseVersionImpl( this );
        releaseVersion.setVersionNumber( versionNumber );
        return releaseVersion;
    }

    public ReleaseVersionImpl createReleaseVersion() {
        return createReleaseVersion( "Foo" );
    }


    public ReleaseVersion createReleaseVersion( ComponentVersion aComponentVersion1 ) {
        pages.releaseVersionForm().openForNew();
        pages.releaseVersionForm().setDescription( "Foo");
        pages.releaseVersionForm().completeNew();
        String versionNumber = pages.releaseVersionShow().getVersion();

        ReleaseVersionImpl releaseVersion = new ReleaseVersionImpl( this );
        releaseVersion.setVersionNumber( versionNumber );
        return releaseVersion;
    }
}
