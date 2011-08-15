/*
 * TestBasics.java created on 21 Sep 2007 07:13:34 by suggitpe for project SandBox - OddsAndSods
 * 
 */
package org.suggs.sandbox.oddsandsods.initialisationorder.basic;

import org.junit.Test;

/**
 * Simple test suite so that we can get the output from the the Test Class. the whole point of this is to log
 * out the order in which things are initialised inside a class.
 * 
 * @author suggitpe
 * @version 1.0 21 Sep 2007
 */
public class TestBasics {

    /**
     * 
     */
    @Test
    public void testBasics() {
        @SuppressWarnings("unused")
        TestClass clazz = new TestClass();
    }
}
