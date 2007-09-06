/*
 * MenuItem.java created on 6 Sep 2007 06:54:45 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.iterator;

/**
 * Class to encapsulate a menu item. This would be used in conjunction
 * with a menu.
 * 
 * @author suggitpe
 * @version 1.0 6 Sep 2007
 */
public class MenuItem
{

    String mName_;
    String mDescription_;
    boolean mVegetarian_;
    double mPrice_;

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
    public MenuItem( String aName, String aDescription, boolean isVegetarian, double aPrice )
    {
        mName_ = aName;
        mDescription_ = aDescription;
        mVegetarian_ = isVegetarian;
        mPrice_ = aPrice;
    }

    /**
     * Returns the value of Name.
     * 
     * @return Returns the Name.
     */
    public String getName()
    {
        return mName_;
    }

    /**
     * Returns the value of Description.
     * 
     * @return Returns the Description.
     */
    public String getDescription()
    {
        return mDescription_;
    }

    /**
     * Returns the value of Vegetarian.
     * 
     * @return Returns the Vegetarian.
     */
    public boolean isVegetarian()
    {
        return mVegetarian_;
    }

    /**
     * Returns the value of Price.
     * 
     * @return Returns the Price.
     */
    public double getPrice()
    {
        return mPrice_;
    }

}
