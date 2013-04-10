/*
 * Waitress.java created on 7 Sep 2007 17:09:06 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.composite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

/**
 * The client in the relationship.
 */
public class Waitress {

    private static final Logger LOG = LoggerFactory.getLogger(Waitress.class);
    private IMenuComponent allMenus;

    public Waitress(IMenuComponent aComponent) {
        allMenus = aComponent;
    }

    public void printMenu() {
        allMenus.print();
    }

    public void printVegetarianMenu() {
        Iterator<?> iter = allMenus.createIterator();
        LOG.debug("\nVEGETARIAN MENU\n-----------------");
        while (iter.hasNext()) {
            IMenuComponent comp = (IMenuComponent) iter.next();
            try {
                if (comp.isVegetarian()) {
                    comp.print();
                }
            } catch (UnsupportedOperationException e) {
            }
        }

    }
}
