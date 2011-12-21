package org.suggs.webapps.buildpipeline.concordion;

import org.suggs.webapps.buildpipeline.dsl.Component;
import org.suggs.webapps.buildpipeline.dsl.DSL;
import org.suggs.webapps.buildpipeline.dsl.ReleaseVersion;
import org.suggs.webapps.buildpipeline.pages.impl.SeleniumPages;

import org.agileinsider.concordion.junit.ConcordionPlus;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Concordion fixture for the create releaes tests.
 * <p/>
 * User: suggitpe Date: 12/12/11 Time: 7:14 PM
 */

@RunWith(ConcordionPlus.class)
public class UserCanCreateReleaseVersionsScenario extends DSL {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( UserCanCreateReleaseVersionsScenario.class );

    private ReleaseVersion releaseVersionPrimary;
    private ReleaseVersion releaseVersionComparator;

    public UserCanCreateReleaseVersionsScenario() {
        super( new SeleniumPages() );
    }

    public boolean assertReleaseDescriptionIs( ReleaseVersion aReleaseVersion, String aDescription ) {
        assertThat( aReleaseVersion.getDescription(), is( equalTo( aDescription ) ) );
        return true;
    }

    public void createTwoReleases() {
        releaseVersionPrimary = createRelease();
        releaseVersionComparator = createRelease();
    }

    public boolean releaseVersionsAreDifferent() {
        if ( releaseVersionPrimary.getVersionNumber() != releaseVersionComparator.getVersionNumber() ) {
            return true;
        }
        return false;
    }

    public void createComponentWithVersion( String aComponentName, String aComponentVersion ) {
        Component component = createComponent( aComponentName );
        component.addVersion( aComponentVersion );
    }


}
