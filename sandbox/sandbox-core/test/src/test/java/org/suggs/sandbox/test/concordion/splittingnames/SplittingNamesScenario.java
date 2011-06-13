package org.suggs.sandbox.test.concordion.splittingnames;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to demonstrate use of objects in fixtures.
 * <p/>
 * User: suggitpe
 * Date: 09/06/11
 * Time: 19:13
 */

@RunWith(ConcordionRunner.class)
public class SplittingNamesScenario {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( SplittingNamesScenario.class );


    public Result split( String aFullName ) {
        Result result = new Result();
        String[] words = aFullName.split( " " );
        result.firstName = words[0];
        result.lastName = words[1];
        return result;
    }

    class Result {

        public String firstName;
        public String lastName;
    }

}
