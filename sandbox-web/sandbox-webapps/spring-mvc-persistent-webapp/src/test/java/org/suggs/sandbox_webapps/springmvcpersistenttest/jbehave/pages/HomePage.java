package org.suggs.sandbox_webapps.springmvcpersistenttest.jbehave.pages;

import java.util.concurrent.TimeUnit;

import org.jbehave.web.selenium.WebDriverProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Page Object impl for the home page of the system.
 * <p/>
 * User: suggitpe
 * Date: 07/04/11
 * Time: 07:53
 */

public final class HomePage extends AbstractPage {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( HomePage.class );


    public HomePage( WebDriverProvider aProvider ) {
        super( aProvider );
    }

    public void open() {
        get( BASE_URL );
        manage().timeouts().implicitlyWait( 10, TimeUnit.SECONDS );
    }

    public void pageIsShown() {
        found( "Welcome" );
    }
}
