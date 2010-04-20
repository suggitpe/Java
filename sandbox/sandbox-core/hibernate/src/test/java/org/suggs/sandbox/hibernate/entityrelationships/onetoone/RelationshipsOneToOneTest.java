/*
 * RelationshipsOneToOneTest.java created on 20 Apr 2010 08:08:08 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entityrelationships.onetoone;

import org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.context.ContextConfiguration;

import org.hibernate.Session;

/**
 * Test class for the one to one relationship example.
 * 
 * @author suggitpe
 * @version 1.0 20 Apr 2010
 */
@ContextConfiguration(locations = { "classpath:xml/ut-annotation-relationships-onetoone.xml" })
public class RelationshipsOneToOneTest extends AbstractSimpleHibernateIntegrationTest<Long, OneToOneEntity> {

    private static final String WHERE_CLAUSE = "data in ('Some data', 'Other data')";
    private static final String TEST_HQL = "from OneToOneEntity where " + WHERE_CLAUSE;

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#cleanUpData(org.hibernate.Session)
     */
    @Override
    protected void cleanUpData( Session aSession ) {

        String otherDelete = " delete from OneToOneOtherEntity o where exists (select 1 from OneToOneEntity e where o.id = e.otherEntity.id and e."
                             + WHERE_CLAUSE + " )";
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
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#createEntityTemplate(java.io.Serializable)
     */
    @Override
    protected OneToOneEntity createEntityTemplate( Long aKey ) {

        OneToOneOtherEntity other = new OneToOneOtherEntity();
        other.setOtherData( "Other data" );

        OneToOneEntity entity = new OneToOneEntity();
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
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#getEntityListForSchemaCreation()
     */
    @Override
    protected List<Class<?>> getEntityListForSchemaCreation() {
        List<Class<?>> entityClassses = new ArrayList<Class<?>>();
        entityClassses.add( OneToOneEntity.class );
        entityClassses.add( OneToOneOtherEntity.class );
        return entityClassses;
    }

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#updateEntityForUpdateTest(java.lang.Object)
     */
    @Override
    protected void updateEntityForUpdateTest( OneToOneEntity aEntity ) {
        aEntity.setData( "Updated data" );
    }

}
