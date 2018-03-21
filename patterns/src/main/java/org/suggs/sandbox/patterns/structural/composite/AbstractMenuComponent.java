/*
 * AbstractMenuComponent.java created on 7 Sep 2007 16:49:44 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.composite;

/**
 * Abstract implementation of the menu component.
 */
public abstract class AbstractMenuComponent implements IMenuComponent {

    @Override
    public void add(IMenuComponent component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public IMenuComponent getChild(int childIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getPrice() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isVegetarian() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void print() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(IMenuComponent component) {
        throw new UnsupportedOperationException();
    }
}
