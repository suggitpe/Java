/*
 * DuckFestTestCase.java created on 31 Aug 2007 06:38:14 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound;

import org.suggs.sandbox.patterns.AbstractPatternTestCase;
import org.suggs.sandbox.patterns.compound.quackfest.DuckSimulator;
import org.suggs.sandbox.patterns.compound.quackfest.factory.CountingDuckFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Test case for the compound pattern example duck fest
 * 
 * @author suggitpe
 * @version 1.0 31 Aug 2007
 */
public class DuckFestTestCase extends AbstractPatternTestCase
{

    private static final Log LOG = LogFactory.getLog( DuckFestTestCase.class );

    /**
     * @see org.suggs.sandbox.patterns.AbstractPatternTestCase#testName()
     */
    @Override
    public void testName()
    {
        LOG.info( "=================================" );
        LOG.debug( "COMPOUND PATTERNS - DuckFest" );
    }

    /**
     * Test the duck simulator
     */
    public void testDuckSimulator()
    {
        DuckSimulator sim = new DuckSimulator();

        sim.simulate( new CountingDuckFactory() );

    }

}
