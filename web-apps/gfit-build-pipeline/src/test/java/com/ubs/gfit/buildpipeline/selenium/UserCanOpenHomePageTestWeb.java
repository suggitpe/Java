package com.ubs.gfit.buildpipeline.selenium;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

public final class UserCanOpenHomePageTestWeb extends DSL {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( UserCanOpenHomePageTestWeb.class );

    public UserCanOpenHomePageTestWeb(){
        super( new SeleniumPages());
    }

    @Test
    public void shouldBeAbleToAccessApplication() {
        openApplication();
        assertApplicationIsShown();
    }
}
