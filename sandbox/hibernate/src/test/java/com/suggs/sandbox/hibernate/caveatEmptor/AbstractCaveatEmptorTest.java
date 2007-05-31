/*
 * AbstractCaveatEmptorTest.java created on 14 May 2007 06:16:25 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.caveatEmptor;

import com.suggs.sandbox.hibernate.support.AbstractHibernateSpringTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractCaveatEmptorTest extends AbstractHibernateSpringTest
{

    private static final Log LOG = LogFactory.getLog( AbstractCaveatEmptorTest.class );

    /**
     * This should do a bit of a debug operation on any class.
     * 
     * @param aObject
     *            object to debug
     * @return the string dumped version of the obejct
     */
    protected static String getObjectAsString( Object aObject )
    {
        StringBuffer buff = new StringBuffer();
        buff.append( aObject.getClass().getSimpleName() + ":" );
        Method[] meths = aObject.getClass().getMethods();
        for ( Method m : meths )
        {
            if ( m.getName().startsWith( "get" ) )
            {
                buff.append( m.getName() ).append( "[" );
                Class c = m.getReturnType();
                if ( c.equals( String.class ) || c.equals( Integer.class ) || c.equals( Date.class ) || c.equals( Long.class ) )
                {
                    try
                    {
                        buff.append( m.invoke( aObject, new Object[] {} ) );
                    }
                    catch ( IllegalAccessException iae )
                    {
                        throw new IllegalStateException( "no access to method [" + m.getName() + "]", iae );

                    }
                    catch ( InvocationTargetException ita )
                    {
                        throw new IllegalStateException( "no target for method [" + m.getName() + "]", ita );
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
