/*
 * RelationshipsManyToManyUnidirectional.java created on 22 Apr 2010 19:12:59 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.entityrelationships.manytomany;

import org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.test.context.ContextConfiguration;

import org.hibernate.Session;

/**
 * Test class for the many to many unidirectional example.
 * 
 * @author suggitpe
 * @version 1.0 22 Apr 2010
 */
@ContextConfiguration(locations = { "classpath:xml/ut-relationships-manytomany-unidirectional.xml" })
public class RelationshipsManyToManyUnidirectional extends AbstractSimpleHibernateIntegrationTest<Long, ManyToManyUnidirectionalEntity> {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( RelationshipsManyToManyUnidirectional.class );

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#cleanUpData(org.hibernate.Session)
     */
    @Override
    protected void cleanUpData( Session aSession ) {
        String cleanEntity = "delete from ManyToManyUnidirectionalEntity";
        String cleanOther = "delete from ManyToManyUnidirectionalEntity";
        String cleanJoin = "delete from MANYTOMANY_UNI_JOIN";

        aSession.createSQLQuery( cleanJoin ).executeUpdate();
        aSession.createQuery( cleanOther ).executeUpdate();
        aSession.createQuery( cleanEntity ).executeUpdate();
    }

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#createEntitySearchHql()
     */
    @Override
    protected String createEntitySearchHql() {
        return "from ManyToManyUnidirectionalEntity where data in ('Some data','Updated data')";
    }

    /**
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#createEntityTemplate(java.io.Serializable,
     *      org.hibernate.Session)
     */
    @Override
    protected ManyToManyUnidirectionalEntity createEntityTemplate( Long aKey, Session aSession ) {

        ManyToManyUnidirectionalOtherEntity other1 = new ManyToManyUnidirectionalOtherEntity();
        other1.setOtherData( "loo loo poo poo" );
        ManyToManyUnidirectionalOtherEntity other2 = new ManyToManyUnidirectionalOtherEntity();
        other2.setOtherData( "loo loo wee wee" );

        aSession.save( other1 );
        aSession.save( other2 );

        ManyToManyUnidirectionalEntity entity = new ManyToManyUnidirectionalEntity();
        entity.setData( "Some data" );
        entity.addOtherEntity( other1 );
        entity.addOtherEntity( other2 );
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
     * @see org.suggs.sandbox.hibernate.support.AbstractSimpleHibernateIntegrationTest#updateEntityForUpdateTest(java.lang.Object)
     */
    @Override
    protected void updateEntityForUpdateTest( ManyToManyUnidirectionalEntity aEntity ) {
        aEntity.setData( "Updated data" );
    }
}
