/*
 * MenuItem.java created on 7 Sep 2007 16:52:38 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.composite;

import org.suggs.sandbox.patterns.structural.composite.iterators.NullIterator;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Concrete implementation of a menu component.
 * 
 * @author suggitpe
 * @version 1.0 7 Sep 2007
 */
public class MenuItem extends AbstractMenuComponent {

    private static final Logger LOG = LoggerFactory.getLogger( MenuItem.class );
    private String name;
    private String description;
    private boolean vegetarian;
    private double price;

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
     * @see org.suggs.sandbox.patterns.structural.composite.AbstractMenuComponent#getName()
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.AbstractMenuComponent#getDescription()
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.AbstractMenuComponent#isVegetarian()
     */
    @Override
    public boolean isVegetarian() {
        return vegetarian;
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.AbstractMenuComponent#getPrice()
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.AbstractMenuComponent#print()
     */
    @Override
    public void print() {
        LOG.debug( " " + getName() + ( isVegetarian() ? "(v), " : ", " ) + getPrice() + "\n\t -- "
                   + getDescription() );
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.IMenuComponent#createIterator()
     */
    @Override
    public Iterator<?> createIterator() {
        return new NullIterator();
    }

}
