package org.suggs.sandbox_webapps.jbehave.pages;

import org.jbehave.web.selenium.WebDriverProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple class that act as a singleton to the page objects in the system.
 * <p/>
 * User: suggitpe
 * Date: 12/05/11
 * Time: 07:26
 */

public class Pages {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( Pages.class );

    private final WebDriverProvider driverProvider;
    private HomePage homePage;
    private FunkyPage funkyPage;

    public Pages( WebDriverProvider aDriverProvider ) {
        driverProvider = aDriverProvider;
    }

    public HomePage home() {
        if ( homePage == null ) {
            homePage = new HomePage( driverProvider );
        }
        return homePage;
    }

    public FunkyPage funkyPage() {
        if ( funkyPage == null ) {
            funkyPage = new FunkyPage( driverProvider );
        }
        return funkyPage;
    }

    public void closeBrowser() {
    }
}
