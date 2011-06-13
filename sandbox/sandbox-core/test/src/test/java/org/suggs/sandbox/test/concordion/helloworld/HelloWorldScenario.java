package org.suggs.sandbox.test.concordion.helloworld;

import org.concordion.integration.junit3.ConcordionTestCase;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple test class to demonstrate Concordion basic usage.
 * <p/>
 * User: suggitpe
 * Date: 09/06/11
 * Time: 07:01
 */

@RunWith(ConcordionRunner.class)
public class HelloWorldScenario extends ConcordionTestCase {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( HelloWorldScenario.class );

    public String getGreeting() {
        return "Hello World!";
    }
}
