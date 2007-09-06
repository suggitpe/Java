/*
 * PancakeHouseMenuIterator.java created on 6 Sep 2007 17:50:05 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.iterator.iterators;

import org.suggs.sandbox.patterns.behavioural.iterator.IIterator;
import org.suggs.sandbox.patterns.behavioural.iterator.MenuItem;

import java.util.List;

/**
 * Iterator class for the pancakehouse menu class.
 * 
 * @author suggitpe
 * @version 1.0 6 Sep 2007
 */
public class PancakeHouseMenuIterator implements IIterator
{

    private List<MenuItem> mItems;
    int mPos = 0;

    /**
     * Constructs a new instance.
     * 
     * @param aItems
     *            a list of menu items
     */
    public PancakeHouseMenuIterator( List<MenuItem> aItems )
    {
        mItems = aItems;
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.iterator.IIterator#hasNext()
     */
    public boolean hasNext()
    {
        if ( mPos >= mItems.size() || mItems.get( mPos ) == null )
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.iterator.IIterator#next()
     */
    public Object next()
    {
        return mItems.get( mPos++ );
    }

}
