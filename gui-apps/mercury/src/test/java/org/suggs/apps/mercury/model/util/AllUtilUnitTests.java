/*
 * AllUtilUnitTests.java created on 10 Dec 2008 19:23:53 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.util;

import org.suggs.apps.mercury.model.util.xml.DomParserUnitTest;
import org.suggs.apps.mercury.model.util.xml.XsltTransformerUtilTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite for the mercury utility functions.
 * 
 * @author suggitpe
 * @version 1.0 10 Dec 2008
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { DomParserUnitTest.class, XsltTransformerUtilTest.class })
public class AllUtilUnitTests
{}
