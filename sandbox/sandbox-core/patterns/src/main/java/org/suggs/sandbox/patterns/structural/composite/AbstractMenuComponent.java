/*
 * AbstractMenuComponent.java created on 7 Sep 2007 16:49:44 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.composite;

/**
 * Abstract implementation of the menu component.
 * 
 * @author suggitpe
 * @version 1.0 7 Sep 2007
 */
public abstract class AbstractMenuComponent implements IMenuComponent {

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.IMenuComponent#add(org.suggs.sandbox.patterns.structural.composite.IMenuComponent)
     */
    @Override
    public void add( IMenuComponent component ) {
        throw new UnsupportedOperationException();
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.IMenuComponent#getChild(int)
     */
    @Override
    public IMenuComponent getChild( int childIndex ) {
        throw new UnsupportedOperationException();
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.IMenuComponent#getDescription()
     */
    @Override
    public String getDescription() {
        throw new UnsupportedOperationException();
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.IMenuComponent#getName()
     */
    @Override
    public String getName() {
        throw new UnsupportedOperationException();
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.IMenuComponent#getPrice()
     */
    @Override
    public double getPrice() {
        throw new UnsupportedOperationException();
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.IMenuComponent#isVegetarian()
     */
    @Override
    public boolean isVegetarian() {
        throw new UnsupportedOperationException();
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.IMenuComponent#print()
     */
    @Override
    public void print() {
        throw new UnsupportedOperationException();
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.IMenuComponent#remove(org.suggs.sandbox.patterns.structural.composite.IMenuComponent)
     */
    @Override
    public void remove( IMenuComponent component ) {
        throw new UnsupportedOperationException();
    }
}
