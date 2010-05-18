/*
 * MenuItem.java created on 6 Sep 2007 06:54:45 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.iterator;

/**
 * Class to encapsulate a menu item. This would be used in conjunction with a menu.
 * 
 * @author suggitpe
 * @version 1.0 6 Sep 2007
 */
public class MenuItem {

    String name;
    String description;
    boolean vegetarian;
    double price;

    /**
     * Constructs a new instance.
     * 
     * @param aName
     *            the menu item name
     * @param aDescription
     *            the description of the menu item
     * @param isVegetarian
     *            whether the menu item is a vegetarian item
     * @param aPrice
     *            the price of the item
     */
    public MenuItem( String aName, String aDescription, boolean isVegetarian, double aPrice ) {
        name = aName;
        description = aDescription;
        vegetarian = isVegetarian;
        price = aPrice;
    }

    /**
     * Returns the value of Name.
     * 
     * @return Returns the Name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the value of Description.
     * 
     * @return Returns the Description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the value of Vegetarian.
     * 
     * @return Returns the Vegetarian.
     */
    public boolean isVegetarian() {
        return vegetarian;
    }

    /**
     * Returns the value of Price.
     * 
     * @return Returns the Price.
     */
    public double getPrice() {
        return price;
    }

}
