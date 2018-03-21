/*
 * RelationshipsManyToOneTest.java created on 20 Apr 2010 19:20:46 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entityrelationships.manytoone;

import org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.test.context.ContextConfiguration;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

@ContextConfiguration(locations = { "classpath:xml/ut-relationships-manytoone.xml" })
public class RelationshipsManyToOneTest extends AbstractSimpleHibernateIntegrationTest<Long, ManyToOneEntity> {

    private static final Logger LOG = LoggerFactory.getLogger( RelationshipsManyToOneTest.class );

    private static final String WHERE_CLAUSE = "data in ('Some data', 'Updated data')";
    private static final String TEST_HQL = "from ManyToOneEntity where " + WHERE_CLAUSE;

    @Override
    protected void cleanUpData( Session aSession ) {
        String otherDelete = " delete from ManyToOneOtherEntity where otherData = 'blah blah blah'";
        String parentDelete = "delete " + TEST_HQL;

        aSession.createQuery( parentDelete ).executeUpdate();
        aSession.createQuery( otherDelete ).executeUpdate();
    }

    @Override
    protected String createEntitySearchHql() {
        return TEST_HQL;
    }

    @Override
    protected void createDependentObjectsForTest( Session aSession ) {
        LOG.debug( "Creating depdendent objects in the database" );
        ManyToOneOtherEntity other = new ManyToOneOtherEntity();
        other.setOtherData( "blah blah blah" );
        aSession.save( other );
    }

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

    @Override
    protected Long createKeyTemplate() {
        // this is actually not needed for this entity.
        return null;
    }

    @Override
    protected void updateEntityForUpdateTest( ManyToOneEntity aEntity ) {
        aEntity.setData( "Updated data" );
    }
}
