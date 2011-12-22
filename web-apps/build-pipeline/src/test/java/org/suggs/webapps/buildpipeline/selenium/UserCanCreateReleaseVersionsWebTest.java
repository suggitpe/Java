package org.suggs.webapps.buildpipeline.selenium;

import org.suggs.webapps.buildpipeline.dsl.Component;
import org.suggs.webapps.buildpipeline.dsl.ComponentVersion;
import org.suggs.webapps.buildpipeline.dsl.DSL;
import org.suggs.webapps.buildpipeline.dsl.ReleaseVersion;
import org.suggs.webapps.buildpipeline.pages.impl.SeleniumPages;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

/**
 * Test suite for creating release versions.
 * <p/>
 * User: suggitpe Date: 22/08/11 Time: 07:11
 */

public final class UserCanCreateReleaseVersionsWebTest extends DSL {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( UserCanCreateReleaseVersionsWebTest.class );

    public UserCanCreateReleaseVersionsWebTest() {
        super( new SeleniumPages() );
    }

    @Test
    public void shouldBeAbleToAddDescriptionToReleaseSoThatPeopleCanEasilyReadIt() {
        String basicRelease = "BasicRelease";
        ReleaseVersion releaseVersion = createReleaseWithDescription( basicRelease );
        assertThat( releaseVersion.getDescription(), equalTo( basicRelease ) );
    }

    @Test
    public void shouldAssignUniqueReleaseVersionToRelease() {
        ReleaseVersion version1 = createRelease();
        ReleaseVersion version2 = createRelease();
        assertThat( version1.getVersionNumber(), not( equalTo( version2.getVersionNumber() ) ) );
    }

    @Test
    public void shouldBeAbleToSelectVersionOfComponents() {
        Component component = createComponent( "SomeFunkyComponent" );
        ComponentVersion componentVersion1 = component.addVersion( "1.2" );
        component.addVersion( "1.3" );
        ReleaseVersion releaseVersion = createReleaseWithComponentVersion( componentVersion1 );
        assertThat( releaseVersion.getVersionOfComponent( component ), equalTo( componentVersion1.getVersionNumber() ) );
    }


}
