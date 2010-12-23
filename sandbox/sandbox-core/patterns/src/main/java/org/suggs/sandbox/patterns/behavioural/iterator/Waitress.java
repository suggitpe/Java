/*
 * Waitress.java created on 6 Sep 2007 07:19:37 by suggitpe for project SandBox - Patterns
 */
package org.suggs.sandbox.patterns.behavioural.iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to represent a waitress
 * 
 * @author suggitpe
 * @version 1.0 6 Sep 2007
 */
public class Waitress {

    private static final Logger LOG = LoggerFactory.getLogger( Waitress.class );

    private IMenu pancakeMenu;
    private IMenu dinerMenu;

    /**
     * Constructs a new instance.
     * 
     * @param aPancakeMenu
     *            the pancake menu
     * @param aDinerMenu
     *            the diner menu
     */
    public Waitress( IMenu aPancakeMenu, IMenu aDinerMenu ) {
        pancakeMenu = aPancakeMenu;
        dinerMenu = aDinerMenu;
    }

    /**
     * Print every item on the menu
     */
    public void printMenu() {
        LOG.debug( "MENU\n--------\nBREAKFAST:" );
        printMenu( pancakeMenu.createIterator() );
        LOG.debug( "LUNCH:" );
        printMenu( dinerMenu.createIterator() );
        LOG.debug( "--------" );
    }

    /**
     * Print all items from the breakfast menu
     */
    public void printBreakfastMenu() {
        LOG.debug( "BREAKFAST MENU\n--------------\n" );
        printMenu( pancakeMenu.createIterator() );
        LOG.debug( "--------------" );
    }

    /**
     * Print all items from the lunch menu
     */
    public void printLunchMenu() {
        LOG.debug( "LUNCH MENU\n----------" );
        printMenu( dinerMenu.createIterator() );
        LOG.debug( "----------" );
    }

    /**
     * Print command for an iterator
     * 
     * @param aIter
     */
    private void printMenu( IIterator aIter ) {
        while ( aIter.hasNext() ) {
            MenuItem m = (MenuItem) aIter.next();
            LOG.debug( m.getName() + ", �" + m.getPrice() + " -- " + m.getDescription() );
        }

    }

    /**
     * Confirms whether an item is vegetarian or not
     * 
     * @param aName
     *            the name of the item
     * @return true if the item is a vagetarian item, else false
     */
    public boolean isItemVegetarian( String aName ) {
        return true;
    }

}
