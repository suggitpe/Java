/*
 * NullIterator.java created on 10 Sep 2007 07:11:56 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.composite.iterators;

import java.util.Iterator;

/**
 * Null implementation of the iterator class
 */
public class NullIterator implements Iterator<Object> {

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Object next() {
        return null;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
