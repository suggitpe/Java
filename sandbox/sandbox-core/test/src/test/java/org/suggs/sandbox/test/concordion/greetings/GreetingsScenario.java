package org.suggs.sandbox.test.concordion.greetings;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to demonstrate passing variables into fixtures.
 * <p/>
 * User: suggitpe
 * Date: 09/06/11
 * Time: 07:15
 */
@RunWith(ConcordionRunner.class)
public class GreetingsScenario {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( GreetingsScenario.class );

    public String greetingFor( String aName ) {
        return "Hello " + aName + "!";
    }
}
