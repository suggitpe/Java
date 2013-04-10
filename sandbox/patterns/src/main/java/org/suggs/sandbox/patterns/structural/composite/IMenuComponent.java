/*
 * IMenuComponent.java created on 7 Sep 2007 06:21:07 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.composite;

import java.util.Iterator;

/**
 * Interface for the composite and leaf nodes within the composite tree.
 */
public interface IMenuComponent {

    void add(IMenuComponent aComponent);

    void remove(IMenuComponent aComponent);

    IMenuComponent getChild(int aChildIndex);

    String getName();

    String getDescription();

    double getPrice();

    boolean isVegetarian();

    void print();

    Iterator<?> createIterator();

}
