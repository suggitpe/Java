/*
 * IteratorTestCase.java created on 6 Sep 2007 18:08:09 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural;

import org.suggs.sandbox.patterns.AbstractPatternTestCase;
import org.suggs.sandbox.patterns.behavioural.iterator.Waitress;
import org.suggs.sandbox.patterns.behavioural.iterator.aggregates.DinerMenu;
import org.suggs.sandbox.patterns.behavioural.iterator.aggregates.PancakeHouseMenu;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Test case for the Iterator pattern
 * 
 * @author suggitpe
 * @version 1.0 6 Sep 2007
 */
public class IteratorTestCase extends AbstractPatternTestCase
{

    private static final Log LOG = LogFactory.getLog( IteratorTestCase.class );

    public void testName()
    {
        LOG.debug( "ITERATOR PATTERN" );
    }

    public void testPrintMenu()
    {
        Waitress waitress = new Waitress( new PancakeHouseMenu(), new DinerMenu() );
        waitress.printMenu();
    }

    public void testPrintLunchMenu()
    {
        Waitress waitress = new Waitress( new PancakeHouseMenu(), new DinerMenu() );
        waitress.printLunchMenu();
    }
}
