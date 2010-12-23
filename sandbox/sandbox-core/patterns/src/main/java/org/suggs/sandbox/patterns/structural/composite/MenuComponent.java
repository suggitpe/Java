/*
 * MenuComponent.java created on 7 Sep 2007 16:58:03 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.composite;

import org.suggs.sandbox.patterns.structural.composite.iterators.CompositeIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The menu composite class.
 * 
 * @author suggitpe
 * @version 1.0 7 Sep 2007
 */
public class MenuComponent extends AbstractMenuComponent {

    private static final Logger LOG = LoggerFactory.getLogger( MenuComponent.class );
    private List<IMenuComponent> menuComps = new ArrayList<IMenuComponent>();
    private String name;
    private String description;

    /**
     * Constructs a new instance.
     * 
     * @param aName
     * @param aDescripton
     */
    public MenuComponent( String aName, String aDescripton ) {
        name = aName;
        description = aDescripton;
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.IMenuComponent#add(org.suggs.sandbox.patterns.structural.composite.IMenuComponent)
     */
    @Override
    public void add( IMenuComponent component ) {
        menuComps.add( component );
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.IMenuComponent#remove(org.suggs.sandbox.patterns.structural.composite.IMenuComponent)
     */
    @Override
    public void remove( IMenuComponent component ) {
        menuComps.remove( component );
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.IMenuComponent#getChild(int)
     */
    @Override
    public IMenuComponent getChild( int childIndex ) {
        return menuComps.get( childIndex );
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.IMenuComponent#getDescription()
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.IMenuComponent#getName()
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.IMenuComponent#print()
     */
    @Override
    public void print() {
        LOG.debug( "\n" + getName() + ", " + getDescription() );
        LOG.debug( "-----------------------" );

        // This is a small bit of recursion (if the object in the iter
        // is a MenuComponent rather that a MenuItem).
        Iterator<?> iter = menuComps.iterator();
        while ( iter.hasNext() ) {
            ( (IMenuComponent) iter.next() ).print();
        }
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.IMenuComponent#createIterator()
     */
    @Override
    public Iterator<?> createIterator() {
        return new CompositeIterator( menuComps.iterator() );
    }

}
