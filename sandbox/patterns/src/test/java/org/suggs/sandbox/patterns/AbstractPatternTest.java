/*
 * AbstractPatternTestCase.java created on 24 Aug 2007 06:28:41 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * Abstract test that will do all of the formatting for the pre and post test exec
 */
public abstract class AbstractPatternTest {

    private static final Logger LOG = LoggerFactory.getLogger( "Test Base" );

    @Before
    public void singleLine() throws Exception {
        LOG.info( "Executing: " + getClass().getSimpleName() );
    }

}
