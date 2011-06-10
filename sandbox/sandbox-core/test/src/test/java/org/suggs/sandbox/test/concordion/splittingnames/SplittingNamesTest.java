package org.suggs.sandbox.test.concordion.splittingnames;

import org.concordion.integration.junit3.ConcordionTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: Justify why you have written this class
 * <p/>
 * User: suggitpe
 * Date: 09/06/11
 * Time: 19:13
 */

public class SplittingNamesTest extends ConcordionTestCase {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( SplittingNamesTest.class );


    public Result split(String aFullName)
    {
        Result result =  new Result();
        String[] words = aFullName.split( " ");
        result.firstName = words[0];
        result.lastName = words[1];
        return result;
    }

    class Result{
        public String firstName;
        public String lastName;
    }

}
