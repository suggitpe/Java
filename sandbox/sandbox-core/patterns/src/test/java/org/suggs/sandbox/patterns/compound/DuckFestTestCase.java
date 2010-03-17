/*
 * DuckFestTestCase.java created on 31 Aug 2007 06:38:14 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound;

import org.suggs.sandbox.patterns.AbstractPatternTestCase;
import org.suggs.sandbox.patterns.compound.quackfest.DuckSimulator;
import org.suggs.sandbox.patterns.compound.quackfest.factory.CountingDuckFactory;

import org.junit.Test;

/**
 * Test case for the compound pattern example duck fest
 * 
 * @author suggitpe
 * @version 1.0 31 Aug 2007
 */
public class DuckFestTestCase extends AbstractPatternTestCase
{

    @Test
    public void testDuckSimulator()
    {
        DuckSimulator sim = new DuckSimulator();
        sim.simulate( new CountingDuckFactory() );
    }

}
