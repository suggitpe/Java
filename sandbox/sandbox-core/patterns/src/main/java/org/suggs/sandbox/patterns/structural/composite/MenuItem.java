/*
 * MenuItem.java created on 7 Sep 2007 16:52:38 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.composite;

import org.suggs.sandbox.patterns.structural.composite.iterators.NullIterator;

import java.util.Iterator;

/**
 * Concrete implementation of a menu component.
 * 
 * @author suggitpe
 * @version 1.0 7 Sep 2007
 */
public class MenuItem extends AbstractMenuComponent
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
     * @see org.suggs.sandbox.patterns.structural.composite.AbstractMenuComponent#getName()
     */
    @Override
    public String getName()
    {
        return mName_;
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.AbstractMenuComponent#getDescription()
     */
    @Override
    public String getDescription()
    {
        return mDescription_;
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.AbstractMenuComponent#isVegetarian()
     */
    @Override
    public boolean isVegetarian()
    {
        return mVegetarian_;
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.AbstractMenuComponent#getPrice()
     */
    @Override
    public double getPrice()
    {
        return mPrice_;
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.AbstractMenuComponent#print()
     */
    @Override
    public void print()
    {
        System.out.println( " " + getName() + ( isVegetarian() ? "(v), " : ", " ) + getPrice()
                            + "\n\t -- " + getDescription() );
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.IMenuComponent#createIterator()
     */
    @SuppressWarnings("unchecked")
    public Iterator createIterator()
    {
        return new NullIterator();
    }

}
