package org.suggs.sandbox_webapps.springmvcpersistenttest.jbehave.pages;

import org.jbehave.web.selenium.WebDriverProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Accessor class to the Page Objects within the application.
 * <p/>
 * User: suggitpe
 * Date: 07/04/11
 * Time: 07:50
 */

public final class Pages {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( Pages.class );

    private final WebDriverProvider driverProvider;
    private HomePage homePage;
    private PingTestPage pingTestPage;

    public Pages( WebDriverProvider aDriverProvider ) {
        driverProvider = aDriverProvider;
    }

    public HomePage home() {
        if ( homePage == null ) {
            homePage = new HomePage( driverProvider );
        }
        return homePage;
    }

    public PingTestPage pingTest() {
        if ( pingTestPage == null ) {
            pingTestPage = new PingTestPage( driverProvider );
        }
        return pingTestPage;
    }
}
