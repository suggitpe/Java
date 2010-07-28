/*
 * Waitress.java created on 7 Sep 2007 17:09:06 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.composite;

import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The client in the relationship.
 * 
 * @author suggitpe
 * @version 1.0 7 Sep 2007
 */
public class Waitress {

    private static final Log LOG = LogFactory.getLog( Waitress.class );
    private IMenuComponent allMenus;

    /**
     * Constructs a new instance.
     * 
     * @param aComponent
     *            the menu component
     */
    public Waitress( IMenuComponent aComponent ) {
        allMenus = aComponent;
    }

    /**
     * Print the content of all the menus
     */
    public void printMenu() {
        allMenus.print();
    }

    /**
     * Using the composite iterator, print out the vegetarian menu.
     */
    public void printVegetarianMenu() {
        Iterator<?> iter = allMenus.createIterator();
        LOG.debug( "\nVEGETARIAN MENU\n-----------------" );
        while ( iter.hasNext() ) {
            IMenuComponent comp = (IMenuComponent) iter.next();
            try {
                if ( comp.isVegetarian() ) {
                    comp.print();
                }
            }
            catch ( UnsupportedOperationException e ) {}
        }

    }
}
