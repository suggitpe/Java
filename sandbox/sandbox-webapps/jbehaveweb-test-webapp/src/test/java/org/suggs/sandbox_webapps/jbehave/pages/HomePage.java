package org.suggs.sandbox_webapps.jbehave.pages;

import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Page object for the home page.
 * <p/>
 * User: suggitpe
 * Date: 12/05/11
 * Time: 11:21
 */

public class HomePage extends AbstractPage {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( HomePage.class );
    private static final String BASE_URL = "http://localhost:9099/jbehaveweb-webapp-test";
    private static final String PAGE_TITLE = "JBehaveWeb Test WebApp";

    public HomePage( WebDriverProvider aDriverProvider ) {
        super( aDriverProvider );
    }

    public void open() {
        LOG.info( "opening up the Home page" );
        get( BASE_URL );
    }

    public void pageIsShown() {
        foundTitle( PAGE_TITLE );
    }

    public void openFunkyPage() {
        LOG.info( "Navigating to funky page" );
        findElement( By.id( "funkyLink" ) ).click();
    }
}
