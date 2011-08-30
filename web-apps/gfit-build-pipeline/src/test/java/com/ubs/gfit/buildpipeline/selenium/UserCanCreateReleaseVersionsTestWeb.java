package com.ubs.gfit.buildpipeline.selenium;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ubs.gfit.buildpipeline.dsl.Component;
import com.ubs.gfit.buildpipeline.dsl.ComponentVersion;
import com.ubs.gfit.buildpipeline.dsl.DSL;
import com.ubs.gfit.buildpipeline.dsl.ReleaseVersion;
import com.ubs.gfit.buildpipeline.pages.SeleniumPages;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

/**
 * Test suite for creating release versions.
 * <p/>
 * User: suggitpe
 * Date: 22/08/11
 * Time: 07:11
 */

public final class UserCanCreateReleaseVersionsTestWeb extends DSL {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( UserCanCreateReleaseVersionsTestWeb.class );

    public UserCanCreateReleaseVersionsTestWeb() {
        super( new SeleniumPages() );
    }

    @Test
    public void shouldBeAbleToAddDescriptionToRelease() {
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
    @Ignore
    public void shouldBeAbleToSelectVersionOfComponents() {
        Component component = createComponent( "aComponent" );
        ComponentVersion componentVersion1 = component.addVersion( "1.2" );
        ComponentVersion componentVersion2 = component.addVersion( "1.3" );
        ReleaseVersion releaseVersion = createVersionBuilder().withComponentVersions( componentVersion1 );
    }



}
