/*
 * AllUnitTests.java created on 13 Oct 2009 19:09:33 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.processors;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite for the readers unit tests.
 * 
 * @author suggitpe
 * @version 1.0 13 Oct 2009
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { JmsMessageSenderTest.class, JmsMessageSenderSprintInjectionTest.class,
                      OrcaMessageSenderTest.class, OrcaMessageSenderSpringInjectionTest.class })
public class AllUnitTests
{}
