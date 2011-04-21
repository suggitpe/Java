package org.suggs.sandbox_webapps.springmvcpersistenttest.jbehave;

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

public final class Home extends AbstractPage{

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( Home.class );


    public Home(WebDriverProvider aProvider){
        super(aProvider);
    }
}
