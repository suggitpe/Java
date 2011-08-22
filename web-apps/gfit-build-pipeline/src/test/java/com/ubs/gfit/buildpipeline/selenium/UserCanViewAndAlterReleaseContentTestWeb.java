package com.ubs.gfit.buildpipeline.selenium;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ubs.gfit.buildpipeline.dsl.DSL;
import com.ubs.gfit.buildpipeline.pages.SeleniumPages;

/**
 * Test suite for testing the viewing and altering of release content.
 * <p/>
 * User: suggitpe
 * Date: 22/08/11
 * Time: 07:12
 */

public final class UserCanViewAndAlterReleaseContentTestWeb extends DSL {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( UserCanViewAndAlterReleaseContentTestWeb.class );

    public UserCanViewAndAlterReleaseContentTestWeb() {
        super( new SeleniumPages() );
    }
}
