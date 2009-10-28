/*
 * AllUnitTests.java created on 13 Oct 2009 19:09:33 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.jmsclient;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.ubs.orca.orcabridge.jmsclient.impl.JmsClientCoreSpringInjectionUnitTest;
import com.ubs.orca.orcabridge.jmsclient.impl.JmsClientCoreTestConnection;
import com.ubs.orca.orcabridge.jmsclient.impl.JmsClientCoreTestProcess;
import com.ubs.orca.orcabridge.jmsclient.impl.JmsDurableReaderActionTest;
import com.ubs.orca.orcabridge.jmsclient.impl.JmsSenderActionTest;

/**
 * Test suite for the JMS client unit tests.
 * 
 * @author suggitpe
 * @version 1.0 13 Oct 2009
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { JmsClientCoreTestConnection.class, JmsClientCoreTestProcess.class,
                      JmsClientCoreSpringInjectionUnitTest.class, JmsSenderActionTest.class,
                      JmsDurableReaderActionTest.class })
public class AllUnitTests
{}
