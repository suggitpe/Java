/*
 * IIterator.java created on 6 Sep 2007 07:24:28 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.iterator;

/**
 * Interface for an iterator pattern
 * 
 * @author suggitpe
 * @version 1.0 6 Sep 2007
 */
public interface IIterator
{

    /**
     * Test to see if there are any more items in the collection
     * 
     * @return true if there are more items, else false
     */
    boolean hasNext();

    /**
     * Get the next item in the collection
     * 
     * @return the next item in the collection, else null if no more
     */
    Object next();

}
