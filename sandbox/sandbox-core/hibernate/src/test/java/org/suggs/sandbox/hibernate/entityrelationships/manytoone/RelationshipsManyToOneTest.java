/*
 * RelationshipsManyToOneTest.java created on 20 Apr 2010 19:20:46 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entityrelationships.manytoone;

import org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.test.context.ContextConfiguration;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Test class for the many to one example.
 * 
 * @author suggitpe
 * @version 1.0 20 Apr 2010
 */
@ContextConfiguration(locations = { "classpath:xml/ut-relationships-manytoone.xml" })
public class RelationshipsManyToOneTest extends AbstractSimpleHibernateIntegrationTest<Long, ManyToOneEntity> {

    private static final Log LOG = LogFactory.getLog( RelationshipsManyToOneTest.class );

    private static final String WHERE_CLAUSE = "data in ('Some data', 'Updated data')";
    private static final String TEST_HQL = "from ManyToOneEntity where " + WHERE_CLAUSE;

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#cleanUpData(org.hibernate.Session)
     */
    @Override
    protected void cleanUpData( Session aSession ) {
        String otherDelete = " delete from ManyToOneOtherEntity where otherData = 'blah blah blah'";
        String parentDelete = "delete " + TEST_HQL;

        aSession.createQuery( parentDelete ).executeUpdate();
        aSession.createQuery( otherDelete ).executeUpdate();
    }

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#createEntitySearchHql()
     */
    @Override
    protected String createEntitySearchHql() {
        return TEST_HQL;
    }

    /**
     * Here we create the dependent ManToOneOtherEntity objects.
     * 
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#createDependentObjectsForTest(org.hibernate.Session)
     */
    @Override
    protected void createDependentObjectsForTest( Session aSession ) {
        LOG.debug( "Creating depdendent objects in the database" );
        ManyToOneOtherEntity other = new ManyToOneOtherEntity();
        other.setOtherData( "blah blah blah" );
        aSession.save( other );
    }

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#createEntityTemplate(java.io.Serializable,
     *      org.hibernate.Session)
     */
    @Override
    protected ManyToOneEntity createEntityTemplate( Long aKey, Session aSession ) {
        Criteria cr = aSession.createCriteria( ManyToOneOtherEntity.class );
        cr.add( Restrictions.eq( "otherData", "blah, blah, blah" ) );
        ManyToOneOtherEntity other = (ManyToOneOtherEntity) cr.uniqueResult();

        ManyToOneEntity entity = new ManyToOneEntity();
        entity.setData( "Some data" );
        entity.setOtherEntity( other );
        return entity;
    }

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#createKeyTemplate()
     */
    @Override
    protected Long createKeyTemplate() {
        // this is actually not needed for this entity.
        return null;
    }

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#updateEntityForUpdateTest(java.lang.Object)
     */
    @Override
    protected void updateEntityForUpdateTest( ManyToOneEntity aEntity ) {
        aEntity.setData( "Updated data" );
    }
}
