package com.ubs.gfit.buildpipeline.selenium;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe
 * Date: 11/08/11
 * Time: 18:35
 */

public final class UserCanOpenHomePageTestWeb {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( UserCanOpenHomePageTestWeb.class );

    @Test
    public void shouldOpenHomePage() {
        SeleniumPages pages = new SeleniumPages();
        pages.homePage().open();
        assertThat( pages.homePage().isShown(), is(true) );
        pages.homePage().isShown();
    }
}
