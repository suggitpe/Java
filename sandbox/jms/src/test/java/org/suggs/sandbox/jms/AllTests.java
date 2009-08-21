/*
 * AllUnitTests.java created on 25 Sep 2008 18:20:37 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.sandbox.jms;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite to manage all of the unit tests from a central execution
 * 
 * @author suggitpe
 * @version 1.0 25 Sep 2008
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { TestPublishToTopic.class, TestSubscribeToTopic.class })
public class AllTests
{}
