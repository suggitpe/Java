/*
 * MessageTest.java created on 19 Mar 2007 16:50:51 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.basicentity;

import org.springframework.test.context.ContextConfiguration;

/**
 * Message test impl that will use HBM driven testing.
 * 
 * @author suggitpe
 * @version 1.0 2 Jul 2007
 */
@ContextConfiguration(locations = { "classpath:xml/ut-messagetest-hbm.xml" })
public class MessageHibernateHbmDaoTest extends AbstractHibernateMessageIntegrationTest {}
