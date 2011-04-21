package org.suggs.sandbox_webapps.springmvcpersistenttest.jbehave;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract class for all pages within this mvc test module.
 * <p/>
 * User: suggitpe
 * Date: 12/04/11
 * Time: 07:18
 */

public abstract class AbstractPage extends WebDriverPage{

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( AbstractPage.class );

    public AbstractPage( WebDriverProvider aProvider ){
        super(aProvider);
    }


}
