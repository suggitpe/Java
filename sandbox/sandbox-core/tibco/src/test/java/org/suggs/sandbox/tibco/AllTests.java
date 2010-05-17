/*
 * AllTests.java created on 19 Jan 2009 19:19:05 by suggitpe for project SandBox - Tibco
 * 
 */
package org.suggs.sandbox.tibco;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite for the Tibco test cases.
 * 
 * @author suggitpe
 * @version 1.0 19 Jan 2009
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { TestTibcoAdminUtil.class, TestTibcoConnectionProperties.class })
public class AllTests {}
