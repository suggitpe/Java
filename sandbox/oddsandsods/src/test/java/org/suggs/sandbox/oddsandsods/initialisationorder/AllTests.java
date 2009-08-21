/*
 * AllTests.java created on 2 Aug 2007 06:03:40 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.sandbox.oddsandsods.initialisationorder;

import org.suggs.sandbox.oddsandsods.initialisationorder.basic.TestBasics;
import org.suggs.sandbox.oddsandsods.initialisationorder.simple.SimpleInstantiationTestCase;
import org.suggs.sandbox.oddsandsods.initialisationorder.specific.StaticMemberTest;
import org.suggs.sandbox.oddsandsods.initialisationorder.specific.StaticMethodTest;
import org.suggs.sandbox.oddsandsods.initialisationorder.specific.TestConstruction;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite for all tests for the mercury model
 * 
 * @author suggitpe
 * @version 1.0 2 Aug 2007
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { SimpleInstantiationTestCase.class, TestBasics.class, StaticMemberTest.class,
                      StaticMethodTest.class, TestConstruction.class })
public class AllTests
{}
