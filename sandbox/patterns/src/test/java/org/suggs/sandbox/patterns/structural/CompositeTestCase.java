/*
 * CompositeTestCase.java created on 7 Sep 2007 06:11:52 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural;

import org.suggs.sandbox.patterns.AbstractPatternTestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Test case for the composite pattern.
 * 
 * @author suggitpe
 * @version 1.0 7 Sep 2007
 */
public class CompositeTestCase extends AbstractPatternTestCase
{

    private static final Log LOG = LogFactory.getLog( CompositeTestCase.class );

    /**
     * @see org.suggs.sandbox.patterns.AbstractPatternTestCase#testName()
     */
    @Override
    public void testName()
    {
        LOG.info( "=================================" );
        LOG.debug( "COMPOSITE PATTERN" );
    }

}
