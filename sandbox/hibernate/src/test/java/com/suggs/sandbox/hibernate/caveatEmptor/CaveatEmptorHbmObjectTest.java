/*
 * CaveatEmptorObjectTest.java created on 13 Apr 2007 19:33:17 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.caveatEmptor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.util.Assert;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class CaveatEmptorHbmObjectTest extends AbstractCaveatEmptorTest
{

    private static final Log LOG = LogFactory.getLog( CaveatEmptorHbmObjectTest.class );

    /**
     * Constructs a new instance.
     */
    public CaveatEmptorHbmObjectTest()
    {
        super();
    }

    /**
     * @see org.springframework.test.AbstractDependencyInjectionSpringContextTests#getConfigLocations()
     */
    protected String[] getConfigLocations()
    {
        return new String[] { "xml/ut-caveat-emptor-objects.xml" };
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
                CreditCard card = new CreditCard( "PGDS", "0123456789", Calendar.getInstance().getTime(), 99, "03", "2009" );
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
                BankAccount ba = new BankAccount( "PGDS", "0987654321", Calendar.getInstance().getTime(), "DummyBankName", "DUMMGB2LXXXX" );
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

    /**
     * This should do a bit of a debug operation on any class.
     * 
     * @param aObject
     *            object to debug
     * @return the string dumped version of the obejct
     */
    public static String getObjectAsString( Object aObject )
    {
        StringBuffer buff = new StringBuffer();
        buff.append( aObject.getClass().getSimpleName() + ":" );
        Method[] meths = aObject.getClass().getMethods();
        for ( int i = 0; i < meths.length; ++i )
        {
            if ( meths[i].getName().startsWith( "get" ) )
            {
                buff.append( meths[i].getName() ).append( "[" );
                Class c = meths[i].getReturnType();
                if ( c.equals( String.class ) || c.equals( Integer.class ) || c.equals( Date.class ) || c.equals( Long.class ) )
                {
                    try
                    {
                        buff.append( meths[i].invoke( aObject, new Object[] {} ) );
                    }
                    catch ( IllegalAccessException iae )
                    {
                        throw new IllegalStateException( "no access to method [" + meths[i].getName() + "]", iae );

                    }
                    catch ( InvocationTargetException ita )
                    {
                        throw new IllegalStateException( "no target for method [" + meths[i].getName() + "]", ita );
                    }
                }
                else
                {
                    buff.append( "???" + "{" + c.getName() + "}" );
                }
                buff.append( "] " );
            }
        }

        return buff.toString();
    }

}
