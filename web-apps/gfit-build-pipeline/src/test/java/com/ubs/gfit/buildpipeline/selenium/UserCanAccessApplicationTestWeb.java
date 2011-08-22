package com.ubs.gfit.buildpipeline.selenium;

import org.junit.Test;
import com.ubs.gfit.buildpipeline.dsl.DSL;
import com.ubs.gfit.buildpipeline.pages.SeleniumPages;

/**
 * Basic Test to prove that the application is up and running and can be accessed.  Treat this as a litmus test for the
 * application being deployed and accessible.
 * <p/>
 * User: suggitpe
 * Date: 11/08/11
 * Time: 18:35
 */

public final class UserCanAccessApplicationTestWeb extends DSL {

    public UserCanAccessApplicationTestWeb() {
        super( new SeleniumPages() );
    }

    @Test
    public void shouldBeAbleToAccessApplication() {
        openApplication();
        checkApplicationIsShown();
    }
}
