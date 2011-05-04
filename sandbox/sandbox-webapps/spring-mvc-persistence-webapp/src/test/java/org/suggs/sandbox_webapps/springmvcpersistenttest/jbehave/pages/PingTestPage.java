package org.suggs.sandbox_webapps.springmvcpersistenttest.jbehave.pages;

import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Page Object impl for the ping test page of the site.
 * <p/>
 * User: suggitpe
 * Date: 12/04/11
 * Time: 07:22
 */

public final class PingTestPage extends AbstractPage {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( PingTestPage.class );

    public PingTestPage( WebDriverProvider aProvider ) {
        super( aProvider );
    }

    public void open() {
        findElement( By.linkText( "Ping Test" ) ).click();
    }

    public void pageIsShown() {
        found( "PONG!" );
    }
}


