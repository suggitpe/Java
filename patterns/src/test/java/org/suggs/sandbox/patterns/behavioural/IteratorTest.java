/*
 * IteratorTestCase.java created on 6 Sep 2007 18:08:09 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural;

import org.suggs.sandbox.patterns.AbstractPatternTest;
import org.suggs.sandbox.patterns.behavioural.iterator.Waitress;
import org.suggs.sandbox.patterns.behavioural.iterator.aggregates.DinerMenu;
import org.suggs.sandbox.patterns.behavioural.iterator.aggregates.PancakeHouseMenu;

import org.junit.Test;

/**
 * Test case for the Iterator pattern
 */
public class IteratorTest extends AbstractPatternTest {

    @Test
    public void testPrintMenu() {
        Waitress waitress = new Waitress( new PancakeHouseMenu(), new DinerMenu() );
        waitress.printMenu();
    }

    @Test
    public void testPrintLunchMenu() {
        Waitress waitress = new Waitress( new PancakeHouseMenu(), new DinerMenu() );
        waitress.printLunchMenu();
    }
}
