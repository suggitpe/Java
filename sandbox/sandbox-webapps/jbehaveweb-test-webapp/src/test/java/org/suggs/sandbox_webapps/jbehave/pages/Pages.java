package org.suggs.sandbox_webapps.jbehave.pages;

import org.jbehave.web.selenium.WebDriverProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe
 * Date: 12/05/11
 * Time: 07:26
 */

public class Pages {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( Pages.class );

    private final WebDriverProvider driverProvider;

    public Pages( WebDriverProvider aDriverProvider ) {
        driverProvider = aDriverProvider;
    }
}
