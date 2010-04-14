/*
 * AbstractHibernateMessageIntegrationTest.java created on 14 Apr 2010 06:59:45 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.basicentity;

import org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Standard test for the Message entity.
 * 
 * @author suggitpe
 * @version 1.0 14 Apr 2010
 */
public abstract class AbstractHibernateMessageIntegrationTest extends AbstractSimpleHibernateIntegrationTest<Long, Message> {

    private static final Log LOG = LogFactory.getLog( AbstractHibernateMessageIntegrationTest.class );
}
