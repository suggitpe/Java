/*
 * CaveatEmptorObjectTest.java created on 13 Apr 2007 19:33:17 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.caveatEmptor;

import com.suggs.sandbox.hibernate.support.AbstractHibernateSpringTest;

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
                

            }
        } );
    }

}
