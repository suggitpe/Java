/*
 * IMenuComponent.java created on 7 Sep 2007 06:21:07 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.composite;

import java.util.Iterator;

/**
 * Interface for the composite and leaf nodes within the composite tree.
 * 
 * @author suggitpe
 * @version 1.0 7 Sep 2007
 */
public interface IMenuComponent {

    /**
     * Add a new child to this component
     * 
     * @param aComponent
     *            the component to add
     */
    void add( IMenuComponent aComponent );

    /**
     * Remove a child from this comonent
     * 
     * @param aComponent
     *            the componen to remove
     */
    void remove( IMenuComponent aComponent );

    /**
     * Get a child
     * 
     * @param aChildIndex
     *            the index number of the child
     * @return the child
     */
    IMenuComponent getChild( int aChildIndex );

    /**
     * Get the name of the component
     * 
     * @return the name of the component
     */
    String getName();

    /**
     * Get the description of the component
     * 
     * @return the description of the component
     */
    String getDescription();

    /**
     * Get the price of the component
     * 
     * @return the price of the component
     */
    double getPrice();

    /**
     * Get the vegetarian flag for the component
     * 
     * @return true if the component is vegetarian
     */
    boolean isVegetarian();

    /**
     * Print out the component
     */
    void print();

    /**
     * Create an iterator for the composite object.
     * 
     * @return an iterator that knows how to iterator over a composite object tree
     */
    Iterator<?> createIterator();

}
