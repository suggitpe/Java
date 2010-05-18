/*
 * PancakeHouseMenu.java created on 6 Sep 2007 07:01:31 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.iterator.aggregates;

import org.suggs.sandbox.patterns.behavioural.iterator.IIterator;
import org.suggs.sandbox.patterns.behavioural.iterator.IMenu;
import org.suggs.sandbox.patterns.behavioural.iterator.MenuItem;
import org.suggs.sandbox.patterns.behavioural.iterator.iterators.PancakeHouseMenuIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * A menu at the pancake house.
 * 
 * @author suggitpe
 * @version 1.0 6 Sep 2007
 */
public class PancakeHouseMenu implements IMenu {

    private List<MenuItem> menuItems = new ArrayList<MenuItem>();

    /**
     * Constructs a new instance.
     */
    public PancakeHouseMenu() {
        addItem( "K&B's pancake breakfast", "Pancakes with scrambled egss and toast", true, 2.99d );
        addItem( "Regular pancake breakfast", "Pancakes with fried eggs and sausage", false, 2.99d );
        addItem( "Blueberry pancakes", "Pancakes made with fresh blueberries", true, 3.49d );
        addItem( "Waffles", "Waffles with your choice of topping", true, 3.59d );
    }

    /**
     * Add an item to the collection
     * 
     * @param aName
     * @param aDescription
     * @param isVegetarian
     * @param aPrice
     */
    private void addItem( String aName, String aDescription, boolean isVegetarian, double aPrice ) {
        MenuItem item = new MenuItem( aName, aDescription, isVegetarian, aPrice );
        menuItems.add( item );
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.iterator.IMenu#createIterator()
     */
    public IIterator createIterator() {
        return new PancakeHouseMenuIterator( menuItems );
    }

    /**
     * Getter for the menu items list
     * 
     * @return the list of menu items
     */
    @SuppressWarnings("unchecked")
    public List getMenuItems() {
        return menuItems;
    }

}
