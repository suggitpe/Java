package org.suggs.sandbox.test.concordion.greetings;

import org.concordion.integration.junit3.ConcordionTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe
 * Date: 09/06/11
 * Time: 07:15
 */

public class GreetingsTest extends ConcordionTestCase {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( GreetingsTest.class );

    public String greetingFor(String aName){
        return "Hello " + aName+"!";
    }
}
