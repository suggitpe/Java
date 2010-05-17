/*
 * AllTests.java created on 23 Aug 2007 06:03:40 by suggitpe for project Sandbox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite for all tests for the mercury connection model
 * 
 * @author suggitpe
 * @version 1.0 2 Aug 2007
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { AdapterTestCase.class, CompositeTestCase.class, DecoratorTestCase.class,
                      FacadeTestCase.class, ProxyTestCase.class })
public class AllTests {}
