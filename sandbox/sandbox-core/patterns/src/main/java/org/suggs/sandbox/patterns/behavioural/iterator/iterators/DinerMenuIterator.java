/*
 * DinerMenuIterator.java created on 6 Sep 2007 07:26:44 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.iterator.iterators;

import org.suggs.sandbox.patterns.behavioural.iterator.IIterator;
import org.suggs.sandbox.patterns.behavioural.iterator.MenuItem;

import java.util.Arrays;

/**
 * Iterator for the diner menu
 * 
 * @author suggitpe
 * @version 1.0 6 Sep 2007
 */
public class DinerMenuIterator implements IIterator {

    private MenuItem[] items;
    int position = 0;

    /**
     * Constructs a new instance.
     * 
     * @param aItems
     */
    public DinerMenuIterator( MenuItem[] aItems ) {
        if ( aItems != null ) {
            items = Arrays.copyOf( aItems, aItems.length );
        }
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.iterator.IIterator#hasNext()
     */
    public boolean hasNext() {
        if ( position >= items.length || items[position] == null ) {
            return false;
        }
        return true;
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.iterator.IIterator#next()
     */
    public Object next() {
        return items[position++];
    }

}
