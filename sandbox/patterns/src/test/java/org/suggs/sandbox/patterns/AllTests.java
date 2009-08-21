/*
 * AllTests.java created on 2 Aug 2007 06:03:40 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.sandbox.patterns;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite for all tests for the mercury model
 * 
 * @author suggitpe
 * @version 1.0 2 Aug 2007
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { org.suggs.sandbox.patterns.behavioural.AllTests.class,
                      org.suggs.sandbox.patterns.compound.AllTests.class,
                      org.suggs.sandbox.patterns.creational.AllTests.class,
                      org.suggs.sandbox.patterns.structural.AllTests.class })
public class AllTests
{}
