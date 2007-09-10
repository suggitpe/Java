/*
 * Waitress.java created on 7 Sep 2007 17:09:06 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.composite;

import java.util.Iterator;

/**
 * The client in the relationship.
 * 
 * @author suggitpe
 * @version 1.0 7 Sep 2007
 */
public class Waitress
{

    private IMenuComponent mAllMenus_;

    /**
     * Constructs a new instance.
     * 
     * @param aComponent
     *            the menu component
     */
    public Waitress( IMenuComponent aComponent )
    {
        mAllMenus_ = aComponent;
    }

    /**
     * Print the content of all the menus
     */
    public void printMenu()
    {
        mAllMenus_.print();
    }

    /**
     * Using the composite iterator, print out the vegetarian menu.
     */
    public void printVegetarianMenu()
    {
        Iterator iter = mAllMenus_.createIterator();
        System.out.println( "\nVEGETARIAN MENU\n-----------------" );
        while ( iter.hasNext() )
        {
            IMenuComponent comp = (IMenuComponent) iter.next();
            try
            {
                if ( comp.isVegetarian() )
                {
                    comp.print();
                }
            }
            catch ( UnsupportedOperationException e )
            {
                // nadda
            }
        }

    }
}
