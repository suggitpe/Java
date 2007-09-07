/*
 * AbstractPatternTestCase.java created on 24 Aug 2007 06:28:41 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Abstract test that will do all of the formatting for the pre and
 * post test exec
 * 
 * @author suggitpe
 * @version 1.0 24 Aug 2007
 */
public abstract class AbstractPatternTestCase extends TestCase
{

    private static final Log LOG = LogFactory.getLog( "Test Base" );

    @Override
    protected void setUp() throws Exception
    {
        LOG.info( "--------------------------------- " + this.getName() + " test" );
    }

    @Override
    protected void tearDown() throws Exception
    {
        LOG.info( "---------------------------------" );
    }

    /**
     * Enforce the impl of a test case called testName
     */
    public abstract void testName();

}
