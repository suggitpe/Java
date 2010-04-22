/*
 * RelationshipsOneToManyUnidirectional.java created on 21 Apr 2010 19:03:41 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entityrelationships.onetomany;

import org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.test.context.ContextConfiguration;

import org.hibernate.Session;

/**
 * Test class for the one to many unidirectional example.
 * 
 * @author suggitpe
 * @version 1.0 21 Apr 2010
 */
@ContextConfiguration(locations = { "classpath:xml/ut-relationships-onetomany-unidirectional.xml" })
public class RelationshipsOneToManyUnidirectional extends AbstractSimpleHibernateIntegrationTest<Long, OneToManyUnidirectionalEntity> {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( RelationshipsOneToManyUnidirectional.class );

    private static final String WHERE_CLAUSE = "data in ('Some data', 'Updated data')";
    private static final String TEST_HQL = "from OneToManyUnidirectionalEntity where " + WHERE_CLAUSE;

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#cleanUpData(org.hibernate.Session)
     */
    @Override
    protected void cleanUpData( Session aSession ) {
        String otherDelete = " delete from OneToManyUnidirectionalOtherEntity o where id in (select id from OneToManyUnidirectionalEntity where "
                             + WHERE_CLAUSE + " )";
        String entityDelete = "delete " + TEST_HQL;

        aSession.createQuery( otherDelete ).executeUpdate();
        aSession.createQuery( entityDelete ).executeUpdate();
    }

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#createEntitySearchHql()
     */
    @Override
    protected String createEntitySearchHql() {
        return TEST_HQL;
    }

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#createEntityTemplate(java.io.Serializable,
     *      org.hibernate.Session)
     */
    @Override
    protected OneToManyUnidirectionalEntity createEntityTemplate( Long aKey, Session aSession ) {
        OneToManyUnidirectionalOtherEntity other = new OneToManyUnidirectionalOtherEntity();
        other.setOtherData( "loopy loooooo" );

        OneToManyUnidirectionalEntity entity = new OneToManyUnidirectionalEntity();
        entity.setData( "Some data" );
        entity.addChild( other );
        return entity;
    }

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#createKeyTemplate()
     */
    @Override
    protected Long createKeyTemplate() {
        return null;
    }

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#getEntityListForSchemaCreation()
     */
    @Override
    protected List<Class<?>> getEntityListForSchemaCreation() {
        List<Class<?>> entityClassses = new ArrayList<Class<?>>();
        entityClassses.add( OneToManyUnidirectionalEntity.class );
        entityClassses.add( OneToManyUnidirectionalOtherEntity.class );
        return entityClassses;
    }

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#updateEntityForUpdateTest(java.lang.Object)
     */
    @Override
    protected void updateEntityForUpdateTest( OneToManyUnidirectionalEntity aEntity ) {
        aEntity.setData( "Updated data" );
    }
}
