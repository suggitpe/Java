/*
 * AbstractCaveatEmptorRelationshipTest.java created on 18 May 2007 19:06:30 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.caveatEmptor;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.util.Assert;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * TODO Write javadoc for AbstractCaveatEmptorRelationshipTest
 * 
 * @author suggitpe
 * @version 1.0 2 Jul 2007
 */
public abstract class AbstractCaveatEmptorRelationshipTest extends AbstractCaveatEmptorTest
{

    private static final Log LOG = LogFactory.getLog( AbstractCaveatEmptorRelationshipTest.class );

    /**
     * Constructs a new instance.
     */
    public AbstractCaveatEmptorRelationshipTest()
    {
        super();
    }

    /**
     * Test the internal relationship between categories
     */
    public void testCreateNewNestedCategory()
    {
        runTest( new TestCallback()
        {

            public Class[] getClassesForCleaning()
            {
                return new Class[] { Category.class };
            }

            public void preTestSetup( Session aSession )
            {
                Category parent = new Category( "Parent category" );
                aSession.save( parent );
            }

            public String getTestName()
            {
                return "createNewNestedCategory";
            }

            public void runTest( Session aSession )
            {

                Criteria c = aSession.createCriteria( Category.class );
                c.add( Restrictions.like( "name", "Parent%" ) );

                List l = c.list();
                LOG.debug( "Category objects in the database number [" + l.size() + "]" );

                Assert.isTrue( l.size() == 1, "Did not find a Parent Category object in the database" );
                Category parent = (Category) l.get( 0 );

                Category child = new Category( "Child category" );
                aSession.save( child );
                parent.addChildCategory( child );
                child.setParentCategory( parent );
            }

        } );
    }

    /**
     * Test the relationship between the user and billing
     */
    public void testCreateNewUserBillingRel()
    {
        runTest( new TestCallback()
        {

            public Class[] getClassesForCleaning()
            {
                return new Class[] { User.class, BillingDetails.class };
            }

            public String getTestName()
            {
                return "testCreateNewUserBillingRel";
            }

            public void preTestSetup( Session aSession )
            {
                User u = createTestUser( "peter", "suggitt" );
                aSession.save( u );
            }

            public void runTest( Session aSession )
            {
                Criteria c = aSession.createCriteria( User.class );
                c.add( Restrictions.eq( "username", "suggittpe" ) );
                List l = c.list();

                LOG.debug( "Have retrieved [" + l.size() + "] users from the database" );

                if ( l.size() != 1 )
                {
                    fail( "Failed to retrieve just one user fromthe database" );
                }

                User u = (User) l.get( 0 );

                CreditCard cc = new CreditCard( "Mr P G D Suggitt",
                                                "aNumber for suggitt",
                                                Calendar.getInstance().getTime(),
                                                null,
                                                1,
                                                "January",
                                                "2009" );

                u.addBillingDetails( cc );
            }
        } );
    }
}
