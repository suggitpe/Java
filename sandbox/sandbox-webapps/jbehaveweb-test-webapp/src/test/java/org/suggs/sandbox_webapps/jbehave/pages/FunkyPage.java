package org.suggs.sandbox_webapps.jbehave.pages;

import org.jbehave.web.selenium.WebDriverProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to encapsulate the core features of the Funky Page.
 * <p/>
 * User: suggitpe
 * Date: 12/05/11
 * Time: 11:21
 */

public class FunkyPage extends AbstractPage {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( FunkyPage.class );
    private static final String PAGE_TITLE = "Some Funky Page";

    public FunkyPage( WebDriverProvider aDriverProvider ) {
        super( aDriverProvider );
    }

    public void pageIsShown() {
        foundTitle( PAGE_TITLE );
    }

}
