/*
 * AllTests.java created on 2 Aug 2007 06:03:40 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury_old.model;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Test suite for all tests for the mercury model
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

        s.addTest( org.suggs.apps.mercury_old.model.connection.AllTests.suite() );

        return s;
    }

}
