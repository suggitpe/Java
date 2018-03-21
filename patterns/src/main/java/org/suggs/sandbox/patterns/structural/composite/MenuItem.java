/*
 * MenuItem.java created on 7 Sep 2007 16:52:38 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.composite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.suggs.sandbox.patterns.structural.composite.iterators.NullIterator;

import java.util.Iterator;

/**
 * Concrete implementation of a menu component.
 */
public class MenuItem extends AbstractMenuComponent {

    private static final Logger LOG = LoggerFactory.getLogger(MenuItem.class);
    private String name;
    private String description;
    private boolean vegetarian;
    private double price;

    public MenuItem(String aName, String aDescription, boolean isVegetarian, double aPrice) {
        name = aName;
        description = aDescription;
        vegetarian = isVegetarian;
        price = aPrice;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isVegetarian() {
        return vegetarian;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void print() {
        LOG.debug(" " + getName() + (isVegetarian() ? "(v), " : ", ") + getPrice() + "\n\t -- "
                + getDescription());
    }

    @Override
    public Iterator<?> createIterator() {
        return new NullIterator();
    }

}
