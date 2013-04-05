/*
 * RelationshipsOneToOneTest.java created on 20 Apr 2010 08:08:08 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entityrelationships.onetoone;

import org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest;

import org.springframework.test.context.ContextConfiguration;

import org.hibernate.Session;

@ContextConfiguration(locations = { "classpath:xml/ut-relationships-onetoone.xml" })
public class RelationshipsOneToOneTest extends AbstractSimpleHibernateIntegrationTest<Long, OneToOneEntity> {

    private static final String WHERE_CLAUSE = "data in ('Some data', 'Updated data')";
    private static final String TEST_HQL = "from OneToOneEntity where " + WHERE_CLAUSE;

    @Override
    protected void cleanUpData( Session aSession ) {

        String otherDelete = " delete from OneToOneOtherEntity o where exists (select 1 from OneToOneEntity e where o.id = e.otherEntity.id and e."
                             + WHERE_CLAUSE + " )";
        String parentDelete = "delete " + TEST_HQL;

        aSession.createQuery( parentDelete ).executeUpdate();
        aSession.createQuery( otherDelete ).executeUpdate();
    }

    @Override
    protected String createEntitySearchHql() {
        return TEST_HQL;
    }

    @Override
    protected OneToOneEntity createEntityTemplate( Long aKey, Session aSession ) {

        OneToOneOtherEntity other = new OneToOneOtherEntity();
        other.setOtherData( "Other data" );

        OneToOneEntity entity = new OneToOneEntity();
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
    protected void updateEntityForUpdateTest( OneToOneEntity aEntity ) {
        aEntity.setData( "Updated data" );
    }

}
