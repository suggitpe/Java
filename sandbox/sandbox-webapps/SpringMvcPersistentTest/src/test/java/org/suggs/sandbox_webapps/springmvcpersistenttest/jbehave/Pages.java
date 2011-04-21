package org.suggs.sandbox_webapps.springmvcpersistenttest.jbehave;

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
    private Home home;
    private PingTest pingTest;

    public Pages( WebDriverProvider aDriverProvider ) {
        driverProvider = aDriverProvider;
    }

    public Home home() {
        if ( home == null ) {
            home = new Home( driverProvider );
        }
        return home;
    }

    public PingTest pingTest() {
        if ( pingTest == null ) {
            pingTest = new PingTest( driverProvider );
        }
        return pingTest;
    }
}
