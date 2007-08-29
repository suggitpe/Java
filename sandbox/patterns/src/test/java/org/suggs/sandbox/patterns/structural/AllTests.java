/*
 * AllTests.java created on 23 Aug 2007 06:03:40 by suggitpe for project Sandbox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Test suite for all tests for the mercury connection model
 * 
 * @author suggitpe
 * @version 1.0 2 Aug 2007
 */
public class AllTests
{

    /**
     * This is the underlying test suite itself
     * 
     * @return the test suite to run
     */
    public static Test suite()
    {
        TestSuite s = new TestSuite();

        s.addTestSuite( DecoratorTestCase.class );

        return s;
    }

}
