/*
 * CaveatEmptorObjectTest.java created on 13 Apr 2007 19:33:17 by suggitpe for project SandBox - Hibernate
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

import org.hibernate.Session;

public class CaveatEmptorObjectTest extends AbstractHibernateSpringTest
{

    private static final Log LOG = LogFactory.getLog( CaveatEmptorObjectTest.class );

    /**
     * Constructs a new instance.
     */
    public CaveatEmptorObjectTest()
    {
        super();
    }

    /**
     * @see com.suggs.sandbox.hibernate.common.AbstractHibernateSpringTest#doAfterPropertiesSet()
     */
    protected void doAfterPropertiesSet() throws Exception
    {
    }

    /**
     * @see com.suggs.sandbox.hibernate.common.AbstractHibernateSpringTest#getHibernateMapFilenames()
     */
    protected String[] getHibernateMapFilenames()
    {
        return new String[] { "hbm/manual/billing-details.hbm.xml", "hbm/manual/credit-card.hbm.xml", "hbm/manual/bank-account.hbm.xml",
                             "hbm/manual/bid.hbm.xml", "hbm/manual/category.hbm.xml", "hbm/manual/comment.hbm.xml",
                             "hbm/manual/item.hbm.xml", "hbm/manual/user.hbm.xml" };
    }

    /**
     * @see com.suggs.sandbox.hibernate.common.AbstractHibernateSpringTest#doCleanUpOldData()
     */
    protected void doCleanUpOldData()
    {
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

            public String getTestName()
            {
                return "testCreateNewCreditCard";
            }

            public void runTest( Session aSession )
            {
                LOG.debug( "Creating a new credit card" );
                CreditCard card = new CreditCard( "PGDS", "0123456789", Calendar.getInstance().getTime(), 99, "03", "2009" );
                // LOG.debug( "Creditcard methods are: " +
                // getObjectAsString( card ) );
                aSession.save( card );
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
