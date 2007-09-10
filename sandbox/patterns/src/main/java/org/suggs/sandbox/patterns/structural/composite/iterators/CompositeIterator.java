/*
 * CompositeIterator.java created on 10 Sep 2007 06:58:28 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.composite.iterators;

import org.suggs.sandbox.patterns.structural.composite.IMenuComponent;
import org.suggs.sandbox.patterns.structural.composite.MenuComponent;

import java.util.Iterator;
import java.util.Stack;

/**
 * Iterator for a composite class hierarchy
 * 
 * @author suggitpe
 * @version 1.0 10 Sep 2007
 */
public class CompositeIterator implements Iterator
{

    Stack<Iterator> mStack_ = new Stack<Iterator>();

    /**
     * Constructs a new instance.
     * 
     * @param aIter
     *            an iterator to use
     */
    public CompositeIterator( Iterator aIter )
    {
        mStack_.push( aIter );
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.IIterator#hasNext()
     */
    public boolean hasNext()
    {
        if ( mStack_.empty() )
        {
            return false;
        }
        else
        {
            Iterator i = mStack_.peek();
            if ( !i.hasNext() )
            {
                mStack_.pop();
                return hasNext();

            }
            else
            {
                return true;
            }
        }
    }

    /**
     * @see org.suggs.sandbox.patterns.structural.composite.IIterator#next()
     */
    public Object next()
    {
        if ( hasNext() )
        {
            Iterator iter = mStack_.peek();
            IMenuComponent comp = (IMenuComponent) iter.next();
            if ( comp instanceof MenuComponent )
            {
                mStack_.push( comp.createIterator() );
            }
            return comp;
        }
        else
        {
            return null;
        }
    }

    /**
     * @see java.util.Iterator#remove()
     */
    public void remove()
    {
        throw new UnsupportedOperationException();
    }

}
