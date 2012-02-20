package org.suggs.sandbox.test.concordionplus.usersearch;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.agileinsider.concordion.junit.ConcordionPlus;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test class to demonstrate the how to use fixtures in a more dynamic manner.
 * <p/>
 * User: suggitpe
 * Date: 10/06/11
 * Time: 18:16
 */

@RunWith(ConcordionPlus.class)
public class PartialMatchesScenario {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( PartialMatchesScenario.class );

    private Set<String> users = new HashSet<String>();

    public void setUpUser( String aUsername ) {
        users.add( aUsername );
    }

    public Iterable<String> getSearchResultsFor( String aSearchString ) {
        SortedSet<String> matches = new TreeSet<String>();
        for ( String name : users ) {
            if ( name.contains( aSearchString ) ) {
                matches.add( name );
            }
        }
        return matches;
    }
}
