/*
 * AbstractCaveatEmptorObjectTest.java created on 18 May 2007 18:59:42 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.caveatEmptor;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.util.Assert;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Test object
 * 
 * @author suggitpe
 * @version 1.0 2 Jul 2007
 */
public abstract class AbstractCaveatEmptorObjectTest extends AbstractCaveatEmptorTest
{

    private static final Log LOG = LogFactory.getLog( AbstractCaveatEmptorObjectTest.class );

    /**
     * Constructs a new instance.
     */
    public AbstractCaveatEmptorObjectTest()
    {
        super();
    }

    /**
     * Test the credit crd object
     */
    public void testCreateNewCreditCard()
    {
        runTest( new TestCallback()
        {

            public Class[] getClassesForCleaning()
            {
                return new Class[] { CreditCard.class, BillingDetails.class, User.class };
            }

            public void preTestSetup( Session aSession )
            {
                User u = createTestUser( "foo", "bar" );
                aSession.save( u );
                aSession.flush();
            }

            public String getTestName()
            {
                return "testCreateNewCreditCard";
            }

            public void runTest( Session aSession )
            {
                Criteria crit = aSession.createCriteria( User.class );
                crit.add( Restrictions.eq( "username", "barfo" ) );
                List l = crit.list();

                Assert.isTrue( l.size() == 1, "Found zero or more then one User with the username [barfo]" );

                User u = (User) l.get( 0 );

                LOG.debug( "Creating a new credit card" );
                CreditCard card = new CreditCard( "PGDS",
                                                  "0123456789",
                                                  Calendar.getInstance().getTime(),
                                                  null,
                                                  new Integer( 99 ),
                                                  "03",
                                                  "2009" );
                u.addBillingDetails( card );
                aSession.save( card );
            }

        } );
    }

    /**
     * Test the bank account object
     */
    public void testCreateNewBankAccount()
    {
        runTest( new TestCallback()
        {

            public Class[] getClassesForCleaning()
            {
                return new Class[] { BankAccount.class, BillingDetails.class, User.class };
            }

            public void preTestSetup( Session aSession )
            {
                User u = createTestUser( "foo", "bar" );
                aSession.save( u );
                aSession.flush();
            }

            public String getTestName()
            {
                return "testCreateNewBankAccounT";
            }

            public void runTest( Session aSession )
            {
                Criteria crit = aSession.createCriteria( User.class );
                crit.add( Restrictions.eq( "username", "barfo" ) );
                List l = crit.list();

                Assert.isTrue( l.size() == 1, "Found zero or more then one User with the username [barfo]" );

                User u = (User) l.get( 0 );
                LOG.debug( "Creating a new Bank Account" );
                BankAccount ba = new BankAccount( "PGDS",
                                                  "0987654321",
                                                  Calendar.getInstance().getTime(),
                                                  null,
                                                  "DummyBankName",
                                                  "DUMMGB2LXXXX" );
                u.addBillingDetails( ba );

                aSession.save( ba );
            }

        } );

    }

    /**
     * Test a new category object
     */
    public void testCreateNewCategory()
    {
        runTest( new TestCallback()
        {

            public Class[] getClassesForCleaning()
            {
                return new Class[] { Category.class };
            }

            public void preTestSetup( Session aSession )
            {
            }

            public String getTestName()
            {
                return "testCreateNewCategory";
            }

            public void runTest( Session aSession )
            {
                LOG.debug( "Creating a new Category" );
                Category c = new Category( "dummy category name" );
                aSession.save( c );

            }

        } );
    }

    /**
     * Test the creation of a new User
     */
    public void testCreateNewUser()
    {
        runTest( new TestCallback()
        {

            public Class[] getClassesForCleaning()
            {
                return new Class[] { User.class };
            }

            public void preTestSetup( Session aSession )
            {
            }

            public String getTestName()
            {
                return "testCreateNewUser";
            }

            public void runTest( Session aSession )
            {
                LOG.debug( "Creating a new user" );
                Address home = new Address( "high street north", "leighton buzzard", "LU7 0EX" );
                Address bill = new Address( "100 liverpool street", "london", "EC2M 2RH" );
                User u = new User( "Peter", "Suggitt", "suggitpe", "welcome", "me@suggs.org.uk", home, bill );
                aSession.save( u );
            }

        } );
    }

    /**
     * Test for a new item
     */
    public void testNewItem()
    {
        runTest( new TestCallback()
        {

            public Class[] getClassesForCleaning()
            {
                return new Class[] { Item.class, Category.class, User.class };
            }

            public String getTestName()
            {
                return "testNewItem";
            }

            public void preTestSetup( Session aSession )
            {
                Category c = createTestCategory( "ItemTestCat" );
                aSession.save( c );
                User u = createTestUser( "peter", "suggitt" );
                aSession.save( u );
                aSession.flush();
            }

            public void runTest( Session aSession )
            {
                Criteria crit = aSession.createCriteria( Category.class );
                Category c = (Category) crit.uniqueResult();
                Criteria usr = aSession.createCriteria( User.class );
                User u = (User) usr.uniqueResult();

                LOG.debug( "Creating a new Item" );
                Item i = createTestItem( "testItem_1000" );
                i.addCategory( c );
                i.setSeller( u );

                aSession.save( i );
            }
        } );
    }

}
