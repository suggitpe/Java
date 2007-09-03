/*
 * FacadeTestCase.java created on 31 Aug 2007 06:38:23 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural;

import org.suggs.sandbox.patterns.AbstractPatternTestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Test case for the facade pattern
 * 
 * @author suggitpe
 * @version 1.0 31 Aug 2007
 */
public class FacadeTestCase extends AbstractPatternTestCase
{

    private static final Log LOG = LogFactory.getLog( FacadeTestCase.class );

    public void testName()
    {
        LOG.debug( "FACADE PATTERN" );
    }

}
