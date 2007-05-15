/*
 * CaveatEmptorRelationshipTest.java created on 11 May 2007 18:55:00 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.caveatEmptor;

import com.suggs.sandbox.hibernate.support.AbstractHibernateSpringTest;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.util.Assert;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;

public class CaveatEmptorHbmRelationshipTest extends AbstractCaveatEmptorTest
{

    private static final Log LOG = LogFactory.getLog( CaveatEmptorHbmRelationshipTest.class );

    /**
     * Constructs a new instance.
     */
    public CaveatEmptorHbmRelationshipTest()
    {
        super();
    }

    /**
     * @see com.suggs.sandbox.hibernate.support.AbstractHibernateSpringTest#getHibernateMapFilenames()
     */
    protected String[] getHibernateMapFilenames()
    {
        return new String[] { "hbm/manual/billing-details.hbm.xml", "hbm/manual/credit-card.hbm.xml", "hbm/manual/bank-account.hbm.xml",
                             "hbm/manual/bid.hbm.xml", "hbm/manual/category.hbm.xml", "hbm/manual/comment.hbm.xml",
                             "hbm/manual/item.hbm.xml", "hbm/manual/user.hbm.xml" };
    }

    /**
     * @see org.springframework.test.AbstractSingleSpringContextTests#getConfigLocations()
     */
    protected String[] getConfigLocations()
    {
        return new String[] { "xml/ut-caveat-emptor-relationships.xml" };
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
                Session s = getSessionFactory().openSession();
                Transaction t = s.beginTransaction();

                Category parent = new Category( "Parent category" );
                s.save( parent );

                t.commit();
                s.close();
            }

            public String getTestName()
            {
                return "createNewNestedCategory";
            }

            public void runTest( Session aSession )
            {

                Criteria c = aSession.createCriteria( Category.class );
                c.add( Expression.like( "name", "Parent%" ) );

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
                return new Class[] {};
            }

            public String getTestName()
            {
                return "testCreateNewUserBillingRel";
            }

            public void preTestSetup( Session aSession )
            {
            }

            public void runTest( Session aSession )
            {
                Criteria c = aSession.createCriteria( User.class );
                c.add( Expression.eq( "username", "suggitpe" ) );
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
                                                1,
                                                "January",
                                                "2009" );

                u.addBillingDetails( cc );
            }
        } );
    }

}
