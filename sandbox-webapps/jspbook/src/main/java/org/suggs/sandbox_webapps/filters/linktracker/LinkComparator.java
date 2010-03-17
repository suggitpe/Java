/*
 * 
 * LinkComparator.java created on 23 Nov 2007 07:22:49 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.filters.linktracker;

import java.util.Comparator;

/**
 * TODO: make this class a generic class with type safe template
 * params. A comparator object for use in sorting a collection of Link
 * objects.
 * 
 * @author suggitpe
 * @version 1.0 23 Nov 2007
 */
public class LinkComparator implements Comparator
{

    /**
     * @see java.util.Comparator#compare(java.lang.Object,
     *      java.lang.Object)
     */
    public int compare( Object lhs, Object rhs )
    {
        Link l = (Link) lhs;
        Link r = (Link) rhs;
        return r.getCount() - l.getCount();
    }

    /**
     * Equals method between two link objects
     * 
     * @param lhs
     *            the left hand object
     * @param rhs
     *            the right hand object
     * @return true if they both have the
     */
    public boolean equals( Object lhs, Object rhs )
    {
        Link l = (Link) lhs;
        Link r = (Link) rhs;
        if ( l.getCount() == r.getCount() )
        {
            return true;
        }
        return false;
    }

}
