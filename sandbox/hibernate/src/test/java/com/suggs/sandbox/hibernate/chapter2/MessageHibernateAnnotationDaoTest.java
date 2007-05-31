/*
 * MessageHibernateAnnotationDaoTest.java created on 22 May 2007 06:04:41 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.chapter2;

public class MessageHibernateAnnotationDaoTest extends AbstractMessageHibernateDaoTest
{

    public MessageHibernateAnnotationDaoTest()
    {
        super();
    }

    /**
     * @see org.springframework.test.AbstractSingleSpringContextTests#getConfigLocations()
     */
    protected String[] getConfigLocations()
    {
        return new String[] { "xml/ut-annotation-messagetest.xml" };
    }
}
