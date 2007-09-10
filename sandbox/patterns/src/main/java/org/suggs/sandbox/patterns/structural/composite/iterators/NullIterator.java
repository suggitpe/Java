/*
 * NullIterator.java created on 10 Sep 2007 07:11:56 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.composite.iterators;

import java.util.Iterator;

/**
 * Null implementation of the iterator class
 * 
 * @author suggitpe
 * @version 1.0 10 Sep 2007
 */
public class NullIterator implements Iterator
{

    /**
     * @see java.util.Iterator#hasNext()
     */
    public boolean hasNext()
    {
        return false;
    }

    /**
     * @see java.util.Iterator#next()
     */
    public Object next()
    {
        return null;
    }

    /**
     * @see java.util.Iterator#remove()
     */
    public void remove()
    {
        throw new UnsupportedOperationException();
    }

}
