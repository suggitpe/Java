/*
 * RelationshipsOneToManyUnidirectional.java created on 21 Apr 2010 19:03:41 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entityrelationships.onetomany;

import org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.test.context.ContextConfiguration;

import org.hibernate.Session;


@ContextConfiguration(locations = { "classpath:xml/ut-relationships-onetomany-bidirectional.xml" })
public class RelationshipsOneToManyBidirectional extends AbstractSimpleHibernateIntegrationTest<Long, OneToManyBidirectionalEntity> {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( RelationshipsOneToManyBidirectional.class );

    private static final String WHERE_CLAUSE = "data in ('Some data', 'Updated data')";
    private static final String TEST_HQL = "from OneToManyBidirectionalEntity where " + WHERE_CLAUSE;


    @Ignore
    @Test
    @Override
    public void basicCreateOperationCreatesCorrectObject() {}

    @Override
    protected void cleanUpData( Session aSession ) {
        String otherDelete = " delete from OneToManyBidirectionalOtherEntity o where id in (select id from OneToManyBidirectionalEntity where "
                             + WHERE_CLAUSE + " )";
        String entityDelete = "delete " + TEST_HQL;

        aSession.createQuery( otherDelete ).executeUpdate();
        aSession.createQuery( entityDelete ).executeUpdate();
    }

    @Override
    protected String createEntitySearchHql() {
        return TEST_HQL;
    }

    @Override
    protected OneToManyBidirectionalEntity createEntityTemplate( Long aKey, Session aSession ) {
        OneToManyBidirectionalOtherEntity other = new OneToManyBidirectionalOtherEntity();
        other.setOtherData( "loopy loooooo" );

        OneToManyBidirectionalEntity entity = new OneToManyBidirectionalEntity();
        entity.setData( "Some data" );
        entity.addChild( other );
        return entity;
    }

    @Override
    protected Long createKeyTemplate() {
        return null;
    }

    @Override
    protected void updateEntityForUpdateTest( OneToManyBidirectionalEntity aEntity ) {
        aEntity.setData( "Updated data" );
    }
}
