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
public class PancakeHouseMenuIterator implements IIterator {

    private List<MenuItem> items;
    private int position = 0;

    /**
     * Constructs a new instance.
     * 
     * @param aItems
     *            a list of menu items
     */
    public PancakeHouseMenuIterator( List<MenuItem> aItems ) {
        items = aItems;
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.iterator.IIterator#hasNext()
     */
    @Override
    public boolean hasNext() {
        if ( position >= items.size() || items.get( position ) == null ) {
            return false;
        }
        return true;
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.iterator.IIterator#next()
     */
    @Override
    public Object next() {
        return items.get( position++ );
    }

}
