/*
 * AllTests.java created on 25 Sep 2008 18:24:59 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model;

import org.suggs.apps.mercury.model.connection.connectionstore.CachedXmlConnectionStoreManagerDecoratorTest;
import org.suggs.apps.mercury.model.connection.connectionstore.JaxbXmlConnectionStoreManagerTest;
import org.suggs.apps.mercury.model.connection.connectionstore.XmlConnectionStoreDaoTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite for all of the mercury connection tests
 * 
 * @author suggitpe
 * @version 1.0 25 Sep 2008
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { XmlConnectionStoreDaoTest.class,
                      CachedXmlConnectionStoreManagerDecoratorTest.class,
                      JaxbXmlConnectionStoreManagerTest.class })
public class AllUnitTests
{}
