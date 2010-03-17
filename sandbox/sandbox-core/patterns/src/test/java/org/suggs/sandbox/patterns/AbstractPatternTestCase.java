/*
 * AbstractPatternTestCase.java created on 24 Aug 2007 06:28:41 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * Abstract test that will do all of the formatting for the pre and
 * post test exec
 * 
 * @author suggitpe
 * @version 1.0 24 Aug 2007
 */
public abstract class AbstractPatternTestCase
{

    private static final Log LOG = LogFactory.getLog( "Test Base" );

    @BeforeClass
    public static void doubleLine()
    {
        LOG.info( "=================================" );
    }

    @Before
    public void singleLine() throws Exception
    {
        LOG.info( "---------------------------------" );
        LOG.info( "Executing: " + getClass().getSimpleName() );
        LOG.info( "---------------------------------" );
    }

}
