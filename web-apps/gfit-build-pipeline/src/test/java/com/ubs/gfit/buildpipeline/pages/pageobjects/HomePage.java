package com.ubs.gfit.buildpipeline.pages.pageobjects;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to represent the Home Page of the webapp
 * <p/>
 * User: suggitpe
 * Date: 04/07/11
 * Time: 18:56
 */

public final class HomePage extends AbstractPage {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( HomePage.class );

    public static final String HOME_PAGE_TITLE = "Home";

    public HomePage( WebDriver aWebDriver ) {
        super( aWebDriver );
    }

    protected String expectedPageTitle() {
        return HOME_PAGE_TITLE;
    }

    public void open() {
        getWebDriver().get( BASE_URL );
    }


}
