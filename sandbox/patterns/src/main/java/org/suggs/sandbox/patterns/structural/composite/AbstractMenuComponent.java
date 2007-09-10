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
public abstract class AbstractMenuComponent implements IMenuComponent
{

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.IMenuComponent#add(org.suggs.sandbox.patterns.structural.composite.IMenuComponent)
     */
    public void add( IMenuComponent component )
    {
        throw new UnsupportedOperationException();
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.IMenuComponent#getChild(int)
     */
    public IMenuComponent getChild( int childIndex )
    {
        throw new UnsupportedOperationException();
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.IMenuComponent#getDescription()
     */
    public String getDescription()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.IMenuComponent#getName()
     */
    public String getName()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.IMenuComponent#getPrice()
     */
    public double getPrice()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.IMenuComponent#isVegetarian()
     */
    public boolean isVegetarian()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.IMenuComponent#print()
     */
    public void print()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.IMenuComponent#remove(org.suggs.sandbox.patterns.structural.composite.IMenuComponent)
     */
    public void remove( IMenuComponent component )
    {
        throw new UnsupportedOperationException();
    }
}
