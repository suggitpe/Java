package org.suggs.webapps.buildpipeline.concordion;

import org.suggs.webapps.buildpipeline.dsl.DSL;
import org.suggs.webapps.buildpipeline.pages.Pages;
import org.suggs.webapps.buildpipeline.pages.impl.SeleniumPages;

import org.agileinsider.concordion.junit.ConcordionPlus;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe Date: 12/12/11 Time: 7:30 AM
 */

@RunWith(ConcordionPlus.class)
public class UserCanAccessApplicationScenario extends DSL {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( UserCanAccessApplicationScenario.class );

    public UserCanAccessApplicationScenario() {
        super( new SeleniumPages() );
    }
}
