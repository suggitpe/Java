/*
 * MessageHibernateAnnotationDaoTest.java created on 22 May 2007 06:04:41 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.basicentity;

import org.springframework.test.context.ContextConfiguration;

/**
 * Message test impl that will use annotation driven testing.
 * 
 * @author suggitpe
 * @version 1.0 2 Jul 2007
 */
@ContextConfiguration(locations = { "classpath:xml/ut-annotation-messagetest.xml" })
public class MessageHibernateAnnotationDaoTest extends AbstractHibernateMessageIntegrationTest {}
