/*
 * AllUnitTests.java created on 25 Sep 2008 18:20:37 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury;

import org.suggs.apps.mercury.model.AllIntegrationTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite to manage all of the unit tests from a central execution
 * 
 * @author suggitpe
 * @version 1.0 25 Sep 2008
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { org.suggs.apps.mercury.model.AllUnitTests.class, AllIntegrationTests.class })
public class AllTests
{}
