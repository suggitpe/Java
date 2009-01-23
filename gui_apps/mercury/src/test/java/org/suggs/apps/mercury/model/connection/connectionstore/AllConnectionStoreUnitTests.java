/*
 * AllConnectionUnitTests.java created on 10 Dec 2008 19:27:02 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connectionstore;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite for mercury connection store functions.
 * 
 * @author suggitpe
 * @version 1.0 10 Dec 2008
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { XmlConnectionStoreDaoUnitTest.class,
                      CachedXmlConnectionStoreManagerDecoratorUnitTest.class,
                      JaxbXmlConnectionStoreManagerUnitTest.class })
public class AllConnectionStoreUnitTests
{}
