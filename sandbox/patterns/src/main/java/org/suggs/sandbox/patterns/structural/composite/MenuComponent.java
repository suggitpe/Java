/*
 * MenuComponent.java created on 7 Sep 2007 16:58:03 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.composite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.suggs.sandbox.patterns.structural.composite.iterators.CompositeIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The menu composite class.
 */
public class MenuComponent extends AbstractMenuComponent {

    private static final Logger LOG = LoggerFactory.getLogger(MenuComponent.class);
    private List<IMenuComponent> menuComps = new ArrayList<IMenuComponent>();
    private String name;
    private String description;

    public MenuComponent(String aName, String aDescripton) {
        name = aName;
        description = aDescripton;
    }

    @Override
    public void add(IMenuComponent component) {
        menuComps.add(component);
    }

    @Override
    public void remove(IMenuComponent component) {
        menuComps.remove(component);
    }

    @Override
    public IMenuComponent getChild(int childIndex) {
        return menuComps.get(childIndex);
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void print() {
        LOG.debug("\n" + getName() + ", " + getDescription());
        LOG.debug("-----------------------");

        // This is a small bit of recursion (if the object in the iter
        // is a MenuComponent rather that a MenuItem).
        Iterator<?> iter = menuComps.iterator();
        while (iter.hasNext()) {
            ((IMenuComponent) iter.next()).print();
        }
    }

    @Override
    public Iterator<?> createIterator() {
        return new CompositeIterator(menuComps.iterator());
    }

}
