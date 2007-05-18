/*
 * AbstractCaveatEmptorTest.java created on 14 May 2007 06:16:25 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.caveatEmptor;

import com.suggs.sandbox.hibernate.support.AbstractHibernateSpringTest;

import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractCaveatEmptorTest extends AbstractHibernateSpringTest
{

    private static final Log LOG = LogFactory.getLog( AbstractCaveatEmptorTest.class );

    /**
     * Helper method for the tests that will create a new User
     * 
     * @return a new User
     */
    protected User createTestUser( String aFirst, String aLast )
    {
        LOG.debug( "Creating a test user" );
        Address home = new Address( "dummyHomeStreet", "dummyHomeCity", "dummyHomeZip" );
        Address billing = new Address( "dummyStreet", "dummyCity", "dummyZip" );
        User ret = new User( aFirst,
                             aLast,
                             ( aLast + aFirst.substring( 0, 2 ) ),
                             "passowrd",
                             ( aFirst + "@" + aLast + ".com" ),
                             home,
                             billing );
        return ret;
    }

    /**
     * Helper method to create a new item for testing
     * 
     * @param aName
     *            the name of the catgeory
     * @return the new category
     */
    protected Item createTestItem( String aName )
    {
        return new Item( aName, "dummyDesc", 1.0, 3.0, Calendar.getInstance().getTime(), Calendar.getInstance().getTime() );
    }

    /**
     * Create a new category for testing
     * 
     * @param aName
     *            the name of the category
     * @return the new test category
     */
    protected Category createTestCategory( String aName )
    {
        return new Category( aName );
    }
}
