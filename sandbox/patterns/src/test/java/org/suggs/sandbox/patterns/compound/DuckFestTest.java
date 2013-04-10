/*
 * DuckFestTestCase.java created on 31 Aug 2007 06:38:14 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound;

import org.suggs.sandbox.patterns.AbstractPatternTest;
import org.suggs.sandbox.patterns.compound.quackfest.DuckSimulator;
import org.suggs.sandbox.patterns.compound.quackfest.factory.CountingDuckFactory;

import org.junit.Test;

/**
 * Test case for the compound pattern example duck fest
 */
public class DuckFestTest extends AbstractPatternTest {

    @Test
    public void testDuckSimulator() {
        DuckSimulator sim = new DuckSimulator();
        sim.simulate( new CountingDuckFactory() );
    }

}
