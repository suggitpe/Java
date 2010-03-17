/*
 * AllTests.java created on 25 Sep 2008 18:24:59 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model;

import org.suggs.apps.mercury.model.connection.connection.AllConnectionUnitTests;
import org.suggs.apps.mercury.model.connection.connectionmanager.AllConnectionManagerUnitTests;
import org.suggs.apps.mercury.model.connection.connectionstore.AllConnectionStoreUnitTests;
import org.suggs.apps.mercury.model.util.AllUtilUnitTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite for all of the mercury connection tests
 * 
 * @author suggitpe
 * @version 1.0 25 Sep 2008
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { AllConnectionUnitTests.class, AllConnectionStoreUnitTests.class,
                      AllConnectionManagerUnitTests.class, AllUtilUnitTests.class })
public class AllUnitTests
{}
