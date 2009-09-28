/*
 * AllUnitTests.java created on 24 Aug 2009 06:56:18 by suggitpe for project Libraries - State Machine
 * 
 */
package com.ubs.orca.orcabridge;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.ubs.orca.orcabridge.readers.OrcaReaderSpringInjectionUnitTest;
import com.ubs.orca.orcabridge.readers.OrcaReaderUnitTest;

/**
 * All Unit Tests suite for the Orca Bridge
 * 
 * @author suggitpe
 * @version 1.0 24 Aug 2009
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { OrcaReaderUnitTest.class, OrcaReaderSpringInjectionUnitTest.class })
public class AllUnitTests
{

    private static final Log LOG = LogFactory.getLog( AllUnitTests.class );

    @BeforeClass
    public static void doBefore()
    {
        LOG.debug( "================== ORCA BRIDGE UNIT TESTS" );
    }
}
