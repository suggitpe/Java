/*
 * MvcTestCase.java created on 31 Aug 2007 06:38:14 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound;

import org.suggs.sandbox.patterns.AbstractPatternTestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Test case for the compound pattern example Model View Controller
 * 
 * @author suggitpe
 * @version 1.0 31 Aug 2007
 */
public class MvcTestCase extends AbstractPatternTestCase
{

    private static final Log LOG = LogFactory.getLog( MvcTestCase.class );

    /**
     * @see org.suggs.sandbox.patterns.AbstractPatternTestCase#testName()
     */
    @Override
    public void testName()
    {
        LOG.info( "=================================" );
        LOG.debug( "COMPOUND PATTERNS - MVC (Model View Controller)" );
    }

}
