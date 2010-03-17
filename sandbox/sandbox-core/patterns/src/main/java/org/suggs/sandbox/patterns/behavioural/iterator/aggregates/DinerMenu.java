/*
 * DinerMenu.java created on 6 Sep 2007 07:10:10 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.iterator.aggregates;

import org.suggs.sandbox.patterns.behavioural.iterator.IIterator;
import org.suggs.sandbox.patterns.behavioural.iterator.IMenu;
import org.suggs.sandbox.patterns.behavioural.iterator.MenuItem;
import org.suggs.sandbox.patterns.behavioural.iterator.iterators.DinerMenuIterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Menu for a diner.
 * 
 * @author suggitpe
 * @version 1.0 6 Sep 2007
 */
public class DinerMenu implements IMenu
{

    private static final Log LOG = LogFactory.getLog( DinerMenu.class );

    private static final int MAX_ITEMS = 6;
    private int mNumItems = 0;
    private MenuItem[] mMenuItems = new MenuItem[MAX_ITEMS];

    /**
     * Constructs a new instance.
     */
    public DinerMenu()
    {
        addItem( "Vegetarian BLT", "'Fakin' bacon with lettuce & tomato on whie bread", true, 2.99d );
        addItem( "BLT", "Bacon lettuce and tomate on whole wheat", false, 2.99d );
        addItem( "Soup of the day", "soup of the day with a bread roll", false, 3.29d );
        addItem( "Hot Dog", "A hot dog with all the trimmings", false, 3.05d );
    }

    /**
     * Add an item to the menu list
     * 
     * @param aName
     *            the name of the menu item
     * @param aDescription
     *            the description of the menu item
     * @param isVegetarian
     *            true if the menuitem is vegetarian
     * @param aPrice
     *            the price of the menu item
     */
    private void addItem( String aName, String aDescription, boolean isVegetarian, double aPrice )
    {
        if ( mNumItems >= MAX_ITEMS )
        {
            LOG.error( "No space left for any more menu items on this menu" );
        }
        else
        {
            mMenuItems[mNumItems++] = new MenuItem( aName, aDescription, isVegetarian, aPrice );
        }
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.iterator.IMenu#createIterator()
     */
    public IIterator createIterator()
    {
        return new DinerMenuIterator( mMenuItems );
    }

}
