/*
 * AllTests.java created on 23 Aug 2007 06:03:40 by suggitpe for project Sandbox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite for all tests for the behavioural unit tests
 * 
 * @author suggitpe
 * @version 1.0 2 Aug 2007
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { CommandTestCase.class, IteratorTestCase.class, ObserverTestCase.class,
                      TemplateTestCase.class,
                      org.suggs.sandbox.patterns.behavioural.state.AllTests.class })
public class AllTests
{}
