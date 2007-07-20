/*
 * MessageTest.java created on 19 Mar 2007 16:50:51 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.chapter2;

/**
 * TODO Write javadoc for MessageHibernateHbmDaoTest
 * 
 * @author suggitpe
 * @version 1.0 2 Jul 2007
 */
public class MessageHibernateHbmDaoTest extends AbstractMessageHibernateDaoTest
{

    /**
     * Constructs a new instance.
     */
    public MessageHibernateHbmDaoTest()
    {
        super();
    }

    /**
     * @see org.springframework.test.AbstractDependencyInjectionSpringContextTests#getConfigLocations()
     */
    @Override
    protected String[] getConfigLocations()
    {
        return new String[] { "xml/ut-hbm-messagetest.xml" };
    }

}
