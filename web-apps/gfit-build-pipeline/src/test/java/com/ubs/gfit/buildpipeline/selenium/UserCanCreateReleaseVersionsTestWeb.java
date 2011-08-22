package com.ubs.gfit.buildpipeline.selenium;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ubs.gfit.buildpipeline.dsl.DSL;
import com.ubs.gfit.buildpipeline.pages.SeleniumPages;

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
}
