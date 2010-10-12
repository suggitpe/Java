/*
 * AbstractCompositeEntityTest.java created on 22 Sep 2010 19:53:31 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.compositeentity;

import org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.Session;

/**
 * Abstract test that allows us to test the use of a composite entity with Hibernate.
 * 
 * @author suggitpe
 * @version 1.0 22 Sep 2010
 */
public abstract class AbstractCompositeEntityTest extends AbstractSimpleHibernateIntegrationTest<Long, CoreEntity> {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( AbstractCompositeEntityTest.class );

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#cleanUpData(org.hibernate.Session)
     */
    @Override
    protected void cleanUpData( Session aSession ) {
        aSession.createQuery( "delete from CoreEntity" ).executeUpdate();
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
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#createEntityTemplate(java.io.Serializable,
     *      org.hibernate.Session)
     */
    @Override
    protected CoreEntity createEntityTemplate( Long aKey, Session aSession ) {
        return new CoreEntity( "This is some text", new CompositeEntity( "comp text", Integer.valueOf( 3 ) ) );
    }

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#updateEntityForUpdateTest(java.lang.Object)
     */
    @Override
    protected void updateEntityForUpdateTest( CoreEntity aEntity ) {
        aEntity.setSomeText( "This is some new text" );
    }

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#createEntitySearchHql()
     */
    @Override
    protected String createEntitySearchHql() {
        return "from CoreEntity";
    }
}
