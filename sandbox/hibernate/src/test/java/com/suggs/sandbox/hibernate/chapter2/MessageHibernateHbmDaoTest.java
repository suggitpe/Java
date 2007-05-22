/*
 * MessageTest.java created on 19 Mar 2007 16:50:51 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.chapter2;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MessageHibernateHbmDaoTest extends AbstractMessageHibernateDaoTest
{

    private static final Log LOG = LogFactory.getLog( MessageHibernateHbmDaoTest.class );

    public MessageHibernateHbmDaoTest()
    {
        super();
    }

    /**
     * @see org.springframework.test.AbstractDependencyInjectionSpringContextTests#getConfigLocations()
     */
    protected String[] getConfigLocations()
    {
        return new String[] { "xml/ut-hbm-messagetest.xml" };
    }

}
