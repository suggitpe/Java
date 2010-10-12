/*
 * AbstractHibernateMessageIntegrationTest.java created on 14 Apr 2010 06:59:45 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.basicentity;

import org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest;

import org.hibernate.Session;

/**
 * Standard test for the Message entity.
 * 
 * @author suggitpe
 * @version 1.0 14 Apr 2010
 */
public abstract class AbstractHibernateMessageIntegrationTest extends AbstractSimpleHibernateIntegrationTest<Long, Message> {

    // private static final Log LOG = LogFactory.getLog( AbstractHibernateMessageIntegrationTest.class );

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#cleanUpData(org.hibernate.Session)
     */
    @Override
    protected void cleanUpData( Session aSession ) {
        aSession.createQuery( "delete from Message" ).executeUpdate();
    }

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#createEntitySearchHql()
     */
    @Override
    protected String createEntitySearchHql() {
        return "from Message";
    }

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#createEntityTemplate(java.io.Serializable)
     */
    @Override
    protected Message createEntityTemplate( Long aKey, Session aSession ) {
        return new Message( "This is a test message" );
    }

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#createKeyTemplate()
     */
    @Override
    protected Long createKeyTemplate() {
        // leaving as null means that we are using sequences. Realy this could b e done better as it is
        // confusing.
        return null;
    }

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#updateEntityForUpdateTest(java.lang.Object)
     */
    @Override
    protected void updateEntityForUpdateTest( Message aEntity ) {
        aEntity.setText( "I have been updated" );
    }
}
