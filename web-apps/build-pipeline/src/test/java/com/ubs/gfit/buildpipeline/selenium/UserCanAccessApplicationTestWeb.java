package com.ubs.gfit.buildpipeline.selenium;

import org.hamcrest.core.Is;
import org.junit.Test;
import com.ubs.gfit.buildpipeline.dsl.DSL;
import com.ubs.gfit.buildpipeline.pages.SeleniumPages;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Basic Test to prove that the application is up and running and can be accessed.  Treat this as a litmus test for the
 * application being deployed and accessible.
 * <p/>
 * User: suggitpe
 * Date: 11/08/11
 * Time: 18:35
 */

public final class UserCanAccessApplicationTestWeb extends DSL {

    @Test
    public void shouldBeAbleToAccessApplication() {
        openApplication();
        assertThat( applicationIsOpen(), is( true ) );
    }

}
