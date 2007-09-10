/*
 * MenuComponent.java created on 7 Sep 2007 16:58:03 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.composite;

import org.suggs.sandbox.patterns.structural.composite.iterators.CompositeIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The menu composite class.
 * 
 * @author suggitpe
 * @version 1.0 7 Sep 2007
 */
public class MenuComponent extends AbstractMenuComponent
{

    List<IMenuComponent> mMenuComps_ = new ArrayList<IMenuComponent>();
    String mName_;
    String mDescription_;

    /**
     * Constructs a new instance.
     * 
     * @param aName
     * @param aDescripton
     */
    public MenuComponent( String aName, String aDescripton )
    {
        mName_ = aName;
        mDescription_ = aDescripton;
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.IMenuComponent#add(org.suggs.sandbox.patterns.structural.composite.IMenuComponent)
     */
    @Override
    public void add( IMenuComponent component )
    {
        mMenuComps_.add( component );
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.IMenuComponent#remove(org.suggs.sandbox.patterns.structural.composite.IMenuComponent)
     */
    @Override
    public void remove( IMenuComponent component )
    {
        mMenuComps_.remove( component );
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.IMenuComponent#getChild(int)
     */
    @Override
    public IMenuComponent getChild( int childIndex )
    {
        return mMenuComps_.get( childIndex );
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.IMenuComponent#getDescription()
     */
    @Override
    public String getDescription()
    {
        return mDescription_;
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.IMenuComponent#getName()
     */
    @Override
    public String getName()
    {
        return mName_;
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.IMenuComponent#print()
     */
    @Override
    public void print()
    {
        System.out.println( "\n" + getName() + ", " + getDescription() );
        System.out.println( "-----------------------" );

        // This is a small bit of recursion (if the object in the iter
        // is a MenuComponent rather that a MenuItem).
        Iterator iter = mMenuComps_.iterator();
        while ( iter.hasNext() )
        {
            ( (IMenuComponent) iter.next() ).print();
        }
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.IMenuComponent#createIterator()
     */
    public Iterator createIterator()
    {
        return new CompositeIterator( mMenuComps_.iterator() );
    }

}
