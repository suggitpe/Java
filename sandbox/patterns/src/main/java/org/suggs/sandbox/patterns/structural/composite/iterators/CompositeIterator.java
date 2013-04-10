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
 */
public class CompositeIterator implements Iterator<Object> {

    private Stack<Iterator<?>> stack = new Stack<Iterator<?>>();

    public CompositeIterator(Iterator<?> aIter) {
        stack.push(aIter);
    }

    @Override
    public boolean hasNext() {
        if (stack.empty()) {
            return false;
        }
        Iterator<?> i = stack.peek();
        if (!i.hasNext()) {
            stack.pop();
            return hasNext();

        }
        return true;
    }

    @Override
    public Object next() {
        if (hasNext()) {
            Iterator<?> iter = stack.peek();
            IMenuComponent comp = (IMenuComponent) iter.next();
            if (comp instanceof MenuComponent) {
                stack.push(comp.createIterator());
            }
            return comp;
        }
        return null;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
