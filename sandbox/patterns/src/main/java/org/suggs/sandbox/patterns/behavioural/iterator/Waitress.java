/*
 * Waitress.java created on 6 Sep 2007 07:19:37 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.iterator;

/**
 * Class to represent a waitress
 * 
 * @author suggitpe
 * @version 1.0 6 Sep 2007
 */
public class Waitress
{

    private IMenu mPancakeMenu_;
    private IMenu mDinerMenu_;

    /**
     * Constructs a new instance.
     * 
     * @param aPancakeMenu
     *            the pancake menu
     * @param aDinerMenu
     *            the diner menu
     */
    public Waitress( IMenu aPancakeMenu, IMenu aDinerMenu )
    {
        mPancakeMenu_ = aPancakeMenu;
        mDinerMenu_ = aDinerMenu;
    }

    /**
     * Print every item on the menu
     */
    public void printMenu()
    {
        System.out.println( "MENU\n--------\nBREAKFAST:" );
        printMenu( mPancakeMenu_.createIterator() );
        System.out.println( "LUNCH:" );
        printMenu( mDinerMenu_.createIterator() );
        System.out.println( "--------" );
    }

    /**
     * Print all items from the breakfast menu
     */
    public void printBreakfastMenu()
    {
        System.out.println( "BREAKFAST MENU\n--------------\n" );
        printMenu( mPancakeMenu_.createIterator() );
        System.out.println( "--------------" );
    }

    /**
     * Print all items from the lunch menu
     */
    public void printLunchMenu()
    {
        System.out.println( "LUNCH MENU\n----------" );
        printMenu( mDinerMenu_.createIterator() );
        System.out.println( "----------" );
    }

    /**
     * Print command for an iterator
     * 
     * @param aIter
     */
    private void printMenu( IIterator aIter )
    {
        while ( aIter.hasNext() )
        {
            MenuItem m = (MenuItem) aIter.next();
            System.out.println( m.getName() + ", £" + m.getPrice() + " -- " + m.getDescription() );
        }

    }

    /**
     * Confirms whether an item is vegetarian or not
     * 
     * @param aName
     *            the name of the item
     * @return true if the item is a vagetarian item, else false
     */
    public boolean isItemVegetarian( String aName )
    {
        return true;
    }

}
